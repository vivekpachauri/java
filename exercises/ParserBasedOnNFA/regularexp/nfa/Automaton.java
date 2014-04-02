
package regularexp.nfa;

import java.util.Collection;
import java.util.Collections;
import java.util.Queue;

import regularexp.nfa.token.Token;

/**
 * Class to represent an NFA. This will be composed of a certain number of internal nodes and transitions to move
 * between nodes
 * 
 * @author vpacha
 * 
 */
public class Automaton
{

    /* every automaton will contain at least a start node which might or might not also be the end node */
    private Node startNode;

    /* we also need a reference to indicate which node the automaton the currently on, this will also help
     * us decide at the end of parsing if we are at an accepting state or not
     */
    private Node currentNode;

    private Collection<Node> nodes;

    /* the transition information should not be stored here, the transition information should be stored inside the
     * nodes
     */

    public Automaton()
    {

        this.startNode = new Node();
        this.currentNode = this.startNode;
        this.nodes = Collections.<Node> emptyList();
    }

    /**
     * Method to initialize the automaton according to this expression and generate the required nodes and transitions
     * 
     * @param expression
     *            The expression string according to which this automaton will be initialized
     */
    public void initializeForExpression(String expression)
    {
        // generate the required Nodes and Transitions to parse the provided expression

        // tokenize
        Queue<Token> tokens = Token.tokenize(expression);

        /* this reference will be used when generating transition for '*' transition */
        Node previous = this.startNode;
        while ((tokens != null) && (tokens.isEmpty() == false))
        {
            Token next = tokens.poll();
            Node nextNode = null;
            Transition trans = null;
            // generate the transition for this token
            // if the transition is for a '.' or a character then simply create the transition and node object
            if (next.getClass().equals(Token.Asterix.class) == false)
            {
                // generate the new node that this transition will point to
                nextNode = new Node();
                trans = new Transition(nextNode, next);
            }
            else
            {
                // in this case no new node is created so the previous and next node will be the same
                nextNode = previous;

                // think about the case for * transition
                /*
                 * if this is a star transition then:
                 * the new transition should go from the previous node back to the previous node for all
                 * alphabets, if there are more tokens after then then those tokens will be added to the
                 * head of the transition collection meaning those transition if are more specific then
                 * will be selected before the star transition is selected as a catch all transition
                 */
                // create a new transition that goes back to the previous node
                trans = new Transition(nextNode, next);
                // add this transition to the previous transition

            }

            //add the new transition to the previous node
            previous.addTransition(trans);
            
            // if all tokens are consumed then the newly created node 'nextNode' will be an accepting node
            if (tokens.isEmpty() == true)
            {
                nextNode.setNodeAsAccepting();
            }

            // update the previous node reference, in case of star transition this will simply be a no-op since
            //nextNode reference will be the same as the previous node reference
            previous = nextNode;
        }
    }

    /*
     * the automaton also need the following functionality:
     * - to take in a character which represents the next character in the candidate string and update itself
     * according to the current automaton node and whether or not there exist a transition for this character from
     * the current node for this character, this will also update the current node
     * - to tell after all of the candidate string has been consumed if the automaton is at a valid end state
     * I think this is about it, I can start filling out the rest of the details.
     */

    /**
     * function to accept a single candidate character and update the automaton according to the automaton's current
     * node and a transition corresponding to this character. If a valid transition exist for this character then this
     * method will update the automatona and will return true, otherwise if there are no available transition then this
     * method will return false meaning that the automaton is in an invalid state and the given character is not
     * accepted by this automaton. If this method returns true then if means that a valid transition exist for this
     * character and the automaton has been modified according to that transition
     * 
     * @param nextChar
     *            The next character of the candidate string which is being tested for validity according to this
     *            automaton
     * @return true if there exist a transition corresponding to this character from the current node in the automaton,
     *         false otherwise.
     */
    public boolean updateAutomaton(String nextChar)
    {
        if ( nextChar.length() != 1)
        {
            throw new IllegalArgumentException("this method should be called with a string of size 1");
        }
        else
        {
//            Token token = Token.tokenize(nextChar).poll();
            
            //find the transition from the current node which corresponds to this token
            Transition trans = this.currentNode.getTransitionForCharacter(nextChar);
            // if no matching transition then indicate error
            if ( trans == null )
            {
                return false;
            }
            // if valid transition found then update the current node of automaton
            this.currentNode = trans.getTargetNode();
            return true;
        }
    }

    /**
     * Method to indicate if the automaton is currently in an accepting state or not. If the automaton is in an
     * accepting state then it means that the candidate so fas was accepted accoding to this automaton and if not then
     * it means that the candidate does not match this automton.
     * 
     * @return
     */
    public boolean isEndState()
    {
        return this.currentNode.isAcceptingNode();
    }
    
    public String toString()
    {
        StringBuffer sb = new StringBuffer();
        sb.append(this.startNode.toString());
        return sb.toString();
    }

}
