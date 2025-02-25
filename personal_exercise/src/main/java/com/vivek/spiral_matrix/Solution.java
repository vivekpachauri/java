package com.vivek.spiral_matrix;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    /*
     * Given an m x n matrix, return all elements of the matrix in spiral order.
     * 
     * 
     * 
     * Example 1:
     * 
     * 
     * Input: matrix = [[1,2,3],[4,5,6],[7,8,9]]
     * Output: [1,2,3,6,9,8,7,4,5]
     * Example 2:
     * 
     * 
     * Input: matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
     * Output: [1,2,3,4,8,12,11,10,9,5,6,7]
     * 
     * 
     * Constraints:
     * 
     * m == matrix.length
     * n == matrix[i].length
     * 1 <= m, n <= 10
     * -100 <= matrix[i][j] <= 100
     */
    public List<Integer> spiralOrder(int[][] matrix) {
        //identify mxn
        int m = matrix.length;
        int n = matrix[0].length;
        List<Integer> result = new ArrayList<>(m * n);

        int numbersLeft = m * n;
        int topRow = 0, leftCol = 0, bottomRow = m - 1, rightCol = n - 1;
        while ( numbersLeft > 0 ) {
            //read top row from leftCol to rightCol
            for ( int i = leftCol; i <= rightCol && numbersLeft > 0; i++ ) {
                result.add(matrix[topRow][i]);
                numbersLeft--;
            }
            //increse the count of top row
            topRow++;
            //read the right col from second row to the last row
            for ( int i = topRow; i <= bottomRow&& numbersLeft > 0; i++ ) {
                result.add(matrix[i][rightCol]);
                numbersLeft--;
            }
            //decrement right column
            rightCol--;
            //read the bottom row from right column to left column
            for ( int i = rightCol; i >= leftCol&& numbersLeft > 0; i-- ) {
                result.add(matrix[bottomRow][i]);
                numbersLeft--;
            }
            //decrement the bottom row index
            bottomRow--;
            //read the left column from bottom to top
            for ( int i = bottomRow; i >= topRow&& numbersLeft > 0; i-- ) {
                result.add(matrix[i][leftCol]);
                numbersLeft--;
            }
            //increment the left column
            leftCol++;
        }

        return result;
        //while ( m > 0 && n > 0 ) {
        //left to right each column
            //row 
        //one row consumed
        //top to bottom each row, one column consumed
        //right to left each column, one row consumed
        //bottom to top each row, one column consumed

        //repeat above
        //}
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        int[][] matrix1 = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };
        System.out.println(solution.spiralOrder(matrix1)); // Output: [1, 2, 3, 6, 9, 8, 7, 4, 5]

        int[][] matrix2 = {
            {1, 2, 3, 4},
            {5, 6, 7, 8},
            {9, 10, 11, 12}
        };
        System.out.println(solution.spiralOrder(matrix2)); // Output: [1, 2, 3, 4, 8, 12, 11, 10, 9, 5, 6, 7]
    }
}
