
package regularexp.nfa.token;

import java.util.LinkedList;
import java.util.Queue;

public abstract class Token
{

    public abstract boolean match(String candidate);

    public abstract String[] getRemainingExpressionsAfterMatch(String expression);

    public static class Asterix extends Token
    {

        @Override
        public boolean match(String character)
        {

            // no need to do emptyness check on the param, this will match anything
            return true;
        }

        @Override
        public String[] getRemainingExpressionsAfterMatch(String expression)
        {

            if (expression.equals("") == true)
            {
                return new String[0];
            }

            StringBuffer temp = new StringBuffer(expression);
            String[] toReturn = new String[temp.length() + 1];
            for (int i = 0; i <= expression.length(); i++)
            {
                toReturn[i] = temp.substring(i);
            }
            return toReturn;
        }
        
        @Override
        public String toString()
        {
            return "Asterix token";
        }
    }

    public static class Period extends Token
    {

        @Override
        public boolean match(String candidate)
        {

            // match fail if candidate is empty
            if (candidate == null || (candidate.equals("") == true))
            {
                return false;
            }
            String character = candidate.substring(0, 1);
            // dot will match any non empty and non-null character
            if (character != null)
            {
                if (character.equals("") == false)
                {
                    return true;
                }
            }
            return false;
        }

        @Override
        public String[] getRemainingExpressionsAfterMatch(String expression)
        {

            if (expression.equals("") == true)
            {
                return new String[0];
            }
            else
            {
                return new String[] { expression.substring(1) };
            }
        }
        
        @Override
        public String toString()
        {
            return "Period token";
        }
    }

    public static class Character extends Token
    {

        private String character;

        public Character(String character)
        {

            this.character = character;
        }

        @Override
        public boolean match(String candidate)
        {

            // match fail if candidate is empty
            if (candidate == null || (candidate.equals("") == true))
            {
                return false;
            }
            String character = candidate.substring(0, 1);
            return this.character.equals(character);
        }

        @Override
        public String[] getRemainingExpressionsAfterMatch(String expression)
        {

            if (expression.equals("") == true)
            {
                return new String[0];
            }
            else
            {
                return new String[] { expression.substring(1) };
            }
        }
        
        @Override
        public String toString()
        {
            return "Character token for - " + this.character;
        }
    }

    public static Queue<Token> tokenize(String expression)
    {
        Queue<Token> toReturn = new LinkedList<Token>();
        Token previous = null;
        Token current = null;
        while (expression != null && (expression.equals("") == false) )
        {
            // get the first character
            String firstChar = expression.substring(0, 1);
            // chop off the first character
            expression = expression.substring(1);
            switch (firstChar)
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
            if (previous == null)
            {
                previous = current;
                toReturn.add(current);
            }
            else
            {
                // if we just recently added an asterix then we don't need to add it again
                if (current.getClass().equals(Asterix.class) == true)
                {
                    if (previous.getClass().equals(Asterix.class) == true)
                    {
                        // if both the current and previous are asterix then do nothing
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
}
