package com.vivek.jump_game_one;

import java.util.Arrays;

public class Driver {
/*
You are given an integer array nums. You are initially positioned at the array's first index, and each element in the array represents your maximum jump length 
at that position.

Return true if you can reach the last index, or false otherwise.

 

Example 1:

Input: nums = [2,3,1,1,4]
Output: true
Explanation: Jump 1 step from index 0 to 1, then 3 steps to the last index.
Example 2:

Input: nums = [3,2,1,0,4]
Output: false
Explanation: You will always arrive at index 3 no matter what. Its maximum jump length is 0, which makes it impossible to reach the last index.
 

Constraints:

1 <= nums.length <= 104
0 <= nums[i] <= 105

Strategy
base case (positive)
case 1
if there is a single element in the array then we return true
[1] or [0]
base case negative
if the value at index 0 is zero and there are more than one element in the array then we return false


[1,2,3] -> [2,3]
[3,2,1] -> [[2,1], [1]]
[3,1,0,1,0,2,1,0] -> [[1,0,1,0,2,1,0], [0,1,0,2,1,0], [1,0,2,1,0]]
value at index 0 will determine how many recursive calls to be made
 */

 public boolean canJump(int[] nums) {
    return canJumpRec(nums, false);
 }

 private boolean canJumpRec(int[] nums, boolean targetReached) {
    if ( nums.length == 1 ) return true;
    else if ( nums[0] >= nums.length - 1 ) return true;
    else {
        boolean[] results = new boolean[nums[0]];
        for ( int i = 0; i < nums[0]; i++ ) {
            results[i] = canJumpRec(Arrays.copyOfRange(nums, i+1, nums.length), false);
        }
        boolean retVal = false;
        for ( boolean result : results ) {
            retVal = retVal || result;
        }
        return retVal;
    }
 }

public static void main(String[] args) {
    Driver driver = new Driver();
    int[] jumps = new int[]{3,2,1,0,4};
    System.out.println("result -> " + driver.canJump(jumps));
}
}
