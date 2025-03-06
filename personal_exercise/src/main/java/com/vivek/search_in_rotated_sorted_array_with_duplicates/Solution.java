package com.vivek.search_in_rotated_sorted_array_with_duplicates;

public class Solution {

    /*
     * There is an integer array nums sorted in non-decreasing order (not
     * necessarily with distinct values).
     * 
     * Before being passed to your function, nums is rotated at an unknown pivot
     * index k (0 <= k < nums.length) such that the resulting array is [nums[k],
     * nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]] (0-indexed). For
     * example, [0,1,2,4,4,4,5,6,6,7] might be rotated at pivot index 5 and become
     * [4,5,6,6,7,0,1,2,4,4].
     * 
     * Given the array nums after the rotation and an integer target, return true if
     * target is in nums, or false if it is not in nums.
     * 
     * You must decrease the overall operation steps as much as possible.
     * 
     * 
     * 
     * Example 1:
     * 
     * Input: nums = [2,5,6,0,0,1,2], target = 0
     * Output: true
     * Example 2:
     * 
     * Input: nums = [2,5,6,0,0,1,2], target = 3
     * Output: false
     * 
     * 
     * Constraints:
     * 
     * 1 <= nums.length <= 5000
     * -104 <= nums[i] <= 104
     * nums is guaranteed to be rotated at some pivot.
     * -104 <= target <= 104
     * 
     * 
     * Follow up: This problem is similar to Search in Rotated Sorted Array, but
     * nums may contain duplicates. Would this affect the runtime complexity? How
     * and why?
     */

    public boolean search(int[] nums, int target) {
    
        return searchRec(nums, 0, nums.length - 1, target);
    }

    private boolean searchRec(int[] nums, int start, int end, int target) {
        /*
         * we have the full range and the target
         * we can tell based on [mid] and [start] whether the number to the left can be bigger than the [mid]
         * or not
         * 
         * base case
         * if single element in the array then simply compare it to target
         * 
         * 
         */
        int mid = start + ((end - start) / 2);
        if ( start == end ) {
            return nums[start] == target;
        }
        else if ( target == nums[mid] ) {
            return true;
        }
        else if ( target > nums[mid] ) {
            //you are looking for something bigger than [mid]
            //check which side it could be on and make call accordingly
            //if the first value is the same as mid value then also we have to make both calls
            if ( nums[start] == nums[mid] ) {
                return searchRec(nums, start, mid, target) || searchRec(nums, mid+1, end, target);
            }
            else if ( nums[start] < nums[mid] ) {
                //everything on the left is less than or equal to mid
                //something bigger than mid can only exist on the right side
                //look to the right
                return searchRec(nums, mid+1, end, target);
            }
            else {
                //thing on the left could be bigger and smaller than the mid
                //and things on the right could be bigger than the mid as well
                //look on both sides
                return searchRec(nums, start, mid, target) || searchRec(nums, mid+1, end, target);
            }
        }
        else {
            //target is smaller than [mid]
            if ( nums[start] == nums[mid] ) {
                //if value at start is equal to value at mid then smaller values could exist
                //on either side of mid so make both calls
                return searchRec(nums, start, mid, target) || searchRec(nums, mid+1, end, target);
            }
            if ( nums[start] < nums[mid] ) {
                //things on both left and right could be smaller than mid, look on both sides
                return searchRec(nums, start, mid, target) || searchRec(nums, mid+1, end, target);
            }
            else {
                //if the [start] is greater than [mid] then everything on the right is greater
                //or equal to mid
                //look to the left side only
                return searchRec(nums, start, mid, target);
            }
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        
        int[] nums1 = {2, 5, 6, 0, 0, 1, 2};
        int target1 = 0;
        System.out.println(solution.search(nums1, target1)); // Output: true

        int[] nums2 = {2, 5, 6, 0, 0, 1, 2};
        int target2 = 3;
        System.out.println(solution.search(nums2, target2)); // Output: false

        int[] nums3 = {4, 5, 6, 6, 7, 0, 1, 2, 4, 4};
        int target3 = 6;
        System.out.println(solution.search(nums3, target3)); // Output: true

        int[] nums4 = {1, 3, 1, 1, 1};
        int target4 = 3;
        System.out.println(solution.search(nums4, target4)); // Output: true

        int[] nums5 = {1, 3, 1, 1, 1};
        int target5 = 0;
        System.out.println(solution.search(nums5, target5)); // Output: false
    }
}
