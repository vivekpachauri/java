package com.vivek.h_index;

import java.util.HashMap;
import java.util.Map;

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
        return hIndexIterOnArray(citations);
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

    private int hIndexIterOnMap(int[] citations) {
        //example 3,0,5,1,6
        /*
         * create a map to keep track of values and the number of times they have been seen
         * add the first value to the map if the number of elements left is equal or less
         * than the value
         * input 3, 0, 5, 1, 6
         * {} 
         * first iter
         * 3, 0, 5, 1, 6
         * |
         * {
         *   "3" -> 1
         * }
         * 
         * second iter
         * if an element exist in the map then ignore 0
         * 3, 0, 5, 1, 6
         *    |
         * {
         *   "3" -> 1
         * }
         * 
         * third iter
         * 3, 0, 5, 1, 6
         *       |
         * {
         *   "3" -> 2,
         *   "5" -> 1
         * }
         * 
         * fourth iter
         * 3, 0, 5, 1, 6
         *          |
         * {
         *   "1" -> 1,
         *   "3" -> 2,
         *   "5" -> 1
         * }
         * 
         * fifth iter
         * 3, 0, 5, 1, 6
         *             |
         * {
         *   "1" -> 2,
         *   "3" -> 3,
         *   "5" -> 2,
         *   "6" -> 1
         * }
         * 
         * 
         * alternate input 1, 0, 5, 3, 6
         * {
         *   1 -> 4,
         *   2 -> 3,
         *   3 -> 3,
         *   4 -> 2,
         *   5 -> 2,
         *   6 -> 1
         * }
         * 
         */
        
        Map<Integer, Integer> seenMap = new HashMap<>();
        for ( int i = 0; i < citations.length; i++ ) {
            //insert 1 through citations[i] entries in the seen map
            for ( int j = 1; j <= citations[i]; j++ ) {
                seenMap.put(j, seenMap.getOrDefault(j, 0) + 1);
            }
        }
        
        int highestK = 0;
        int leastDiff = Integer.MAX_VALUE;
        for ( Map.Entry<Integer, Integer> entry : seenMap.entrySet() ) {
            System.out.println("key: " + entry.getKey() + " value: " + entry.getValue());
            int diff = entry.getValue() - entry.getKey();
            if (diff >= 0) {
                if (diff < leastDiff) {
                    leastDiff = diff;
                    highestK = entry.getKey();
                }
            }
        }

        //now iterate over the map entries in the descending order of keys and find the one
        //where the difference between key and value is least
        return highestK;
    }

    private int hIndexIterOnArray(int[] citations) {
        //example 3,0,5,1,6
        /*
         * create a map to keep track of values and the number of times they have been seen
         * add the first value to the map if the number of elements left is equal or less
         * than the value
         * input 3, 0, 5, 1, 6
         * {} 
         * first iter
         * 3, 0, 5, 1, 6
         * |
         * {
         *   "3" -> 1
         * }
         * 
         * second iter
         * if an element exist in the map then ignore 0
         * 3, 0, 5, 1, 6
         *    |
         * {
         *   "3" -> 1
         * }
         * 
         * third iter
         * 3, 0, 5, 1, 6
         *       |
         * {
         *   "3" -> 2,
         *   "5" -> 1
         * }
         * 
         * fourth iter
         * 3, 0, 5, 1, 6
         *          |
         * {
         *   "1" -> 1,
         *   "3" -> 2,
         *   "5" -> 1
         * }
         * 
         * fifth iter
         * 3, 0, 5, 1, 6
         *             |
         * {
         *   "1" -> 2,
         *   "3" -> 3,
         *   "5" -> 2,
         *   "6" -> 1
         * }
         * 
         * 
         * alternate input 1, 0, 5, 3, 6
         * {
         *   1 -> 4,
         *   2 -> 3,
         *   3 -> 3,
         *   4 -> 2,
         *   5 -> 2,
         *   6 -> 1
         * }
         * 
         */
        int maxValue = 0;
        for ( int i = 0; i < citations.length; i++ ) {
            if (citations[i] > maxValue )
                maxValue = citations[i];
        }
        int[] seenArray = new int[maxValue];
        for (int i = 0; i < maxValue; i++) {
            seenArray[i] = 0;
        }
        for ( int i = 0; i < citations.length; i++ ) {
            //insert 1 through citations[i] entries in the seen map
            for ( int j = 1; j <= citations[i]; j++ ) {
                seenArray[j-1] = seenArray[j-1] + 1;
            }
        }
        int highestKArray = 0;
        int leastDiffArray = Integer.MAX_VALUE;
        for (int i = 0; i < seenArray.length; i++ ) {
            System.out.println("array key: " + i + " array value: " + seenArray[i]);
            int diff = seenArray[i] - (i+1);
            if ( diff >= 0 ) {
                if ( diff < leastDiffArray) {
                    leastDiffArray = diff;
                    highestKArray = i+1;
                }
            }
        }

        //now iterate over the array entries in the descending order of keys and find the one
        //where the 
        return highestKArray;
    }

    public static void main(String[] args) {
        Driver driver = new Driver();
        int[] citations = {
        //{11,13};
            //3,0,6,1,5
            3,0,1,6,6,6,6,6,6,6
            //3,0
            //0
    };
        System.out.println(driver.hIndex(citations));
    }
}
