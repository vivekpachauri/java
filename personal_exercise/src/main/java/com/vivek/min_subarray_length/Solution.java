package com.vivek.min_subarray_length;

public class Solution {
    /*
     * Given an array of positive integers nums and a positive integer target,
     * return the minimal length of a subarray whose sum is greater than or equal to
     * target. If there is no such subarray, return 0 instead.
     * 
     * 
     * 
     * Example 1:
     * 
     * Input: target = 7, nums = [2,3,1,2,4,3]
     * Output: 2
     * Explanation: The subarray [4,3] has the minimal length under the problem
     * constraint.
     * Example 2:
     * 
     * Input: target = 4, nums = [1,4,4]
     * Output: 1
     * Example 3:
     * 
     * Input: target = 11, nums = [1,1,1,1,1,1,1,1]
     * Output: 0
     * 
     * 
     * Constraints:
     * 
     * 1 <= target <= 109
     * 1 <= nums.length <= 105
     * 1 <= nums[i] <= 104
     * 
     * 
     * Follow up: If you have figured out the O(n) solution, try coding another
     * solution of which the time complexity is O(n log(n))
     */

    public int minSubArrayLen(int target, int[] nums) {
        int beginning = 0, end = 0, length = 0, windowSum = 0, windowSize = 0, minWindowSize = -1;
        
        for ( int i = 0; i < nums.length; i++ ) {
            end = i;
            windowSum += nums[i];
            windowSize += 1;
            if ( windowSum < target ) {
                //sum of elements in window is not equal to target yet, increase the window size
                //extend end to this index
                // end = i;
                // windowSum += nums[i];
                // windowSize += 1;
                //window already extended as part of the default loop work, nothing to do here
                System.out.println(String.format("normal window increase to include value %d at index %d with current window size %d and window sum %d", nums[i], i, windowSize, windowSum));
            }
            // else if ( windowSum == target ) {
            //     System.out.println(String.format("found target at index %d with start %d and end %d", i, beginning, end));
            //     //if window sum matches target then store the current window size if it is useful
            //     if ( minWindowSize == -1 || (windowSize < minWindowSize) ) {
            //         minWindowSize = windowSize;
            //     }
            // }
            else {
                System.out.println(String.format("window sum too big at index %d beginning %d end %d and value %d", i, beginning, end, windowSum));
                //if the current window sum is greater than target then shrink the window until the window sum is less than target
                //while the window sum is greater than target, shift the window start to the right and reduce the window sum
                while ( windowSum >= target ) {
                                        
                    if ( minWindowSize == -1 || (windowSize < minWindowSize) ) {
                        minWindowSize = windowSize;
                    }
                    System.out.println(String.format("reducring value %d from window sum for beginning index %d", nums[beginning], beginning));
                    windowSum -= nums[beginning];
                    beginning++;
                    windowSize--;
                }
                // if ( windowSum == target ) {
                //     System.out.println("found correct target during shrinking");
                //     if ( minWindowSize == -1 || (windowSize < minWindowSize) ) {
                //         minWindowSize = windowSize;
                //     }
                // }
                System.out.println(String.format("after shrinking at index %d sum is %d with window beginning %d and ending %d and size %d", i, windowSum, beginning, end, windowSize));
            }
        }
        return (minWindowSize == -1) ? 0 : minWindowSize;
    }


     public static void main(String[] args ) {
        Solution s = new Solution();
        // int[] arr = new int[]{1,2,3,4,5};
        int[] arr = new int[]{2,3,1,2,4,3};
        System.out.println(s.minSubArrayLen(7, arr));

     }
}
