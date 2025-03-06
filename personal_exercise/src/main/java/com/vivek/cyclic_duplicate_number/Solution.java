package com.vivek.cyclic_duplicate_number;

class Solution {
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
