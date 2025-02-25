package com.vivek.two_sum;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Solution {
    public int[] twoSum(int[] nums, int target) {
        int[] sortedNums = new int[nums.length];
        for ( int i = 0; i < nums.length; i++ ) {
            sortedNums[i] = nums[i];
        }
        Arrays.sort(sortedNums);
        int[] sortedSolution = twoSumInnser(sortedNums, target);
        int firstIndex = -1;
        int secondIndex = -1;
        for ( int i = 0; ((i < sortedNums.length) && ((firstIndex < 0) || (secondIndex < 0))); i++ ) {
            //sortedSolution[i] = indexMap.get(nums[sortedSolution[i]]);
            if ( nums[i] == sortedNums[sortedSolution[0]] ) {
                firstIndex = i;
            }
            if ( nums[i] == sortedNums[sortedSolution[1]] ) {
                secondIndex = i;
            }
        }
/*
        for ( int i = sortedNums.length - 1; i >= 0 && secondIndex < 0; i-- ) {
            //sortedSolution[i] = indexMap.get(nums[sortedSolution[i]]);
            if ( nums[i] == sortedNums[sortedSolution[1]] ) {
                secondIndex = i;
                break;
            }
        } 
*/
        sortedSolution[0] = firstIndex;
        sortedSolution[1] = secondIndex;
        return sortedSolution;

    }

    private int[] twoSumInnser(int[] numbers, int target) {
        /*
         * Strategy
         * start with the beginning and then end and then move either the left or right pointer depending on whether the sum is too high or too low than
         * target.
         */
        int[] solution = new int[2];
        int beginning = 0, end = numbers.length - 1;
        // solution[0] = beginning;
        // solution[1] = end;
        while ( beginning < end ) {
            int sum = numbers[beginning] + numbers[end];
            if ( sum == target) {
                solution[0] = beginning;
                solution[1] = end;
                return solution;
            }
            else {
                //if the sum is bigger than target then move the index which points to the bigger of the two numbers, else move the pointer which points
                //to the smaller of the two numbers
                if (sum > target ) {
                    //move the right pointer to the left
                    end--;
                }
                else {
                    //move the left pointer to the right
                    beginning++;
                }
            }
        }
        return solution;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        
        int[] nums1 = {2, 7, 11, 15};
        int target1 = 9;
        System.out.println("Test 1: " + Arrays.toString(solution.twoSum(nums1, target1))); // Output should be [0, 1]

        int[] nums2 = {3, 2, 4};
        int target2 = 6;
        System.out.println("Test 2: " + Arrays.toString(solution.twoSum(nums2, target2))); // Output should be [1, 2]

        int[] nums3 = {3, 3};
        int target3 = 6;
        System.out.println("Test 3: " + Arrays.toString(solution.twoSum(nums3, target3))); // Output should be [0, 1]
    }
}
