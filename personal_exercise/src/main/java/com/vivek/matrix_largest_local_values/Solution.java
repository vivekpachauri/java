package com.vivek.matrix_largest_local_values;

public class Solution {
    /*
     * You are given an n x n integer matrix grid.
     * 
     * Generate an integer matrix maxLocal of size (n - 2) x (n - 2) such that:
     * 
     * maxLocal[i][j] is equal to the largest value of the 3 x 3 matrix in grid
     * centered around row i + 1 and column j + 1.
     * In other words, we want to find the largest value in every contiguous 3 x 3
     * matrix in grid.
     * 
     * Return the generated matrix.
     * 
     * 
     * 
     * Example 1:
     * 
     * 
     * Input: grid = [[9,9,8,1],[5,6,2,6],[8,2,6,4],[6,2,2,2]]
     * Output: [[9,9],[8,6]]
     * Explanation: The diagram above shows the original matrix and the generated
     * matrix.
     * Notice that each value in the generated matrix corresponds to the largest
     * value of a contiguous 3 x 3 matrix in grid.
     * Example 2:
     * 
     * 
     * Input: grid = [[1,1,1,1,1],[1,1,1,1,1],[1,1,2,1,1],[1,1,1,1,1],[1,1,1,1,1]]
     * Output: [[2,2,2],[2,2,2],[2,2,2]]
     * Explanation: Notice that the 2 is contained within every contiguous 3 x 3
     * matrix in grid.
     * 
     * 
     * Constraints:
     * 
     * n == grid.length == grid[i].length
     * 3 <= n <= 100
     * 1 <= grid[i][j] <= 100
     */
    public int[][] largestLocal(int[][] grid) {
        //identify the size of the result
        int gridSize = grid.length;
        int targetSize = grid.length - 2;
        int targetRow = 0, targetCol = 0;
        int[][] result = new int[targetSize][targetSize];
        for ( int i = 1; i < gridSize-1; i++ ) {
            for ( int j = 1; j < gridSize-1; j++ ) {
                result[targetRow][targetCol] = getMax(grid, i, j);
                targetCol += 1;
                if ( targetCol >= targetSize ) {
                    targetCol = 0;
                    targetRow += 1;
                }
            }
        }
        return result;
    }

    private int getMax(int[][] grid, int row, int col) {
        //method to return the maximum value from the 3x3 grid centered around row, col
        int max = grid[row][col];
        for ( int i = -1; i < 2; i++ ) {
            for ( int j = -1; j < 2; j++ ) {
                int currVal = grid[row + i][col + j];
                System.out.println(String.format("value for row %d and col %d is %d", (row + i), (col + j), currVal));
                max = max > currVal ? max : currVal;
                System.out.println(String.format("max value for grid around row %d and col %d is now %d", row, col, max));
            }
        }
        return max;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        int[][] grid = {{9,9,8,1},{5,6,2,6},{8,2,6,4},{6,2,2,2}};
        int[][] result = s.largestLocal(grid);
        System.out.println(result);
    }
}
