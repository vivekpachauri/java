package com.vivek.missing_number;

public class Solution {

    /*
     * Given an array nums containing n distinct numbers in the range [0, n], return
     * the only number in the range that is missing from the array.
     * 
     * 
     * 
     * Example 1:
     * 
     * Input: nums = [3,0,1]
     * 
     * Output: 2
     * 
     * Explanation:
     * 
     * n = 3 since there are 3 numbers, so all numbers are in the range [0,3]. 2 is
     * the missing number in the range since it does not appear in nums.
     * 
     * Example 2:
     * 
     * Input: nums = [0,1]
     * 
     * Output: 2
     * 
     * Explanation:
     * 
     * n = 2 since there are 2 numbers, so all numbers are in the range [0,2]. 2 is
     * the missing number in the range since it does not appear in nums.
     * 
     * Example 3:
     * 
     * Input: nums = [9,6,4,2,3,5,7,0,1]
     * 
     * Output: 8
     * 
     * Explanation:
     * 
     * n = 9 since there are 9 numbers, so all numbers are in the range [0,9]. 8 is
     * the missing number in the range since it does not appear in nums.
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * Constraints:
     * 
     * n == nums.length
     * 1 <= n <= 104
     * 0 <= nums[i] <= n
     * All the numbers of nums are unique.
     */

     public int missingNumber(int[] nums) {
        
        int retVal = nums.length;
        int index = 0;
        while ( index < nums.length ) {
            int correctLocation = (nums[index] < nums.length) ? nums[index] : (nums[index] - 1);
            if ( (nums[index] < nums.length) && nums[index] != nums[correctLocation] ) {
                int temp = nums[index];
                nums[index] = nums[correctLocation];
                nums[correctLocation] = temp;
            }
            else {
                index++;
            }
        }
        //printArr(nums);
        for ( int i = 0; i < nums.length; i++ ) {
            //System.out.println("comparing " + nums[i] + " and " + i);
            if (nums[i] != i) {
                //System.out.println("not equal, return " + i);
                retVal = i;
                break;
            }
        }
        return retVal;
    }

    public void printArr(int[] arr) {
        for ( int i : arr ) {
            System.out.print(i + " -> ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        int[] arr = new int[] {1,2};
        System.out.println("missing number is " + s.missingNumber(arr));
    }
}
