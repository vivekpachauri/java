package treelevel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
 * This Class contains the logic of printing the nodes of the tree 
 * while keeping track of the level that the nodes exist at.
 * The core of the logic is in methods printLevel and printLevelHelper
 */
public class NodePrinter {

	//root node
	Node tree;
	
	// populate the tree to whichever values in this method
	public void populate() 
	{
		this.tree = new Node(1, new Node(2, new Node(4, null, null), new Node(5, null, null)), new Node(3, new Node(6, null, null), new Node(7, null, null)));
	}

	/*
	 * Method to print out all the nodes of the root node given as parameter
	 */
	public void printLevel(Node node)
	{
		// as discussed during the interview I will send an empty Set of Tuple
		// and the Set will be populated be the recursive delegate method
		Set<Tuple> tuples = new HashSet<>();
		// calling the delegate method here with the starting values of the
		// aggregate variables (level number 0 and empty set of tuples)
		printLevelHelper(node, 0, tuples);

		// once the method returns the set of tuples is properly populated with
		// the level and the nodes that exist at that level, next we conert it
		// into a list and them sort that list so that it could be printed in
		// sorted order of level
		List<Tuple> tupleList = new ArrayList<>(tuples);
		//sort this list
		Collections.sort(tupleList, (Tuple a, Tuple b) -> 
		{
			if (a.level < b.level) 
				return -1; 
			else if ( a.level == b.level ) 
				return 0; 
			else return 1;
		});
		
		// for every tuple in the list, print the node values for every node and
		// the level on which the node was found
		tupleList.forEach((Tuple t) -> 
		{
			t.nodesAtThisLevel.forEach((Node n) -> System.out.print(n.value + "  "));
			System.out.println("<------- level " + t.level);
		});
	}
	
	/*
	 * The auxillary helper recursive method used by printLevel method to
	 * recursively visit the nodes and keep track of which level each node is
	 * found at using aggregate variables as part of parameter list
	 */
	private void printLevelHelper(Node node, int level, Set<Tuple> tuples)
	{
		// base case is easy to keep track of, nothing more to do if node is
		// null
		if ( node == null ) return;

		Tuple tuple = this.getTupleAtIndex(level, tuples);
		if (tuple == null) {
			tuple = new Tuple();
			tuple.level = level;
			tuple.nodesAtThisLevel = new ArrayList<>();
		}
		tuple.nodesAtThisLevel.add(node);
		tuples.add(tuple);
		printLevelHelper(node.left, level + 1, tuples);
		printLevelHelper(node.right, level + 1, tuples);

	}
	
	/*
	 * Helper method to iterate over all the tuples and return the tuple which
	 * was created for the 'index' level
	 */
	private Tuple getTupleAtIndex(int index, Set<Tuple> tuples)
	{
		Tuple toReturn = null;
		for (Tuple tuple : tuples )
		{
			if ( tuple.level == index )
			{
				toReturn = tuple;
			}
		}
		return toReturn;
	}
	
	public static void main(String[] args)
	{
		NodePrinter np = new NodePrinter();
		np.populate();
//		np.print(np.tree);
		np.printLevel(np.tree);
	}
	
	/*
	 * Tuple object to combine integer representing level and a collection of
	 * Node which represents all the nodes which are at this level 
	 * Note - equals method should check the level integer so that 
	 * a Set of Tuple object will behave as expected.
	 */
	public static class Tuple{
		public Integer level;
		public List<Node> nodesAtThisLevel = new ArrayList<>();
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((level == null) ? 0 : level.hashCode());
			return result;
		}
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Tuple other = (Tuple) obj;
			if (level == null) {
				if (other.level != null)
					return false;
			} else if (!level.equals(other.level))
				return false;
			return true;
		}
		@Override
		public String toString() {
			return "Tuple [level=" + level + ", nodesAtThisLevel="
					+ nodesAtThisLevel + "]";
		}
		
	}
	
}
