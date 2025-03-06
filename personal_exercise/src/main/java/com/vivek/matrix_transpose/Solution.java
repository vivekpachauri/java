package com.vivek.matrix_transpose;

public class Solution {

    /*
     * Given a 2D integer array matrix, return the transpose of matrix.
     * 
     * The transpose of a matrix is the matrix flipped over its main diagonal,
     * switching the matrix's row and column indices.
     * 
     * 
     * 
     * 
     * 
     * Example 1:
     * 
     * Input: matrix = [[1,2,3],[4,5,6],[7,8,9]]
     * Output: [[1,4,7],[2,5,8],[3,6,9]]
     * Example 2:
     * 
     * Input: matrix = [[1,2,3],[4,5,6]]
     * Output: [[1,4],[2,5],[3,6]]
     * 
     * 
     * Constraints:
     * 
     * m == matrix.length
     * n == matrix[i].length
     * 1 <= m, n <= 1000
     * 1 <= m * n <= 105
     * -109 <= matrix[i][j] <= 109
     */

     public int[][] transpose(int[][] matrix) {
         int m = matrix.length, n = matrix[0].length;
         int[][] result = new int[n][m];
        for ( int i = 0; i < m; i++ ) {
            for ( int j = 0; j < n; j++ ) {
                //swqp [i][j] with [j][i]
                // int temp = matrix[i][j];
                // matrix[i][j] = matrix[j][i];
                // matrix[j][i] = temp;
                result[j][i] = matrix[i][j];
            }
        }
        return result;
     }

     public static void main(String[] args) {
        int[][] arr = {
            {1,2,3},
            {4,5,6}
            // ,
            // {7,8,9}
        };
        Solution s = new Solution();
        int[][] result = s.anticlockwise90(arr);
        s.printArray(result);
     }

     private void printArray(int[][] arr) {
        for ( int i = 0; i < arr.length; i++ ) {
            for ( int j = 0; j < arr[0].length; j++ ) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
         }
     }

     public int[][] inPlaceTranspose(int[][] matrix) {
        //in place transpose
        int rows = matrix.length;
        int cols = matrix[0].length;
        for ( int i = 0; i < rows; i++ ) {
            for ( int j = i; j < cols; j++ ) {
                //if i == j then we are on diagonal which we can skip
                if ( i != j ) {
                    int temp = matrix[i][j];
                    matrix[i][j] = matrix[j][i];
                    matrix[j][i] = temp;
                } 
            }
        }
        return matrix;
     }

     public int[][] transposeAgain(int[][] matrix) {
        int inputRows = matrix.length;
        int inputCols = matrix[0].length;
        int[][] result = new int[inputCols][inputRows];
        for ( int i = 0; i < inputRows; i++ ) {
            for ( int j = 0; j < inputCols; j++) {
                result[j][i] = matrix[i][j];
            }
        }
        return result;
     }

     public int[][] clockwise90(int[][] matrix) {
        int inputRows = matrix.length;
        int inputCols = matrix[0].length;
        int[][] result = new int[inputCols][inputRows];
        for ( int i = inputRows-1; i >= 0; i-- ) {
            for ( int j = 0; j < inputCols; j++) {
                result[j][(inputRows-1)-i] = matrix[i][j];
            }
        }
        return result;
     }

     public int[][] anticlockwise90(int[][] matrix) {
        int inputRows = matrix.length;
        int inputCols = matrix[0].length;
        int[][] result = new int[inputCols][inputRows];
        for ( int i = 0; i < inputRows; i++ ) {
            for ( int j = inputCols - 1; j >= 0; j-- ) {
                result[(inputCols-1)-j][i] = matrix[i][j];
            }
        }
        return result;
     }

}
