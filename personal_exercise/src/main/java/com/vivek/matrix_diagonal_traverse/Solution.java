package com.vivek.matrix_diagonal_traverse;

public class Solution {

    /*
     * Given an m x n matrix mat, return an array of all the elements of the array
     * in a diagonal order.
     * 
     * 
     * 
     * Example 1:
     * 
     * 
     * Input: mat = [[1,2,3],[4,5,6],[7,8,9]]
     * Output: [1,2,4,7,5,3,6,8,9]
     * Example 2:
     * 
     * Input: mat = [[1,2],[3,4]]
     * Output: [1,2,3,4]
     * 
     * 
     * Constraints:
     * 
     * m == mat.length
     * n == mat[i].length
     * 1 <= m, n <= 104
     * 1 <= m * n <= 104
     * -105 <= mat[i][j] <= 105
     */
    public int[] findDiagonalOrder(int[][] mat) {
        //total number of diagonals = number of row + (number of cols - 1)
        int numDiagonals = mat.length + (mat[0].length - 1);
        int m = mat.length;
        int n = mat[0].length;
        int[] result = new int[m * n];
        int resultIndex = 0;

        //int row = 0, col = 0;

        //m times the row will start with 0 and then the row will start increasing

        // int diagonalLength = 1;
        for ( int i = 0; i < numDiagonals; i++ ) {
            //loop over all the diagonals
            //for each diagonal we have to check if we are operating on a max size diagonal (smaller of m and n)
            //go from 0 to number of rows
            //create all permutations of indexes from 0 to i
            if ( i % 2 != 0 ) {
                //if odd diagonal then we go right first (0, i) and then diagonally down reducing the higher value and increasing the lower value until we are
                //withing the limit of the indexes again
                int row = Math.max(0, i - (n - 1));
                int col = Math.min(i, (n-1));
                while ( col >= 0 && row <= (m-1) ) {
                    result[resultIndex++] = mat[row][col];
                    row += 1;
                    col -= 1;
                }
                //capture this value and then update the index
            }
            else {
                //if even diagonal then we go down first then right
                int row = Math.min(i, (m-1));
                int col = Math.max(0, i - (m - 1));
                while ( col <= (n-1) && row >= 0 ) {
                    result[resultIndex++] = mat[row][col];
                    row -= 1;
                    col += 1;
                }
            }

        }

        return result;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        int[][] mat1 = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };
        int[] result1 = solution.findDiagonalOrder(mat1);
        System.out.println(java.util.Arrays.toString(result1)); // Output: [1, 2, 4, 7, 5, 3, 6, 8, 9]

        int[][] mat2 = {
            {1, 2},
            {3, 4}
        };
        int[] result2 = solution.findDiagonalOrder(mat2);
        System.out.println(java.util.Arrays.toString(result2)); // Output: [1, 2, 3, 4]

        int[][] mat3 = {
            {1, 2}
        };
        int[] result3 = solution.findDiagonalOrder(mat3);
        System.out.println(java.util.Arrays.toString(result3)); // Output: [1, 2, 3, 4]

        int[][] mat4 = {
            {1},
            {2}
        };
        int[] result4 = solution.findDiagonalOrder(mat4);
        System.out.println(java.util.Arrays.toString(result4)); // Output: [1, 2, 3, 4]


    }
}
