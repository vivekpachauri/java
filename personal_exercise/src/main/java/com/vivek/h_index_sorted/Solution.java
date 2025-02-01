package com.vivek.h_index_sorted;

class Solution {
    /*
     * Given an array of integers citations where citations[i] is the number of
     * citations a researcher received for their ith paper and citations is sorted
     * in ascending order, return the researcher's h-index.
     * 
     * According to the definition of h-index on Wikipedia: The h-index is defined
     * as the maximum value of h such that the given researcher has published at
     * least h papers that have each been cited at least h times.
     * 
     * You must write an algorithm that runs in logarithmic time.
     * 
     * 
     * 
     * Example 1:
     * 
     * Input: citations = [0,1,3,5,6]
     * Output: 3
     * Explanation: [0,1,3,5,6] means the researcher has 5 papers in total and each
     * of them had received 0, 1, 3, 5, 6 citations respectively.
     * Since the researcher has 3 papers with at least 3 citations each and the
     * remaining two with no more than 3 citations each, their h-index is 3.
     * 
     * Example 2:
     * 
     * Input: citations = [1,2,100]
     * Output: 2
     * 
     * Example 3:
     * 
     * Input: citations = [0,1,2,5,6]
     * Output: 2
     * 
     * Example 4:
     * 
     * Input: citations = [0,4,5,5,6]
     * Output: 4
     * 
     * Constraints:
     * 
     * n == citations.length
     * 1 <= n <= 105
     * 0 <= citations[i] <= 1000
     * citations is sorted in ascending order.
     */
    public int hIndex(int[] citations) {
        /*
         * we have the length of the array indicating the number of publications,
         * we want to find the largest sub-array such that every value in that array is
         * greater or equal to
         * the size of the array
         */
        // return hIndexRec(citations, 0, citations.length-1, false, 0,0);
        return hIndexIter(citations);

    }

    private int hIndexRec(int[] citations, int beginning, int end, boolean wasPreviousValid, int previousMid, int adder) {
        int retVal;
        if (beginning == end) {
            retVal = citations[beginning] > 0 ? 1 : 0;
            return retVal;
        }
        int mid = ((end - beginning) / 2) + beginning;
        //mid = (mid == beginning) ? mid + 1 : mid;
        int diff = citations[mid] - (mid + 1);
        // example 1 [1,2,3,4,5] 3 - [3]
        // example 2 [1,2,2,4,5] 2 - [3]
        // example 3 [1,2,4,4,5] 4 - [3]
        // example 4 [1,4,4,4,5] 4 - [3]

        // if the value is zero this is the best case scenario, we cannot find a
        // sub-array bigger than
        // this, return this size

        // if the value is positive then maybe we can find a bigger sub-array so try it
        // on the left size of this
        // mid point

        // if the value is negative then perhaps a smaller sub-array is possible so try
        // it on the right side, unless we previously found a valid value
        // of this mid point
        if ( diff == 0 ) {
            return mid + 1 + adder; 
        }
        else if (diff > 0) {
            return hIndexRec(citations, beginning, mid, true, mid + 1 + adder, mid);
        }
        // diff less than 0
        else {
            if ( wasPreviousValid ) {
                return previousMid;
            }
            else {
                return hIndexRec(citations, mid, end, false, mid, -mid);
            }
        }
        // return mid + 1;
    }

    private int hIndexIter(int[] citations) {
        /*
        input array is sorted
        I have to find the leftmost index such that 
          - every element in that array is greater or equal to the size of the sub-array

        - we need to know the size of the full array we are working with so that at each step we know what is the preferred size of the sub array we wish to have
        - 
        starting point is the mid of the array, if that value is exactly same as half the size of the array then anything to the left will be 
        equal or less in value, so best case scenario will be the mid point being the size we select and return

        if the element value is less than the index at mid point then maybe a sub-array to the right will be valid (smaller in size than half the full array size)

        if the element value is greater then the index at mid point then possibly a sub-array to the left will be valid(larger in size than half the full arrry size)


        */
        /*
         * corner case
         */
        if (citations.length == 1) {
            if ( citations[0] == 0 ) {
                return 0;
            }
            else {
                return 1;
            }
        }
        int size = citations.length;
        int start = 0;
        int end = size - 1;
        int mid = 0;
        // boolean bestFound = false;
        //int bestSize = citations[0] > 0 ? 1 : 0;
        int validSizeFound = 0;
        int diff = 0;

        do {
            size = (end - start) + 1;
            mid = start + ((end + 1) - start)/2;
            //int half = (size % 2 == 0 ) ? size / 2 : (size / 2) + 1;
            int half = (size % 2 == 0) ? (size / 2) : (size / 2 ) + 1;
            diff = citations[mid] - (half + validSizeFound);
            if ( diff >= 0 ) {
                // bestFound = true;
                //if even the increase the valid size found by half, if odd then by half + 1
                //((end - start) % 2 != 0 ? (end - start) / 2 : ((end - start) / 2) + 1);
                validSizeFound = validSizeFound + half;
                //keep track of the size we found and then check the left size of the array to see if we can make the size bigger
                //end = (mid % 2 == 0) ? mid - 1 : mid;
                end = mid - 1;
            }
            else {
                //if diff is less than 0 then the existing best size could not be make bigger by this half sub-array, check to the right
                start = mid + 1;
            }
        } while ( (start <= end) );

        return validSizeFound;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        
        int[] citations1 = {0, 1, 3, 5, 6};
        System.out.println("Test case 1: " + solution.hIndex(citations1)); // Output: 3
        
        int[] citations2 = {1, 2, 100};
        System.out.println("Test case 2: " + solution.hIndex(citations2)); // Output: 2
        
        int[] citations3 = {0, 1, 2, 5, 6};
        System.out.println("Test case 3: " + solution.hIndex(citations3)); // Output: 2
        
        int[] citations4 = {0, 4, 5, 5, 6};
        System.out.println("Test case 4: " + solution.hIndex(citations4)); // Output: 4
        
        int[] citations5 = {2, 2};
        System.out.println("Test case 5: " + solution.hIndex(citations5)); // Output: 0

        int[] citations6 = {4,4,4,4};
        System.out.println("Test case 6: " + solution.hIndex(citations6)); // Output: 4

        int[] citations7 = {1,1,1,1};
        System.out.println("Test case 7: " + solution.hIndex(citations7)); // Output: 1
    }
}