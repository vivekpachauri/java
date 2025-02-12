package com.vivek.range_sum_query;

class NumArray {

    /*
     * Given an integer array nums, handle multiple queries of the following type:
     * 
     * Calculate the sum of the elements of nums between indices left and right
     * inclusive where left <= right.
     * Implement the NumArray class:
     * 
     * NumArray(int[] nums) Initializes the object with the integer array nums.
     * int sumRange(int left, int right) Returns the sum of the elements of nums
     * between indices left and right inclusive (i.e. nums[left] + nums[left + 1] +
     * ... + nums[right]).
     * 
     * 
     * Example 1:
     * 
     * Input
     * ["NumArray", "sumRange", "sumRange", "sumRange"]
     * [[[-2, 0, 3, -5, 2, -1]], [0, 2], [2, 5], [0, 5]]
     * Output
     * [null, 1, -1, -3]
     * 
     * Explanation
     * NumArray numArray = new NumArray([-2, 0, 3, -5, 2, -1]);
     * numArray.sumRange(0, 2); // return (-2) + 0 + 3 = 1
     * numArray.sumRange(2, 5); // return 3 + (-5) + 2 + (-1) = -1
     * numArray.sumRange(0, 5); // return (-2) + 0 + 3 + (-5) + 2 + (-1) = -3
     * 
     * 
     * Constraints:
     * 
     * 1 <= nums.length <= 104
     * -105 <= nums[i] <= 105
     * 0 <= left <= right < nums.length
     * At most 104 calls will be made to sumRange.
     */
    private int[] nums;
    public NumArray(int[] nums) {
        this.nums = new int[nums.length];
        this.nums[0] = nums[0];
        for ( int i = 1; i < nums.length; i++ ) {
            this.nums[i] = nums[i] + this.nums[i-1];
        }

    }

    public int sumRange(int left, int right) {
        if ( left == 0 ) {
            return this.nums[right];
            
        }
        return this.nums[right] - this.nums[left-1];
    }
    public static void main(String[] args) {
        int[] nums = {-2, 0, 3, -5, 2, -1};
        NumArray numArray = new NumArray(nums);
        
        System.out.println(numArray.sumRange(0, 2)); // Output: 1
        System.out.println(numArray.sumRange(2, 5)); // Output: -1
        System.out.println(numArray.sumRange(0, 5)); // Output: -3

        System.out.println(numArray.sumRange(1, 3)); // Output: -2
    }
}

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * int param_1 = obj.sumRange(left,right);
 */
