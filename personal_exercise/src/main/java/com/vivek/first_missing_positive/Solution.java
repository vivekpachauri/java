package com.vivek.first_missing_positive;

import java.util.Arrays;

public class Solution {

    /*
     * Given an unsorted integer array nums. Return the smallest positive integer
     * that is not present in nums.
     * 
     * You must implement an algorithm that runs in O(n) time and uses O(1)
     * auxiliary space.
     * 
     * 
     * 
     * Example 1:
     * 
     * Input: nums = [1,2,0]
     * Output: 3
     * Explanation: The numbers in the range [1,2] are all in the array.
     * Example 2:
     * 
     * Input: nums = [3,4,-1,1]
     * Output: 2
     * Explanation: 1 is in the array but 2 is missing.
     * Example 3:
     * 
     * Input: nums = [7,8,9,11,12]
     * Output: 1
     * Explanation: The smallest positive integer 1 is missing.
     * 
     * 
     * Constraints:
     * 
     * 1 <= nums.length <= 105
     * -231 <= nums[i] <= 231 - 1
     */

    public int firstMissingPositive(int[] nums) {
        // int smallestPosible = 1;
        // int largetstPossible = 1;
        // //re-order around pivot of 0
        // int lastNegativeIndex = 0;
        // for ( int i = 0; i < nums.length; i++ ) {
        //     if ( nums[i] < 0) {
        //         //doesn't tell us anything
        //         swap(lastNegativeIndex, i, nums);
        //         lastNegativeIndex++;
        //     }
        // }

        // printArray(nums);

        // int index = lastNegativeIndex;
        // //cyclic sort from lastNegativeIndex onwards
        // while ( index < nums.length ) {
        //     int expectedValue = (1 + (index % lastNegativeIndex));
        //     System.out.println("index " + index + " expectedValue " + expectedValue);
        //     int correct = nums[index] + lastNegativeIndex;
        //     if ( (nums[index] < (nums.length - 1)) && (nums[index] != expectedValue) ) {
        //         swa
        //     }

        // }
        // return 0;
        /*
         * think out loud
         * we have list of integers, unsorted
         * sorting is probably not an option being n squared
         * we can find the min and max in o(1)
         * we can find the diff between current num and the min or max
         * what we want to find are the chains
         */

        int index = 0;
        while ( index < nums.length ) {
            int correct = nums[index] - 1;
            if ( ( nums[index] > 0 ) && (nums[index] != nums[correct]) ) {
                swap(index, correct, nums);
            }
            else {
                index++;
            }
        }
        printArray(nums);
        for ( int i = 0; i < nums.length; i++ ) {
            if ( nums[i] != (i + 1) ) {
                return (i + 1);
            }
        }
        return 0;
     }

     private void swap(int i, int j, int[] arr) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
     }

     private void printArray(int[] arr) {
        Arrays.stream(arr).forEach(a -> System.out.print(a + " -> "));
     }

     public static void main(String[] args) {
        Solution s = new Solution();
        int[] arr = new int[]{-1,0,-2,4,5,0,-3, 1};
        System.out.println("missing number is " + s.firstMissingPositive(arr));
     }
}
