package com.vivek.search_in_rotated_sorted_array;

public class Solution {
    /*
     * There is an integer array nums sorted in ascending order (with distinct
     * values).
     * 
     * Prior to being passed to your function, nums is possibly rotated at an
     * unknown pivot index k (1 <= k < nums.length) such that the resulting array is
     * [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]]
     * (0-indexed). For example, [0,1,2,4,5,6,7] might be rotated at pivot index 3
     * and become [4,5,6,7,0,1,2].
     * 
     * Given the array nums after the possible rotation and an integer target,
     * return the index of target if it is in nums, or -1 if it is not in nums.
     * 
     * You must write an algorithm with O(log n) runtime complexity.
     * 
     * 
     * 
     * Example 1:
     * 
     * Input: nums = [4,5,6,7,0,1,2], target = 0
     * Output: 4
     * Example 2:
     * 
     * Input: nums = [4,5,6,7,0,1,2], target = 3
     * Output: -1
     * Example 3:
     * 
     * Input: nums = [1], target = 0
     * Output: -1
     * 
     * 
     * Constraints:
     * 
     * 1 <= nums.length <= 5000
     * -104 <= nums[i] <= 104
     * All values of nums are unique.
     * nums is an ascending array that is possibly rotated.
     * -104 <= target <= 104
     */
    public int search(int[] nums, int target) {
        int result = -1;
        int peakIndex = findPeakIndex(nums);
        // System.out.println("peak index " + peakIndex);
        result = searchAsc(nums, 0, peakIndex, target);
        if (result == -1) {
            // System.out.println("not found in first half");
            result = searchAsc(nums, peakIndex + 1, nums.length - 1, target);
        }
        return result;
    }

    private int findPeakIndex(int[] nums) {
        int start = 0;
        int end = nums.length - 1;
        int result = -1;
        // Tuple result = new Tuple();
        while (start < end) {
            int mid = start + ((end - start) / 2);
            // System.out.println("start " + start + " end " + end + " mid " + mid);

            // if mid is the pivot or mid is just to the right
            // of the pivot ([mid] > [mid+1] or [mid] < [mid-1])
            // if we arrive at a single point then return it
            if (nums[mid] > nums[mid + 1]) {
                return mid;
            } else if ((mid > start) && nums[mid] < nums[mid - 1]) {
                return mid - 1;
            }
            // if the value at start is greater than value at mid
            // then it is the continuation of the ascending array, so look to the right
            else if (nums[start] < nums[mid]) {

                start = mid + 1;
            }
            // if the value at start is greater than the value at mid then the switch
            // happened
            // somewhere to the left, so look to the left
            else {
                // search in right half
                end = mid - 1;
            }
        }
        return result;
    }

    private int searchAsc(int[] nums, int start, int end, int target) {
        int result = -1;
        while (start <= end) {
            int mid = start + ((end - start) / 2);
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                // search to the right
                start = mid + 1;
            } else {
                // search to the left
                end = mid - 1;
            }
        }
        return result;
    }
}
