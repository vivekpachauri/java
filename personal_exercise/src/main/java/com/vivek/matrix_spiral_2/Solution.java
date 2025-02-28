package com.vivek.matrix_spiral_2;

public class Solution {
    /*
     * Given a positive integer n, generate an n x n matrix filled with elements
     * from 1 to n2 in spiral order.
     * 
     * 
     * 
     * Example 1:
     * 
     * 
     * Input: n = 3
     * Output: [[1,2,3],[8,9,4],[7,6,5]]
     * Example 2:
     * 
     * Input: n = 1
     * Output: [[1]]
     * 
     * 
     * Constraints:
     * 
     * 1 <= n <= 20
     */
    public int[][] generateMatrix(int n) {
        //calculate how many numbers to be filled
        //create an outer whle loop
        //keep track of the index of the top, bottom, left, right row and column
        //make four consecutive steps filling out the top then right then bottom and then left while making sure that we have not exceeded the total number of grids
        //grids to fill
        int[][] result = new int[n][n];
        int top = 0, left = 0, right = n - 1, bottom = n - 1;
        int totalGrids = n * n;
        int gridCounter = 0;
        while (gridCounter < totalGrids ) {
            System.out.println(1);
            //fill the top from left to right
            for ( int i = left; (i <= right) && (gridCounter < totalGrids ); i++ ) {
                result[top][i] = gridCounter + 1;
                gridCounter++;
                System.out.println(top);
            }
            //increment the top counter by 1
            top += 1;
            //fill the right row from top to bottom
            for ( int i = top; (i <= bottom) && (gridCounter < totalGrids ); i++ ) {
                result[i][right] = gridCounter + 1;
                gridCounter++;
                System.out.println(right);
            }
            //decrement the right index by 1
            right -= 1;
            //fill the bottom row from right to left
            for ( int i = right; (i >= left) && (gridCounter < totalGrids ); i-- ) {
                result[bottom][i] = gridCounter + 1;
                gridCounter++;
                System.out.println(bottom);
            }
            //decrement the bottom index by 1
            bottom--;
            //fill the left column from bottom to top
            for ( int i = bottom; (i >= top) && (gridCounter < totalGrids ); i-- ) {
                result[i][left] = gridCounter + 1;
                gridCounter++;
                System.out.println(left);
            }
            //increment the left index
            left++;
        }
        return result;
    }

    public static void main(String[] args) {
        Solution s = new Solution();

        
        int[][] arr = s.generateMatrix(3);

        s.printArray(arr);
    }

    private void printArray(int[][] arr) {
        for ( int i = 0; i < arr.length; i++ ) {
            for ( int j = 0; j < arr.length; j++ ) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    }

}
