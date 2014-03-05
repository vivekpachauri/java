package regularexp;

import java.util.LinkedList;
import java.util.Queue;


/* class to match a regular expression with a candidate string and idenfity if the candidate match the expression or not */
public class RegularExpressionParser
{
    /* method to test if the given candidate string match the given regular expression */
    public boolean match (String expression, String candidate)
    {
        //handle the two base case: 1 - empty regular expression matched with empty string results in mismatch
        // 2 - empty regular expression matched with non empty string results in mismatch
        if ( expression == null ) return false;
        if ( expression.equals("") == true ) return false;
        return matchInternal(tokenize(expression), candidate);
    }

    /* 
     * I can see a very strong indication of a recursive pattern here, match the first token in the regular expression
     * with the remaining candidate string if it match then 
     */
    private boolean matchInternal(Queue<Token> tokens, String candidate)
    {
        /*
         * break the expression into token queue
         */
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
        return false;
    }
    
    private boolean matchInternalWithMultipleCandidates(Queue<Token> tokens, String[] candidates)
    {
        return false;
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
        public abstract boolean match(String character);
    }
    private class Asterix extends Token
    {
        public boolean match(String character)
        {
            //no need to do emptyness check on the param, this will match anything
            return true;
        }
    }
    
    private class Period extends Token
    {
        public boolean match(String character)
        {
            //dot will match any character
            if ( character != null )
            {
                if ( character.equals("") == false )
                {
                    return true;
                }
            }
            return false;
        }
    }
    
    private class Character extends Token
    {
        private String character;
        public Character(String character)
        {
            this.character = character;
        }
        
        public boolean match(String character)
        {
            return this.character.equals(character);
        }
    }
}
