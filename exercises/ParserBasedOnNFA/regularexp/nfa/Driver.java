package regularexp.nfa;


public class Driver
{
    Automaton automaton;
    String pattern, candidate;
    public static void main (String[] args )
    {
        String pattern, candidate = null;
        if ( args.length == 2 )
        {
            pattern = args[0];
            candidate = args[1];
        }
        else
        {
            pattern = "ab.*de";
            candidate = "abcede";
        }
        Driver driver = new Driver(pattern, candidate);
        driver.display();
        System.out.println(driver.testIfValid());
    }
    
    public Driver(String pattern, String candidate)
    {
        automaton = new Automaton();
        automaton.initializeForExpression(pattern);
        this.pattern = pattern;
        this.candidate = candidate;
    }
    
    public boolean testIfValid()
    {
        for ( int i = 0; i < this.candidate.length(); i++ )
        {
            if ( this.automaton.updateAutomaton(new Character(this.candidate.charAt(i)).toString()) == false )
            {
                return false;
            }
        }
        return this.automaton.isEndState();
    }
    
    public void display()
    {
        System.out.println(this.automaton.toString());
    }
}
