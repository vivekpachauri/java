package com.vivek.three_sum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Solution {
    /**
     * Given an integer array nums, return all the triplets [nums[i], nums[j],
     * nums[k]] such that i != j, i != k, and j != k, and nums[i] + nums[j] +
     * nums[k] == 0.
     * 
     * Notice that the solution set must not contain duplicate triplets.
     * 
     * 
     * 
     * Example 1:
     * 
     * Input: nums = [-1,0,1,2,-1,-4]
     * Output: [[-1,-1,2],[-1,0,1]]
     * Explanation:
     * nums[0] + nums[1] + nums[2] = (-1) + 0 + 1 = 0.
     * nums[1] + nums[2] + nums[4] = 0 + 1 + (-1) = 0.
     * nums[0] + nums[3] + nums[4] = (-1) + 2 + (-1) = 0.
     * The distinct triplets are [-1,0,1] and [-1,-1,2].
     * Notice that the order of the output and the order of the triplets does not
     * matter.
     * Example 2:
     * 
     * Input: nums = [0,1,1]
     * Output: []
     * Explanation: The only possible triplet does not sum up to 0.
     * Example 3:
     * 
     * Input: nums = [0,0,0]
     * Output: [[0,0,0]]
     * Explanation: The only possible triplet sums up to 0.
     * 
     * 
     * Constraints:
     * 
     * 3 <= nums.length <= 3000
     * -10^5 <= nums[i] <= 10^5
     * 
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        /*
        * [.......,a...,b,.....,c....]
        * sort? likely yes, we don't have to return index from the original array so no complication of preserving the original index
        */
        Arrays.sort(nums);
        //recursive? likely yes but first let's try iterative
        //if after sorting the smallest element is greater than 0 or largest element less than 0 then no way the sum can reach 0
        if ((nums.length < 3) || (nums[0] > 0) || (nums[nums.length - 1] < 0)) {
            return result;
        }
        int beginning = 0, end = nums.length, mid = beginning + 1;
        while (( beginning < end ) && ( mid < end )) {
            int small = nums[beginning], medium = nums[mid], large = nums[end];
            if ( small + medium + large == 0 ) {
                List<Integer> solution = new ArrayList<>();
                solution.add(small);
                solution.add(medium);
                solution.add(large);
                result.add(solution);
            }
            //if total is net positive then we need to move mid to either left or right
            // -5 -4 -3 -2  -1  6, 9, 10, 11, 12
            else if ( small + medium + large > 0 ) {
                //check if 
            }
        }
        //function to have the array and to check the target and how many numbers to find
        //threeSum(int[] nums, int target, int count, int[] result)
        //base case if nothing to find then return the result array
        //if only one number to find and 
        
        return Arrays.asList(Arrays.asList(1,2,3));

    }
}
