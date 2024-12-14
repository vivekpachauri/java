package com.vivek.rotate_array;

import java.util.Arrays;

public class Driver {

    public void rotate(int[] nums, int k) {
        if ( nums.length < k ) {
            rotateInPlace(nums, k);
        }
        else {
            rotateWithCopy(nums, k);
        }
        // rotateWithCopy(nums, k);
    }

    private void rotateInPlace(int[] nums, int k) {
        /*
        [1,2,3,4,5]       [1,2,3,4,5]    [1,2,3,4,5]
        [1,2,3,4,5], 3 -> [-,-,1,2,3] -> [4,-,1,2,3]
        [1,2], 3
         * iterative
         * keep swapping by 2,
         * will have to make the entire array rotate by k number
         * therefore outer loop can be 0 to k
         * inner loop will be 0 to size
         */
        for (int i = 0; i < k; i++ ) {
            int last = nums[nums.length-1];
            for (int j = nums.length - 1; j > 0; j-- ) {
                //rotate by 1, keep track of the last element and set the first to last
                // int tmp = nums[j-1];
                nums[j] = nums[j-1];
            }
            nums[0] = last;
        }
    }

    private void rotateWithCopy(int[] nums, int k) {
        k = k % nums.length;
        //if ( nums.length == 1 ) return;
        //let's try with creating a new copy
        //create an array of the same size
        int[] newCopy = Arrays.copyOf(nums, nums.length);//new int[nums.length];
        //copy the elements 
            //generate a stream for the core array
            //fill the new array 
        //copying
        //take the length - k elements and insert them in the new array starting from index k
        //so for array of size 5 [1,2,3,4,5] final result should be [5,4,1,2,3] with k = 2 the
        //new array should 5 - 2 = 3 initial elements and insert  them in the new array starting
        //at position k
        for ( int i = 0; i < nums.length - k; i++ ) {
            nums[i+k] = newCopy[i];
        }
        //now take the k+1 onwards indexess and insert them in the new array
//        if ( nums.length > k) {
            
            for ( int i = nums.length - k, j = 0; i < nums.length; i++, j++ ) {
                nums[j] = newCopy[i];
            }
        // }
        // else {
        //     for ( int i = 0; i < k; i++ ) {
        //         swap
        //     }
        // }
        //nums=newCopy;
        
    }

    public static void main(String[] args) {
        Driver driver = new Driver();
        int[] input = new int[] {1,2};
        driver.rotate(input, 3);
        for (int i : input) { System.out.print(i + ",");}
    }

}
