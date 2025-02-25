package com.vivek.rotate_matrix;

public class Solution {
    /*
     * You are given an n x n 2D matrix representing an image, rotate the image by
     * 90 degrees (clockwise).
     * 
     * You have to rotate the image in-place, which means you have to modify the
     * input 2D matrix directly. DO NOT allocate another 2D matrix and do the
     * rotation.
     * 
     * 
     * 
     * Example 1:
     * 
     * 
     * Input: matrix = [[1,2,3],[4,5,6],[7,8,9]]
     * Output: [[7,4,1],[8,5,2],[9,6,3]]
     * Example 2:
     * 
     * 
     * Input: matrix = [[5,1,9,11],[2,4,8,10],[13,3,6,7],[15,14,12,16]]
     * Output: [[15,13,2,5],[14,3,4,1],[12,6,8,9],[16,7,10,11]]
     * 
     * 
     * Constraints:
     * 
     * n == matrix.length == matrix[i].length
     * 1 <= n <= 20
     * -1000 <= matrix[i][j] <= 1000
     */

     public void rotate(int[][] matrix) {
        int n = matrix.length - 1;
        for ( int i = 0; i < matrix.length; i++ ) {
            for ( int j = i; j < matrix.length; j++ ) {
                //swap [i][j] with [n-i][n-j]
                // System.out.println("swapping [" + j + "][" + (n-i) + "] with [" + i + "][" + j + "]");
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
        printMatrix(matrix);

        //now reverse each row
        for ( int i = 0; i < matrix.length; i++ ) {
            for ( int first = 0, last = matrix.length - 1; first < last; first++, last-- ) {
                int temp = matrix[i][first];
                matrix[i][first] = matrix[i][last];
                matrix[i][last] = temp;
            }
        }
     }
    public static void main(String[] args) {
        Solution solution = new Solution();

        int[][] matrix1 = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };
        printMatrix(matrix1);
        solution.rotate(matrix1);
        printMatrix(matrix1);

        int[][] matrix2 = {
            {5, 1, 9, 11},
            {2, 4, 8, 10},
            {13, 3, 6, 7},
            {15, 14, 12, 16}
        };
        printMatrix(matrix2);
        solution.rotate(matrix2);
        printMatrix(matrix2);
    }

    private static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            for (int val : row) {
                System.out.print(val + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
