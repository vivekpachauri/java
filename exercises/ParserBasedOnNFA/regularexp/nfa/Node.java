
package regularexp.nfa;

import java.util.Stack;

/**
 * Class to represent an nfa node. These nodes should also contain the transition information inside them.
 * 
 * @author vpacha
 * 
 */
/*
 * So far I'm thinking that the node class is going to contain the transitions that goes out from this node.
 * Need to re think if I need to capture the information abou the current state and if I store that information
 * then how will I store it.
 * For now let's try to avoid putting any expression related information in the node, if this turns out to
 * be a problem then we'll try to change it.
 */
public class Node
{

    /* this should be an ordered collection so that for nodes with star transitions we will have two transitions,
     * first will be the more specific or the preferred transition which will be taken for the desired alphabet,
     * and the second will be the transition which will be taken for every other alphabet
     */
    private Stack<Transition> transitions;

    /** to indicate that this node is an end or accepting node */
    private boolean isEndNode;

    public Node()
    {

        this.transitions = new Stack<Transition>();
        this.isEndNode = false;
    }

    /**
     * Method to return the transition out of all available transitions which matches the given character
     * 
     * @param character
     *            The character for which an available transition should be searched for
     * @return The transition which match the parameter character, null if no transitions are available for this
     *         character from this node
     */
    public Transition getTransitionForCharacter(String character)
    {

        Stack<Transition> stack = new Stack<Transition>();
        for (Transition t : this.transitions)
        {
            stack.push(t);
        }

        while (stack.isEmpty() == false)
        {
            Transition t = stack.pop();
            if (t.isTransitionValidForCharacter(character) == true)
            {
                return t;
            }
        }
        return null;
    }

    /**
     * Method to add a new transition to the available list of transitions to this node
     * 
     * @param t
     *            The transition to be added
     */
    public void addTransition(Transition t)
    {

        this.transitions.push(t);
    }

    /**
     * Method to indicate if this node represent one of the end or accepting node in the automaton
     * 
     * @return true if this node is an accepting or end node, false otherwise
     */
    public boolean isAcceptingNode()
    {

        return this.isEndNode;
    }

    public void setNodeAsAccepting()
    {

        this.isEndNode = true;
    }

    @Override
    public String toString()
    {

        StringBuffer sb = new StringBuffer();
        if (this.isEndNode == true)
        {
            sb.append("accepting ");
        }
        sb.append("node " + System.identityHashCode(this) + " with transitions -> ");
        for (Transition t : this.transitions)
        {
            if (t.getTargetNode() != this)
            {
                sb.append(t.toString());
            }
        }
        return sb.toString();
    }
}
