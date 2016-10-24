package passwordfind;

public class Driver {
	public static void main (String[] args )
	{
		Driver d = new Driver();
		String data = "aa0aB2Baas9abaab";
		System.out.println("longest valid - " + d.find(data));
	}
	
	public int find(String data)
	{
		//first using every index where a number exist in the string, break the string into multiple
		//pieces and then check each piece to see if it is a valid password candidate
		String[] parts = data.split("[0-9]");
		int longestValid = -1;
		for ( String part : parts )
		{
			System.out.println(part);
			if ( this.containsUpperCaseChar(part) )
			{
				if ( part.length() > longestValid )
				{
					longestValid = part.length();
				}
			}
		}
		return longestValid;
	}
	
	/*
	 * function to check if the given string contains atleast one upper case character.
	 * This is done by iterating over every character in the array and then checking if the ascii
	 * value of the character is withint the range of upper case ascii characters
	 */
	private boolean containsUpperCaseChar(String s)
	{
		for ( char c : s.toCharArray())
		{
			int intValue = c;
			if ( intValue >= 65 && intValue <=90 )
			{
				System.out.println("char " + c + " lies withint range of [65-90] " + intValue);
				return true;
			}

		}
		return false;
	}
}
