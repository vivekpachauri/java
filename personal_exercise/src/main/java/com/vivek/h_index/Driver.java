package com.vivek.h_index;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MaximizeAction;

/*
Given an array of integers citations where citations[i] is the number of citations a researcher received 
for their ith paper, return the researcher's h-index.

According to the definition of h-index on Wikipedia: The h-index is defined as the maximum value of h 
such that the given researcher has published at least h papers that have each been cited at least h times.

Example 1:

Input: citations = [3,0,6,1,5]
Output: 3
Explanation: [3,0,6,1,5] means the researcher has 5 papers in total and each of them had received 3, 0, 6, 1, 5 citations respectively.
Since the researcher has 3 papers with at least 3 citations each and the remaining two with no more than 3 citations each, their h-index is 3.
Example 2:

Input: citations = [1,3,1]
Output: 1
 

Constraints:

n == citations.length
1 <= n <= 5000
0 <= citations[i] <= 1000
 */
public class Driver {
    public int hIndex(int[] citations) {
        return hIndexIterONSquared(citations);
    }

    private int hIndexIterONSquared(int[] citations) {
        if ( citations.length == 1 ) {
            return citations[0] > 0 ? 1 : 0;
        }
        int maxHIndex = 0;
        for ( int i = 0; i < citations.length; i++ ) {
            citations[i] = Math.min(citations.length, citations[i]);
        }
        for ( int i = 0; i < citations.length; i++ ) {
            int currVal = citations[i];
            int numValuesGreaterOrEqualToCurr = 0;
            for ( int j = 0; j < citations.length; j++ ) {
                if (citations[j] >= currVal) {
                    numValuesGreaterOrEqualToCurr++;
                }
            }
            currVal = Math.min(currVal, numValuesGreaterOrEqualToCurr);
            maxHIndex = Math.max(currVal, maxHIndex);
        }
        return maxHIndex;
    }

    private int hIndexIterOn(int[] citations) {
        //example 3,0,5,1,6
        /*
         * loop from 0 to length
         * before the loop being upper bound is length and lower bound is 0
         * read the value in the first array element
         * the upper bound gets reduced by len - (number of elements read) = 5 - 1 = 4
         * lower bound is the value of array element = 3
         * 
         * next loop the 
         * 
         */
        return 0;
    }

    public static void main(String[] args) {
        Driver driver = new Driver();
        int[] citations = {
        //{11,13};
            3,0,6,1,5
            //3,0,1,6,6,6,6,6,6,6
            //3,0
    };
        System.out.println(driver.hIndex(citations));
    }
}
