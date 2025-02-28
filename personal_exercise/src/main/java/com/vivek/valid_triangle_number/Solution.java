package com.vivek.valid_triangle_number;

import java.util.Arrays;

public class Solution {
    /*
     * Given an integer array nums, return the number of triplets chosen from the
     * array that can make triangles if we take them as side lengths of a triangle.
     * 
     * 
     * 
     * Example 1:
     * 
     * Input: nums = [2,2,3,4]
     * Output: 3
     * Explanation: Valid combinations are:
     * 2,3,4 (using the first 2)
     * 2,3,4 (using the second 2)
     * 2,2,3
     * Example 2:
     * 
     * Input: nums = [4,2,3,4]
     * Output: 4
     * 
     * 
     * Constraints:
     * 
     * 1 <= nums.length <= 1000
     * 0 <= nums[i] <= 1000
     */
    public int triangleNumber(int[] nums) {
        //start with the two outer numbers and scan through every interior to see if the combination of the three could form a triangle number
        //sort first
        Arrays.sort(nums);
        //small numbers don't tell anything but the largest could be used to check if the other two are greater than the largest so start from the right and
        //go to the left
        int end = nums.length - 1;
        
        return 0;
    }

    private boolean isTriangle(int one, int two, int three) {
        // the sum of each two should be greater than the third
        if ( one + two > three)
            if ( two + three > one)
                if (one + three > two)
                    return true;
        return false;
    }
    public static void main(String[] args) {
        Solution solution = new Solution();
        
        int[] test1 = {2, 2, 3, 4};
        System.out.println("Test 1: " + solution.triangleNumber(test1)); // Expected output: 3
        
        int[] test2 = {4, 2, 3, 4};
        System.out.println("Test 2: " + solution.triangleNumber(test2)); // Expected output: 4
        
        int[] test3 = {11, 4, 9, 6, 15, 18};
        System.out.println("Test 3: " + solution.triangleNumber(test3)); // Expected output: 10

        int[] test4 = {1,2,3,4,5,6};
        System.out.println("Test 4: " + solution.triangleNumber(test4)); // Expected output: 7
    }
    
}
