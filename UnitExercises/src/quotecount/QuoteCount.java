package quotecount;

public class QuoteCount {
	public static void main (String[] args )
	{
		QuoteCount qC = new QuoteCount();
		String[] dataStrings = {"some data here", "some more data here"};
		
//		System.out.println(qC.countQuotes(dataStrings));
		System.out.println(qC.countInOneFile("some \" more data \" here and \" here".split(" "))); // some " data " here
	}
	
	public int countQuotes(String[] strings )
	{
		Tuple[] countResults = new Tuple[strings.length];
		int index = 0;
		while ( index < strings.length )
		{
			countResults[index++] = countInOneFile(strings[index].split(" "));
		}
		return consolidateResultForIndividualStrings(countResults);
	}
	
	/*
	 * Method to count all non-quoted spaces in one single file or group of strings
	 */
	public Tuple countInOneFile(String[] data)
	{
		System.out.print("data: ");
		for ( String s : data )
		{
			System.out.print(s + " ");
		}
		System.out.println();
		Tuple toReturn = new Tuple();
		boolean quoteSeenOnce = false;
		int evenQuoteSpaceCount = 0, oddQuoteSpaceCount = 0;
		int index = 0;
		while ( index < data.length )
		{
			//first processing - treat the situation as normal as if this is the first file or string being processed without worrying 
			//about what if one quote was already seen in previous file
			

			// is this next token a quote?
			//yes - two quotes seen so flip the quote seen boolean
			if ( data[index].equals("\""))
			{
				 quoteSeenOnce = (!quoteSeenOnce);
			}

			//is quote seen already? -- maybe should not check this first, becasue either way, we are going to have to flip the flag
			//yes
			if ( quoteSeenOnce )
			{
				oddQuoteSpaceCount++;
			}
			else
			{
				evenQuoteSpaceCount++;
			}
			//no
			index++;
		}
		toReturn.isEven = !quoteSeenOnce;
		toReturn.spaceCountForEven = evenQuoteSpaceCount-1;
		toReturn.spaceCountForOdd = oddQuoteSpaceCount-1;
		return toReturn;
	}
	
	private static class Tuple{
		public int spaceCountForEven;
		public int spaceCountForOdd;
		public boolean isEven;
		
		public Tuple()
		{
			this.spaceCountForEven = 0;
			this.spaceCountForOdd = 0;
			this.isEven = false;
		}
		
		public String toString()
		{
			return "[space for even: " + spaceCountForEven + " space for odd: " + spaceCountForOdd + " isEven: " + isEven + "]";
		}
	}
	
	private int consolidateResultForIndividualStrings(Tuple[] results)
	{
		return 0;
	}
}
