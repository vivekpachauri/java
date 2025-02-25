package com.vivek.matrix_toeplitz_test;

public class Solution {
    /*
     * Given an m x n matrix, return true if the matrix is Toeplitz. Otherwise,
     * return false.
     * 
     * A matrix is Toeplitz if every diagonal from top-left to bottom-right has the
     * same elements.
     * 
     * 
     * 
     * Example 1:
     * 
     * 
     * Input: matrix = [[1,2,3,4],[5,1,2,3],[9,5,1,2]]
     * Output: true
     * Explanation:
     * In the above grid, the diagonals are:
     * "[9]", "[5, 5]", "[1, 1, 1]", "[2, 2, 2]", "[3, 3]", "[4]".
     * In each diagonal all elements are the same, so the answer is True.
     * Example 2:
     * 
     * 
     * Input: matrix = [[1,2],[2,2]]
     * Output: false
     * Explanation:
     * The diagonal "[1, 2]" has different elements.
     */
    public boolean isToeplitzMatrix(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        //diagonal
        //start from first column to last column
        for ( int col = 0; col < n; col++ ) {
            //start from first row to last row while making sure that i stays within the range for column size
            int colVal = matrix[0][col];
            for ( int diagonal = 1; (diagonal < m) && ((diagonal + col) < n); diagonal++ ) {
                if ( matrix[diagonal][diagonal + col] != colVal ) {
                    return false;
                }
            }
        }

        for ( int row = 1; row < m; row++ ) {
            int colVal = matrix[row][0];
            for ( int diagonal = 1; (diagonal < n) && ((diagonal + row) < m); diagonal++ ) {
                if ( matrix[diagonal + row][diagonal] != colVal ) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        
        int[][] matrix1 = {
            {1, 2, 3, 4},
            {5, 1, 2, 3},
            {9, 5, 1, 2}
        };
        System.out.println(solution.isToeplitzMatrix(matrix1)); // Output: true

        int[][] matrix2 = {
            {1, 2},
            {2, 2}
        };
        System.out.println(solution.isToeplitzMatrix(matrix2)); // Output: false

        int[][] matrix3 = {
            {36, 59, 71, 15, 26, 82, 87},
            {56, 36, 59, 71, 15, 26, 82},
            {15, 0, 36, 59, 71, 15, 26}
        };
        System.out.println(solution.isToeplitzMatrix(matrix3)); // Output: false


    }
}
