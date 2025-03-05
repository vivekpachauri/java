package com.vivek.merge_intervals;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Solution {

    /*
     * Given an array of intervals where intervals[i] = [starti, endi], merge all
     * overlapping intervals, and return an array of the non-overlapping intervals
     * that cover all the intervals in the input.
     * 
     * 
     * 
     * Example 1:
     * 
     * Input: intervals = [[1,3],[2,6],[8,10],[15,18]]
     * Output: [[1,6],[8,10],[15,18]]
     * Explanation: Since intervals [1,3] and [2,6] overlap, merge them into [1,6].
     * Example 2:
     * 
     * Input: intervals = [[1,4],[4,5]]
     * Output: [[1,5]]
     * Explanation: Intervals [1,4] and [4,5] are considered overlapping.
     * 
     * 
     * Constraints:
     * 
     * 1 <= intervals.length <= 104
     * intervals[i].length == 2
     * 0 <= starti <= endi <= 104
     */
    public int[][] merge(int[][] intervals) {
        //sort them first
        Arrays.sort(intervals, (a, b) -> {
            Integer first = a[0];
            Integer second = b[0];
            return first != second ? first.compareTo(second) : ((Integer)a[1]).compareTo((Integer)b[1]);
        });
        Map<Integer, Integer> previousIndexMap = new HashMap<>();
        //iterate over all the intervals
        for ( int i = 1; i < intervals.length; i++ ) {
            //find the index of the actual previous interval
            int previousIndex = previousIndexMap.containsKey(i-1) ? previousIndexMap.get(i-1) : (i - 1);
            //check if the beginning of this is at or after the end of the previous
            if ( intervals[i][0] <= intervals[previousIndex][1] ) {
                //merge
                intervals[previousIndex][1] = Math.max(intervals[i][1], intervals[previousIndex][1]);
                //set the begining of this to 0 to indicate that this got merged
                intervals[i][0] = -1;
                //set the previous index of this to the correct previous
                previousIndexMap.put(i, previousIndex);
            }
        }

        //now count how many are left and populate the return array
        int intervalsLeft = 0;
        for ( int i = 0; i < intervals.length; i++ ) {
            if ( intervals[i][0] != -1 ) {
                intervalsLeft++;
            }
        }
        int[][] result = new int[intervalsLeft][2];
        int resultIndex = 0;
        for ( int i = 0; i < intervals.length; i++ ) {
            if ( intervals[i][0] != -1 ) {
                result[resultIndex][0] = intervals[i][0];
                result[resultIndex][1] = intervals[i][1];
                resultIndex++;
            }
        }

        return result;
    }


    public static void main(String[] args) {
        // int[][] arr = new int[][]{{6,7}, {4,5}, {2,3}, {1,2}};
        // int[][] arr = new int[][]{{6,7}, {2,4}, {2,3}, {1,2}};
        int[][] arr = new int[][]{{1,4}, {2,3}};
        Solution s = new Solution();
        printArray(s.merge(arr));
    }

    public static void printArray(int[][] arr) {
        for ( int i = 0; i < arr.length; i++ ) {
            System.out.print(arr[i][0] + ", " + arr[i][1] + "->");
        }
    }
}
