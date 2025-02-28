package com.vivek.contiguous_array;

class Solution {
    /*
     * Given a binary array nums, return the maximum length of a contiguous subarray
     * with an equal number of 0 and 1.
     * 
     * 
     * 
     * Example 1:
     * 
     * Input: nums = [0,1]
     * Output: 2
     * Explanation: [0, 1] is the longest contiguous subarray with an equal number
     * of 0 and 1.
     * Example 2:
     * 
     * Input: nums = [0,1,0]
     * Output: 2
     * Explanation: [0, 1] (or [1, 0]) is a longest contiguous subarray with equal
     * number of 0 and 1.
     * 
     * [0,1,0,0,1,0,0,0,0] -> 4
     * [1,1,2,3,3,4,5,6,7]
     * [0,1,1,1,2,2,2,2,2]
     * [0,2,2,2,4,4,4,4,4]
     * 
     * [1,0,0,0,0,0,0,0,1] -> 4
     * [0,1,2,3,4,5,6,7,7]
     * [1,1,1,1,1,1,1,1,2]
     * [0,2,2,2,4,4,4,4,4]
     * 
     * [1,0,0,0,1,0,0,0,1] -> 2
     * [0,1,2,3,3,4,5,6,6]
     * [1,1,1,1,2,2,2,2,3]
     * [0,2,2,2,4,4,4,4,4]
     * 
     * [0,0,0,0,1,1,0,0,1] -> 6
     * [1,2,3,4,4,4,5,6,6]
     * [0,0,0,0,1,2,2,2,3]
     * [0,0,0,0,2,4,4,4,6]
     * 
     * 
     * {0, 0, 0, 0, 1, 1, 0, 0, 1}
     * [1, 2, 3, 4, 4, 4, 5, 6, 6]
     * [0, 0, 0, 0, 1, 2, 2, 2, 3]
     * 
     * {1, 0, 0, 0, 1, 1, 0, 0, 0}
     * [0, 1, 2, 3, 3, 3, 4, 5, 6]
     * [1, 1, 1, 1, 2, 3, 3, 3, 3]
     * 
     * [0,1,1,0,1,1,1,0]
     * [1,1,1,2,2,2,2,3]
     * [0,1,2,2,3,4,5,5]
     * 
     * [1,0,0,1,0,0,0,1]
     * [0,1,2,2,3,4,5,5]
     * [1,1,1,2,2,2,2,3]

     * ? distance between 0s won't work because of wrapping
     * ? check for difference in some way
     * 
     * 
     * Constraints:
     * 
     * 1 <= nums.length <= 105
     * nums[i] is either 0 or 1.
     */

    public int findMaxLength(int[] nums) {
        /*
         * let's try and come up with a few examples to see if this is a straight forward linear scan problem or not
         * 
         * options
         * - keep a count of the number of zeros and 1s seen so far and whenever the number is equal we update the count of the size
         */
        // int count0 = 0, count1 = 0;
        int maxSubarraySize = 0;
        int[] count0arr = new int[nums.length];
        int[] count1arr = new int[nums.length];
        int count0 = 0, count1 = 0;
        for (int i = 0; i < nums.length; i++ ) {
            if (nums[i] == 0) {
                count0++;
                // count0arr[i] = count0;
            }
            else {
                count1++;
                // count1arr[i] = count1;
            }
            count0arr[i] = count0;
            count1arr[i] = count1;
        }

        for ( int i = 0; i < nums.length; i++ ) {
            //check the count of number of 1s
            //if the number of 1s seen so far is greater or equal than the number of 0s seen then the sub-array size could be increased
        }
//        maxSubarraySize = count0arr[nums.length-1] < count1arr[nums.length-1] ? 2 * count0arr[nums.length-1] : count1arr[nums.length-1] * 2;
        return maxSubarraySize;
    }
    public static void main(String[] args) {
        Solution solution = new Solution();
        
        
        int[] nums1 = {0, 1};
        System.out.println("Example 1: " + solution.findMaxLength(nums1)); // Output: 2

        int[] nums2 = {0, 1, 0};
        System.out.println("Example 2: " + solution.findMaxLength(nums2)); // Output: 2

        int[] nums3 = {0, 1, 0, 0, 1, 0, 0, 0, 0};
        System.out.println("Example 3: " + solution.findMaxLength(nums3)); // Output: 4

        int[] nums4 = {0, 0, 0, 0, 1, 1, 0, 0, 1};
        System.out.println("Example 4: " + solution.findMaxLength(nums4)); // Output: 6
    
        int[] nums5 = {0,1,1,0,1,1,1,0};
        System.out.println("Example 5: " + solution.findMaxLength(nums5)); // Output: 4
    }


}
