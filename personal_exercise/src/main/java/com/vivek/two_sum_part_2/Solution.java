package com.vivek.two_sum_part_2;

public class Solution {
    /*
     * Given a 1-indexed array of integers numbers that is already sorted in
     * non-decreasing order, find two numbers such that they add up to a specific
     * target number. Let these two numbers be numbers[index1] and numbers[index2]
     * where 1 <= index1 < index2 <= numbers.length.
     * 
     * Return the indices of the two numbers, index1 and index2, added by one as an
     * integer array [index1, index2] of length 2.
     * 
     * The tests are generated such that there is exactly one solution. You may not
     * use the same element twice.
     * 
     * Your solution must use only constant extra space.
     * 
     * 
     * 
     * Example 1:
     * 
     * Input: numbers = [2,7,11,15], target = 9
     * Output: [1,2]
     * Explanation: The sum of 2 and 7 is 9. Therefore, index1 = 1, index2 = 2. We
     * return [1, 2].
     * Example 2:
     * 
     * Input: numbers = [2,3,4], target = 6
     * Output: [1,3]
     * Explanation: The sum of 2 and 4 is 6. Therefore index1 = 1, index2 = 3. We
     * return [1, 3].
     * Example 3:
     * 
     * Input: numbers = [-1,0], target = -1
     * Output: [1,2]
     * Explanation: The sum of -1 and 0 is -1. Therefore index1 = 1, index2 = 2. We
     * return [1, 2].
     * 
     * 
     * Constraints:
     * 
     * 2 <= numbers.length <= 3 * 104
     * -1000 <= numbers[i] <= 1000
     * numbers is sorted in non-decreasing order.
     * -1000 <= target <= 1000
     * The tests are generated such that there is exactly one solution.
     */
    public int[] twoSum(int[] numbers, int target) {
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
                solution[0] = beginning + 1;
                solution[1] = end + 1;
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
        
        int[] numbers1 = {2, 7, 11, 15};
        int target1 = 9;
        int[] result1 = solution.twoSum(numbers1, target1);
        System.out.println("Example 1: [" + result1[0] + ", " + result1[1] + "]");

        int[] numbers2 = {2, 3, 4};
        int target2 = 6;
        int[] result2 = solution.twoSum(numbers2, target2);
        System.out.println("Example 2: [" + result2[0] + ", " + result2[1] + "]");

        int[] numbers3 = {-1, 0};
        int target3 = -1;
        int[] result3 = solution.twoSum(numbers3, target3);
        System.out.println("Example 3: [" + result3[0] + ", " + result3[1] + "]");
    }

}
