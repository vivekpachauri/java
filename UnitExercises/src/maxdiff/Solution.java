package maxdiff;

public class Solution {
	public static void main (String[] args )
	{
		Solution s = new Solution();
//		int[] data = {1,3,-3};
//		int[] data = {4,3,2,5,1,1};
		int[] data = {1,4,3,2,5};
		System.out.println(s.solve(data));
	}
	
	
	public int solve(int[] data)
	{
		/*
		 * I can smell that memoization will be required for this
		 * in order to solve this in O(n) time, the information I can see getting propagated
		 * when I work on sub-problem is the maximum elements I have up to a point in the left
		 * and right sub-array of the index that I am currently checking.
		 * 
		 * Therefore in order to construct a dynaic programming solution for this I should first
		 * think of the base and last case
		 * 
		 * base case is if there are two elements in the array then I know that the maximum element
		 * of my sub-problem is the first element in the array and the only calculation I have to do is
		 * what is Math.abs ( max(data[0]) - max(data[1]) )
		 * which because of my pre-initialization of the maxLeft and maxRight array will simply be
		 * Math.abs ( maxLeft[0] - maxRight[1] )
		 * 
		 * at my final calculation what I need is to know what is the maximum element of the array
		 * excluding the last element and I have to find that maximum element's different from the
		 * last element of the array so
		 * Math.abs ( max (A[0], .... A[N-2]) - A[N-1] )
		 * 
		 * therefore I need to maintain two additional arrays which will help me find this
		 * max (A[0], A[1], ..... A[N-2]) element and memoize it so that I am not re-calculating it
		 * 
		 * similarly when I dive into a sub-problem by reducing the index k I need to continue to keep
		 * track of the max number I have seen in the right sub-array
		 */
		// create a new array to hold the values of the max elements seen up to
		// that index in the param data
		int[] maxLeft = new int[data.length];	
		int[] maxRight = new int[data.length];
		for ( int i = 0; i < maxLeft.length; i++ )
		{
			maxLeft[i] = Integer.MIN_VALUE;
			maxRight[i] = Integer.MIN_VALUE;
		}
		//maximum up to k=0 will be only the first element therefore
		maxLeft[0] = data[0];
		maxRight[maxRight.length - 1] = data[data.length-1];
		//kick start the aux function with the last possible calculation to be made for k
		return aux(data, maxLeft, maxRight, data.length-1);
	}
	
	/*
	 * create an auxillary function to hold the array containing the maximum values seen upto that index
	 * as part of the function parameter and the current index we are checking.
	 */
	public int aux(int[] data, int[] maxLeft, int[] maxRight, int k)
	{
		//base case, k = 1 since there must be atleast two elements in the source array
		//because 0 <= k < N-1 and what we are finding is 
		//abs(max{A[0], A[1]...A[k]} - max{A[k+1], A[k+2],...A[N-1]} )
		if ( k == 1 )
		{
			//set the value of maxes[k]
			maxLeft[k] = data[k]>maxLeft[k-1]?data[k]:maxLeft[k-1];
//			return Math.abs(maxes[k-1] - data[k]);
			return Math.abs(maxLeft[k-1] - maxRight[k]);
		}

		//recursively call the function again with a sub problem
		maxRight[k - 1] = data[k-1] > maxRight[k] ? data[k-1]: maxRight[k];
		int previousVal = aux(data, maxLeft, maxRight, k-1);
		int currVal = Math.abs(maxLeft[k-1] - maxRight[k]);
		//update the value of maxes at current k
		maxLeft[k] = data[k]>maxLeft[k-1]?data[k]:maxLeft[k-1];
		return currVal > previousVal ? currVal : previousVal;

	}
}
