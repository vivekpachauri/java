package com.vivek.rotate_array;

public class Driver {

    public void rotate(int[] nums, int k) {
        /*
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

    public static void main(String[] args) {
        Driver driver = new Driver();
        int[] input = new int[] {-1,-100,3,99};
        driver.rotate(input, 2);
        for (int i : input) { System.out.print(i + ",");}
    }

}
