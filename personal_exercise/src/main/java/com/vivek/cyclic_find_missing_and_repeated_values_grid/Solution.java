package com.vivek.cyclic_find_missing_and_repeated_values_grid;

class Solution {

    /*
     * You are given a 0-indexed 2D integer matrix grid of size n * n with values in
     * the range [1, n2]. Each integer appears exactly once except a which appears
     * twice and b which is missing. The task is to find the repeating and missing
     * numbers a and b.
     * 
     * Return a 0-indexed integer array ans of size 2 where ans[0] equals to a and
     * ans[1] equals to b.
     * 
     * 
     * 
     * Example 1:
     * 
     * Input: grid = [[1,3],[2,2]]
     * Output: [2,4]
     * Explanation: Number 2 is repeated and number 4 is missing so the answer is
     * [2,4].
     * Example 2:
     * 
     * Input: grid = [[9,1,7],[8,9,2],[3,4,6]]
     * Output: [9,5]
     * Explanation: Number 9 is repeated and number 5 is missing so the answer is
     * [9,5].
     * 
     * 
     * Constraints:
     * 
     * 2 <= n == grid.length == grid[i].length <= 50
     * 1 <= grid[i][j] <= n * n
     * For all x that 1 <= x <= n * n there is exactly one x that is not equal to
     * any of the grid members.
     * For all x that 1 <= x <= n * n there is exactly one x that is equal to
     * exactly two of the grid members.
     * For all x that 1 <= x <= n * n except two of them there is exatly one pair of
     * i, j that 0 <= i, j <= n - 1 and grid[i][j] == x.
     */
    public int[] findMissingAndRepeatedValues(int[][] nums) {
        // each row is size n
        // assume n is 3 and value is 4
        // find the correct location for this
        // value / n is the correct row
        // value % n is the correct col
        // return array containing number which is missing and which is duplicate
        int[] result = new int[2];
        int row = 0;
        int col = 0;
        int n = nums.length;
        while (row < n) {
            while (col < n) {
                System.out.println("row " + row + " col " + col);
                int correctRow = (nums[row][col] - 1) / n;
                int correctCol = ((nums[row][col] - 1) % n);
                System.out.println("for index " + row + "," + col + " and value " + nums[row][col]
                        + " the correct row is " + correctRow + " and correct col is " + correctCol);
                // 0 index should contain 1, 1 index should contain 2 etc
                if (nums[row][col] != nums[correctRow][correctCol]) {
                    System.out.println("swapping " + nums[row][col] + " at " + row + ", " + col + " with "
                            + nums[correctRow][correctCol] + " at " + correctRow + ", " + correctCol);
                    int temp = nums[correctRow][correctCol];
                    nums[correctRow][correctCol] = nums[row][col];
                    nums[row][col] = temp;
                } else {
                    col++;
                }
            }
            col = 0;
            row++;
        }

        printArray(nums);
        // scan and find the out of place value and it's index
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < nums.length; j++) {
                if (nums[i][j] != ((i * n) + j + 1)) {
                    System.out.println("checking final array index " + i + ", " + j + " with value " + nums[i][j]
                            + " against expected " + ((i * n) + j + 1));
                    return new int[] { nums[i][j], (i * n) + j + 1 };
                }
            }
        }
        return result;
    }

    private void printArray(int[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                System.out.print(arr[i][j] + " -> ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        int[][] grid1 = { { 1, 3 }, { 2, 2 } };
        int[] result1 = solution.findMissingAndRepeatedValues(grid1);
        System.out.println("Result for grid1: [" + result1[0] + ", " + result1[1] + "]");

        int[][] grid2 = { { 9, 1, 7 }, { 8, 9, 2 }, { 3, 4, 6 } };
        int[] result2 = solution.findMissingAndRepeatedValues(grid2);
        System.out.println("Result for grid2: [" + result2[0] + ", " + result2[1] + "]");
    }
}
