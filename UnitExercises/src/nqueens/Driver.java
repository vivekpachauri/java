package nqueens;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Driver {
/*	int[] queens;*/
	int boardSize;
	
	public static final void main(String[] args)
	{
		Driver d = new Driver();
		d.boardSize = 4;
//		d.queens = new int[0];
		Set<List<Integer>> sols = d.start();
		System.out.println(sols);
/*		List<Integer> sol = new ArrayList<>();
		sol.add(2);
		sol.add(0);
		sol.add(3);
		System.out.println(d.isSafe(3, sol));*/
	}
	
	public Set<List<Integer>> start()
	{
		return placeQueen(boardSize);
	}
	
	
	public Set<List<Integer>> placeQueen(int queens)
	{
//		System.out.println(queens.length);
		if ( queens == 0 )
		{
			HashSet<List<Integer>> toReturn = new HashSet<List<Integer>>();
			toReturn.add(new ArrayList<Integer>());
			return toReturn;
		}
		Set<List<Integer>> previousSolutions = placeQueen(queens-1);
		Set<List<Integer>> solutions = new HashSet<>();
		//now augment the previous solution with possibly a new queen
		for ( int i = 0; i < boardSize; i++ )
		{
			for ( List<Integer> solution : previousSolutions )
			{
				if ( isSafe(i, solution) )
				{
					List<Integer> newSol = new ArrayList<>(solution);
					newSol.add(i);
					solutions.add(newSol);
				}
			}
		}
		
		return solutions;
	}
	
	public boolean isSafe(int column, List<Integer> partialSolution)
	{
		boolean toReturn = true;
		for ( int i = 0; i < partialSolution.size(); i++ )
		{
			int col = partialSolution.get(i);
			if ( col == column )
			{
				toReturn = false;
			}
			if ( Math.abs(col - column) == partialSolution.size() - i)
			{
				toReturn = false;
			}
		}
		return toReturn;
	}
}
