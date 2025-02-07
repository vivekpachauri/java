package com.vivek.container_with_most_water;

class Solution {
    /*
     * You are given an integer array height of length n. There are n vertical lines
     * drawn such that the two endpoints of the ith line are (i, 0) and (i,
     * height[i]).
     * 
     * Find two lines that together with the x-axis form a container, such that the
     * container contains the most water.
     * 
     * Return the maximum amount of water a container can store.
     * 
     * Notice that you may not slant the container.
     * 
     * 
     * 
     * Example 1:
     * 
     * 
     * Input: height = [1,8,6,2,5,4,8,3,7]
     * Output: 49
     * Explanation: The above vertical lines are represented by array
     * [1,8,6,2,5,4,8,3,7]. In this case, the max area of water (blue section) the
     * container can contain is 49.
     * Example 2:
     * 
     * Input: height = [1,1]
     * Output: 1
     * 
     * 
     * Constraints:
     * 
     * n == height.length
     * 2 <= n <= 105
     * 0 <= height[i] <= 104
     */
    public int maxAreaInvalid(int[] height) {
        /*
         * iterate through once and keep track of the max water that could be stored
         * between current and the longest previous height
         */
        int tallest = height[0];
        int indexTallest = 0;
        int maxWater = 0;
        for (int i = 1; i < height.length; i++) {
            // we need to keep track of the previous tallest and the max water so far
            int currentWater = height[i] < tallest ? ((i - indexTallest) * height[i]) : ((i - indexTallest) * tallest);
            if (currentWater > maxWater) {
                maxWater = currentWater;
            }
            if (height[i] > tallest) {
                tallest = height[i];
                indexTallest = i;
            }
        }
        return maxWater;
    }

    public int maxArea(int[] height) {
        /*
         * start with full container
         */
        int leftIndex = 0;
        int rightIndex = height.length - 1;
        int leftHeight = height[leftIndex];
        int rightHeight = height[rightIndex];
        int maxVolume = leftHeight < rightHeight ? (rightIndex - leftIndex) * (leftHeight) : (rightIndex - leftIndex) * rightHeight;
        //so that is the max volume of the full container

        //now start to shrink the size of the container per loop until the width of the container is > 0 (rightIndex - leftIndex > 0)
        while ( rightIndex - leftIndex > 0 ) {
            //bring either right index to one left or left index to one right depending on which one is shorter, if both the same the move the left one
            //to the right
            if (height[leftIndex] > height[rightIndex] ) {
                //left is taller, so move the right one
                rightIndex--;
            }
            else {
                leftIndex++;
            }

            //re-check the volume and if the volume is greater than current max volume then update it
            leftHeight = height[leftIndex];
            rightHeight = height[rightIndex];
            int newVolume = leftHeight < rightHeight ? (rightIndex - leftIndex) * (leftHeight) : (rightIndex - leftIndex) * rightHeight;
            if ( newVolume > maxVolume ) {
                maxVolume = newVolume;
            }
        }
        return maxVolume;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] heights1 = { 1, 8, 6, 2, 5, 4, 8, 3, 7 };
        int[] heights2 = { 1, 1 };
        int[] heights3 = { 4, 3, 2, 1, 4 };
        int[] heights4 = { 1, 2, 1 };
        int[] heights5 = { 1, 2, 3, 1 };
        int[] heights6 = { 1, 9, 9, 1 };
        int[] heights7 = { 3, 4, 1, 2, 2, 4, 1, 3, 2 };

        System.out.println("Max area for heights1: " + solution.maxArea(heights1)); // Expected: 49
        System.out.println("Max area for heights2: " + solution.maxArea(heights2)); // Expected: 1
        System.out.println("Max area for heights3: " + solution.maxArea(heights3)); // Expected: 16
        System.out.println("Max area for heights4: " + solution.maxArea(heights4)); // Expected: 2
        System.out.println("Max area for heights5: " + solution.maxArea(heights5)); // Expected: 3
        System.out.println("Max area for heights6: " + solution.maxArea(heights6)); // Expected: 8
        System.out.println("Max area for heights7: " + solution.maxArea(heights7)); // Expected: 21
    }
}
