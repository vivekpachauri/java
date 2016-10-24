package dynamicprogrammingexamples;

public class LongestSequence {

	public static void main (String[] args )
	{
		LongestSequence s = new LongestSequence();
		int[] data = {4,5,3,2,5,7,6,4,3,4,8,9,6,3};
		s.printLongestSequence(data);
	}
	/*
	 * This method find the length of the longest non-decreasing sequence
	 */
	public int printLongestSequence(int[] data)
	{
		Tuple[] result = new Tuple[data.length];
		for ( int i = 0; i < result.length; i++ )
		{
			result[i] = new Tuple();
		}
//		int[] result = new int[data.length];
//		result[0].lastIndex = -1;
		result[0].lastValue = data[0];
/*		for ( int i = 0; i < result.length; i++ )
		{
			result[i] = -1;
		}*/
		
		for ( int i = 0; i < data.length; i++ )
		{
			result[i].lastValue = 1;
			result[i].lastIndex = -1;
			for ( int j = i-1; j > -1; j--)
			{
				if ( data[i] >= data[j] )
				{
					if ( result[i].lastValue < result[j].lastValue + 1)
					{
						result[i].lastValue = result[j].lastValue+1;
						result[i].lastIndex = j;
					}
				}
			}
		}
		for (Tuple t : result )
		{
			System.out.println(t);
		}
		//print the longest increasing sequence
		int longestIndex = 0;
		int longestValue = 0;
		for ( int i = 0; i < result.length; i++ )
		{
			if (result[i].lastValue > longestValue )
			{
				longestIndex = i;
				longestValue = result[i].lastValue;
			}
		}
		int nextToPrint = longestIndex;
		while ( nextToPrint >= 0 )
		{
			System.out.print(data[nextToPrint] + " -> ");
			nextToPrint = result[nextToPrint].lastIndex;
		}
		return -1;
	}
	
	public static class Tuple{
		public int lastIndex;
		public int lastValue;
		
		public Tuple()
		{
			this.lastIndex = -1;
			this.lastValue = 0;
		}
		
		public String toString()
		{
			return "[" + lastValue + "," + lastIndex + "]";
		}
		
	}
	
}
