package dynamicprogrammingexamples;

/*
 * This class will try to solve the following problem:
 * Given a matrix and each cell of the matrix contains a number greater than 0. Starting with the top
 * right of the matrix and with each movement being able to move either right or down, find the maximum total
 * of the numbers in each cell that could achieved on the way down the matrix.
 */
public class Count2D {

	public static void main (String[] args )
	{
		int[][] data = {{1,2,3},{4,5,6}, {7,8,9}};
		Count2D count = new Count2D();
		count.print2DMatrix(data);
		count.start(data);
	}
	
	public void print2DMatrix(int[][] data)
	{
		for ( int i = 0; i < data.length; i++ )
		{
			for ( int j = 0; j  < data[i].length; j++ )
			{
				System.out.print(data[i][j] + " ");
			}
			System.out.println();
		}
	}
	public void start(int[][] data)
	{
		int[] path = new int[data.length];
		int[][]	 best = new int[data.length][data.length];
		for (int i = 0; i < best.length; i++)
		{
			for ( int j = 0; j < best.length; j++ )
			{
				best[i][j] = -1;
			}
		}
		best[0][0] = data[0][0];
		move(data.length-1, data.length-1, data, path, best);
		int max = move(data.length-1, data.length-1, data);
/*		for ( int i = 0; i < path.length; i++ )
		{
			System.out.print(path + "  ");
		}*/
		System.out.println(max);
		this.print2DMatrix(best);
	}
	
	public int move(int row, int col, int[][] data)
	{
		if ( row == 0 && col == 0 )
		{
			return data[0][0];
		}
		else if ( col == 0 )
		{
			return move(row-1, col, data) + data[row][col];
		}
		else if ( row == 0 )
		{
			return move(row, col-1, data) + data[row][col];
		}
		else return Math.max(move(row-1, col, data),  move(row, col-1, data)) + data[row][col];
		
	}
	public void move(int row, int col, int[][] data, int[] path, int[][] best)
	{
		if ( best[row][col] > -1 )
		{
			return;
		}
		else if ( row == 0 && col == 0 )
		{
			best[row][col] = data[row][col];
		}
		else if ( col == 0 )
		{
			move(row-1, col, data, path, best);
			best[row][col] = data[row][col] + best[row-1][col];
		}
		else if ( row == 0 )
		{	
//			path[0] = + findMax(data[row]);
			int maxToLeft;
			move(row, col-1, data, path, best);
			best[row][col] = data[row][col] + best[row][col-1];
/*			if ( best[row][col-1] > -1 )
			{
				maxToLeft = best[row][col];
			}
			else
			{
				move(row, col-1, data, path, best);
				maxToLeft = best[row][col-1];
			}
			best[row][col] = data[row][col] + maxToLeft;*/
		}
		else
		{
			move(row-1, col, data, path, best);
			move(row, col-1, data, path, best);
			best[row][col] = Math.max(best[row-1][col], best[row][col-1]) + data[row][col];
		}
/*		int[] maxInLast = new int[data.length];
		for ( int i = 0; i < data.length; i++ )
		{
			
		}*/
	}
	
	public int findMax(int[] data)
	{
		int currentMax = data[0];
		for ( int i = 1; i < data.length; i++ )
		{
			if ( currentMax < data[i])
			{
				currentMax = data[i];
			}
		}
		return currentMax;
	}
	
}
