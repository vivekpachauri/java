package regularexp;

import java.util.LinkedList;
import java.util.Queue;


/* class to match a regular expression with a candidate string and idenfity if the candidate match the expression or not */
public class RegularExpressionParser
{
    
    public static void main (String[] args )
    {
        RegularExpressionParser rep = new RegularExpressionParser();
        if (args.length != 2)
        {
            System.out.println(rep.match("abc*", "ababc"));
        }
        else{
            System.out.println(rep.match(args[0], args[1]));
        }
    }
    
    /* method to test if the given candidate string match the given regular expression */
    public boolean match (String expression, String candidate)
    {
        //handle the two base case: 1 - empty regular expression matched with empty string results in mismatch
        // 2 - empty regular expression matched with non empty string results in mismatch
        if ( expression == null ) return false;
        if ( expression.equals("") == true ) return false;
        /*
         * break the expression into token queue
         */
        return matchInternalWithMultipleCandidates(tokenize(expression), new String[]{candidate});
    }

    /* 
     * I can see a very strong indication of a recursive pattern here, match the first token in the regular expression
     * with the remaining candidate string if it match then 
     */
    private boolean matchInternal(Queue<Token> tokens, String candidate)
    {
        
        
        /*
         * as we match one token with the remaining characters in the candidate, it might result in multiple possible
         * remaining candidate strings, example - "a*bc" expression when matched with the candidate "aaabbbabc" will
         * result in following scenarios:
         * when first a is matched the remaining candidate will be aabbbabc
         * 
         * when the asterix is matched the remaining candidate will be dependent on what lies beyond asterix or it could
         * result in as many different possibilities as the length of the candidate such that each possibility is same as
         * the previous minus the first character, matching the asterix means that we should be matching the remaining
         * tokens with all the possibilities of the remaining candidate, and if any one match then the match is successful
         * for this string
         */
        
        /*
         * if no more tokens left
         */
        if ( tokens.isEmpty() == true )
        {
            /*
             * if candidate has been fully consumed then successful parse
             */
            if ( candidate.equals("") == true )
            {
                return true;
            }
            /*
             * else if the candidate still remains unconsumed by the expression then unsuccessful merge
             */
            else
            {
                return false;
            }
        }
        
        Token next = tokens.poll();
        //match the first with the candidate, if matches then match the remaining
        if ( next.match(candidate) == true)
        {
            return matchInternalWithMultipleCandidates(tokens, next.getRemainingExpressionsAfterMatch(candidate));
        }
        return false;
    }
    
    private boolean matchInternalWithSingleCandidate(Queue<Token> tokens, String candidate)
    {
        
        /*
         * if no more tokens left
         */
        if ( tokens.isEmpty() == true )
        {
            /*
             * if candidate has been fully consumed then successful parse
             */
            if ( candidate.equals("") == true )
            {
                return true;
            }
            /*
             * else if the candidate still remains unconsumed by the expression then unsuccessful merge
             */
            else
            {
                return false;
            }
        }
        
        /*
         * if the first token match the first character in the candidate then the match so far is successful,
         * then call this method recursively with the remaining tokens and the new candidate
         */
        Token nextToken = tokens.poll();
        if (nextToken.match(candidate) == true )
        {
            String[] candidates = nextToken.getRemainingExpressionsAfterMatch(candidate);
            if ( candidates.length > 1){
                return matchInternalWithMultipleCandidates(tokens, candidates);
            }
            else
            {
                return matchInternalWithSingleCandidate(tokens, candidates[0]);
            }
        }
        return false;
    }
    
    private boolean matchInternalWithMultipleCandidates(Queue<Token> tokens, String[] candidates)
    {
        
        /*
         * if no more tokens left
         */
        if ( tokens.isEmpty() == true )
        {
            /*
             * if candidate has been fully consumed then successful parse
             */
            if ( candidates.length == 1 && (candidates[0].equals("") == true))
            {
                return true;
            }
            /*
             * else if the candidate still remains unconsumed by the expression then unsuccessful merge
             */
            else
            {
                return false;
            }
        }
        /*
         * if the tokens are not empty then try to match
         */
        boolean result = false;
        Token token = tokens.poll();
        // get the result of matching the first token with all the candidates
        for ( int i = 0; i < candidates.length; i++ )
        {
            //if the token match with a candidate, then try to match the remaining tokens with the remaining candidates
            //generated by this token
            if ( token.match(candidates[i]) == true )
            {
                result = result || matchInternalWithMultipleCandidates(new LinkedList<Token>(tokens), token.getRemainingExpressionsAfterMatch(candidates[i]));
            }
//            result = result || matchInternalWithSingleCandidate(tokens., candidate)
        }
        return result;
    }
    
    private Queue<Token> tokenize(String expression)
    {
        Queue<Token> toReturn = new LinkedList<Token>();
        Token previous = null;
        Token current = null;
        while ( expression.equals("") == false )
        {
            //get the first character
            String firstChar = expression.substring(0,1);
            //chop off the first character
            expression = expression.substring(1);
            switch (firstChar )
            {
            case ".":
                current = new Period();
                break;
            case "*":
                current = new Asterix();
                break;
            default:
                current = new Character(firstChar);
            }
            if ( previous == null)
            {
                previous = current;
                toReturn.add(current);
            }
            else
            {
                //if we just recently added an asterix then we don't need to add it again
                if ( current.getClass().equals(Asterix.class) == true )
                {
                    if ( previous.getClass().equals(Asterix.class) == true )
                    {
                        //if both the current and previous are asterix then do nothing
                        ;
                    }
                    else
                    {
                        toReturn.add(current);
                    }
                }
                else
                {
                    toReturn.add(current);
                }
            }
        }
        
        return toReturn;
    }
    
    private abstract class Token
    {
        public abstract boolean match(String candidate);

        public abstract String[] getRemainingExpressionsAfterMatch(String expression);
    }
    private class Asterix extends Token
    {
        public boolean match(String character)
        {
            //no need to do emptyness check on the param, this will match anything
            return true;
        }
        
        @Override
        public String[] getRemainingExpressionsAfterMatch(String expression)
        {
            if ( expression.equals("") == true )
            {
                return new String[0];
            }

            StringBuffer temp = new StringBuffer(expression);
            String[] toReturn = new String[temp.length() + 1];
            for ( int i = 0; i <= expression.length(); i++ )
            {
                toReturn[i] = temp.substring(i);
            }
            return toReturn;
        }
    }
    
    private class Period extends Token
    {
        public boolean match(String candidate)
        {
            //match fail if candidate is empty
            if ( candidate == null || (candidate.equals("") == true) )
            {
                return false;
            }
            String character = candidate.substring(0,1);
            //dot will match any non empty and non-null character
            if ( character != null )
            {
                if ( character.equals("") == false )
                {
                    return true;
                }
            }
            return false;
        }
        
        @Override
        public String[] getRemainingExpressionsAfterMatch(String expression)
        {
            if ( expression.equals("") == true )
            {
                return new String[0];
            }
            else {
                return new String[]{expression.substring(1)};
            }
        }
    }
    
    private class Character extends Token
    {
        private String character;
        public Character(String character)
        {
            this.character = character;
        }
        
        public boolean match(String candidate)
        {
            //match fail if candidate is empty
            if ( candidate == null || (candidate.equals("") == true) )
            {
                return false;
            }
            String character = candidate.substring(0,1);
            return this.character.equals(character);
        }
        
        @Override
        public String[] getRemainingExpressionsAfterMatch(String expression)
        {
            if ( expression.equals("") == true )
            {
                return new String[0];
            }
            else {
                return new String[]{expression.substring(1)};
            }
        }
    }
}
