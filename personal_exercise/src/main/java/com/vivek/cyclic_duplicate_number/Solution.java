package com.vivek.cyclic_duplicate_number;

class Solution {

    public int[] findMissingAndDuplicate(int[] nums) {
        //return array containing number which is missing and which is duplicate
        int[] result = new int[2];
        int index = 0;
        while ( index < nums.length ) {
            int correctPosition = nums[index] - 1;
            //0 index should contain 1, 1 index should contain 2 etc
            if ( nums[index] != nums[correctPosition] ) {
                int temp = nums[correctPosition];
                nums[correctPosition] = nums[index];
                nums[index] = temp;
            }
            else {
                index++;
            }
        }

        //scan and find the out of place value and it's index
        for ( int i = 0; i < nums.length; i++ ) {
            if ( nums[i] != (i+1) ) {
                result = new int[] {(i+1), nums[i]};
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        int[] arr = new int[] {5,6,6,1,2,3,4};
        for ( int i : s.findMissingAndDuplicate(arr) ) {
            System.out.print(i + " -> " );
        }
    }

    public int findDuplicate(int[] nums) {
        int result = 0;
        int index = 0;
        while ( index < nums.length ) {
            int correct = nums[index] - 1;
            if ( nums[index] != nums[correct] ) {
                int temp = nums[index];
                nums[index] = nums[correct];
                nums[correct] = temp;
            }
            else {
                if ( nums[index] != (index + 1) ) {
                    return nums[index];
                }
                // printArr(nums);
                // System.out.println("value " + nums[index] + " index " + index);
                // if ( (index + 1) != nums[index]) { 
                //     System.out.println("here");
                //     result[0] = nums[index];
                //     result[1] = (index + 1);
                //     return result;
                // }
                index++;
            }
        }
        //printArr(nums);
        // for ( int i = 0; i < nums.length; i++ ) {
        //     if ( nums[i] != (i + 1) ) {
        //         result[0] = nums[i];
        //         result[1] = (i + 1);
        //     }
        // }
        return result;
    
    }
}
