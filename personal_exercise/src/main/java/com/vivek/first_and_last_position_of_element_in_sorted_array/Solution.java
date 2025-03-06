package com.vivek.first_and_last_position_of_element_in_sorted_array;

class Solution {
    public int[] searchRange(int[] nums, int target) {
        int start = 0;
        int end = nums.length - 1;
        int first = -1, last = -1;
        while ( start <= end ) {
            int mid = ((end - start) / 2) + start;
            if ( nums[mid] < target) {
                //if this is less then the target must be to the right
                
                start = mid + 1;
            }
            else if ( nums[mid] >= target) {
                if ( nums[mid] == target) {
                    first = mid;
                }
                end = mid - 1;
            }
            // else {
            //     //search to the left
            //     first = mid;
            //     end = mid;
            // }
        }
        // if ( start == end ) {
        //     first = start;
        // }

        start = 0;
        end = nums.length - 1;

        while ( start <= end ) {
            int mid = ((end - start) / 2) + start;
            if ( nums[mid] <= target) {
                //if this is less then the target must be to the right
                if ( nums[mid] == target ) {
                    last = mid;
                }
                start = mid + 1;
            }
            else if ( nums[mid] >= target) {
                end = mid - 1;
            }
            // else {
            //     //search to the right
            //     last = mid;
            //     start = mid + 1;
            // }
        }

        // if ( start == end ) {
        //     last = start - 1;
        // }
        return new int[] {first, last};
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        int[] arr = new int[]{5,7,6,7,8,8,10};
        // int[] arr = new int[]{8,8};
        for ( int i : s.searchRange(arr, 8) ) {
            System.out.print(i + " , ");
        }
    }
}
