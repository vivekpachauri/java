package com.vivek.find_numbers_disappeared_in_array;

import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<Integer> findDisappearedNumbers(int[] nums) {
        // int max = 0;
        // for ( int i = 0; i < nums.length; i++ ) {
        //     if (nums[i] > max) {
        //         max = nums[i];
        //     }
        // }
        // int[] retVal = new int[]
        // int retVal = nums.length;
        List<Integer> retVal = new ArrayList<>();
        int index = 0;
        while ( index < nums.length ) {
            int correctLocation = nums[index]-1;
            if ( /*(nums[index] < (nums.length -1)) && */nums[index] != nums[correctLocation] ) {
                int temp = nums[index];
                nums[index] = nums[correctLocation];
                nums[correctLocation] = temp;
            }
            else {
                index++;
            }
        }
        printArr(nums);
        for ( int i = 0; i < nums.length; i++ ) {
            //System.out.println("comparing " + nums[i] + " and " + i);
            if (nums[i] != (i+1)) {
                //System.out.println("not equal, return " + i);
                retVal.add(i+1);
            }
        }
        return retVal;
    }

    private void printArr(int[] nums) {
        for ( int i : nums ) {
            System.out.print(i + " -> ");
        }
        System.out.println();
    }
}
