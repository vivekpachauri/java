package pascaltriangle;

public class Driver {
	
	public int[][] data = {{0}};
	public static void main (String[] args)
	{
		Driver d = new Driver();
		d.initialize(5);
	}
	
	private void print(int height, int[][] data)
	{

		for (int i = 0; i < height; i++) {
			for (int j = 0; j < height; j++) {
				System.out.print(data[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	public void initialize(int height)
	{
		data = new int[height][height];
		print(height, data);
		load(0, height, data);
		
	}
	
	private void load(int currentLevel, int finalLevel, int[][] data)
	{
		if ( currentLevel > finalLevel )
			return;
		//else 
		data[0][0] = 1;
		for ( int i = 1; i < finalLevel; i++ )
		{
			for ( int j = 0; j <= i; j++ )
			{
				if ( j == 0 ) data[i][j] = 1;
				else if ( j == i) data[i][j] = 1;
				else data[i][j] = data[i-1][j-1]+data[i-1][j];
			}
		}
		print(finalLevel, data);
	}
}
