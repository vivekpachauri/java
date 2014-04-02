package regularexp.nfa;

import regularexp.nfa.token.Token;


/**
 * Class to represent a transition in an nfa. The transition should contain information inside them to indicate
 * which node they are going to be pointing to if this transitioin is taken.
 * 
 * @author vpacha
 *
 */
public class Transition
{
    /* The target node of this transition */
    private Node target;
    
    /* this will capture the character or language element for which this transition should be taken */
//    private String transitionCharacter;
    /* this will capture the token for which this transition should be taken  */
    private Token transitionToken;
    
    /**
     * Default constructor
     * @param target
     * The target node that this transition leads to
     * @param character
     */
    public Transition(Node target, Token token)
    {
        this.target = target;
        this.transitionToken = token;
    }

    /**
     * Method to check if this transition match the given character
     * @param character
     * The character which is to be tested for match with this transition
     * @return
     * true if this transition is valid for the given parameter character, false otherwise
     */
    public boolean isTransitionValidForCharacter(String character)
    {
        return this.transitionToken.match(character);
    }

    public Node getTargetNode()
    {
        return target;
    }
    
    public String toString()
    {
        StringBuffer sb = new StringBuffer();
        sb.append("transition for token: " + this.transitionToken.toString() + " to node: " + this.target.toString());
        return sb.toString();
    }
}
