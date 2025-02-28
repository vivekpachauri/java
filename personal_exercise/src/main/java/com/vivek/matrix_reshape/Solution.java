package com.vivek.matrix_reshape;

public class Solution {

    /*
     * In MATLAB, there is a handy function called reshape which can reshape an m x
     * n matrix into a new one with a different size r x c keeping its original
     * data.
     * 
     * You are given an m x n matrix mat and two integers r and c representing the
     * number of rows and the number of columns of the wanted reshaped matrix.
     * 
     * The reshaped matrix should be filled with all the elements of the original
     * matrix in the same row-traversing order as they were.
     * 
     * If the reshape operation with given parameters is possible and legal, output
     * the new reshaped matrix; Otherwise, output the original matrix.
     * 
     * 
     * 
     * Example 1:
     * 
     * 
     * Input: mat = [[1,2],[3,4]], r = 1, c = 4
     * Output: [[1,2,3,4]]
     * Example 2:
     * 
     * 
     * Input: mat = [[1,2],[3,4]], r = 2, c = 4
     * Output: [[1,2],[3,4]]
     * 
     * 
     * Constraints:
     * 
     * m == mat.length
     * n == mat[i].length
     * 1 <= m, n <= 100
     * -1000 <= mat[i][j] <= 1000
     * 1 <= r, c <= 300
     */
    public int[][] matrixReshape(int[][] mat, int r, int c) {
        int m = mat.length, n = mat[0].length;
        if ( (m * n) != (r * c) ) {
            return mat;
        }

        int result[][] = new int[r][c];
        //everything should go in increasing column order first and then increasing row order
        int targetRow = 0, targetCol = 0;
        for ( int i = 0; i < m; i++ ) {
            for ( int j = 0; j < n; j ++ ) {
                //put the current value in the next appropriate array spot
                result[targetRow][targetCol] = mat[i][j];
                //increment the target col and if it reaches max then reset it to 0 and then increase the target row
                targetCol++;
                if ( targetCol >= c ) {
                    //reset the targetCol and increment the target row
                    targetCol = 0;
                    targetRow++;
                }
            }
        }
        return result;

    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        int[][] mat1 = {{1, 2}, {3, 4}};
        int r1 = 1, c1 = 4;
        int[][] result1 = solution.matrixReshape(mat1, r1, c1);
        System.out.println("Test 1:");
        for (int[] row : result1) {
            for (int num : row) {
                System.out.print(num + " ");
            }
            System.out.println();
        }

        int[][] mat2 = {{1, 2}, {3, 4}};
        int r2 = 2, c2 = 4;
        int[][] result2 = solution.matrixReshape(mat2, r2, c2);
        System.out.println("Test 2:");
        for (int[] row : result2) {
            for (int num : row) {
                System.out.print(num + " ");
            }
            System.out.println();
        }
    }

    
}
