package com.vivek.k_pairs_with_smallest_sums;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

import javax.swing.Painter;

public class Solution {

    /*
     * You are given two integer arrays nums1 and nums2 sorted in non-decreasing
     * order and an integer k.
     * 
     * Define a pair (u, v) which consists of one element from the first array and
     * one element from the second array.
     * 
     * Return the k pairs (u1, v1), (u2, v2), ..., (uk, vk) with the smallest sums.
     * 
     * 
     * 
     * Example 1:
     * 
     * Input: nums1 = [1,7,11], nums2 = [2,4,6], k = 3
     * Output: [[1,2],[1,4],[1,6]]
     * Explanation: The first 3 pairs are returned from the sequence:
     * [1,2],[1,4],[1,6],[7,2],[7,4],[11,2],[7,6],[11,4],[11,6]
     * Example 2:
     * 
     * Input: nums1 = [1,1,2], nums2 = [1,2,3], k = 2
     * Output: [[1,1],[1,1]]
     * Explanation: The first 2 pairs are returned from the sequence:
     * [1,1],[1,1],[1,2],[2,1],[1,2],[2,2],[1,3],[1,3],[2,3]
     * 
     * 
     * Constraints:
     * 
     * 1 <= nums1.length, nums2.length <= 105
     * -109 <= nums1[i], nums2[i] <= 109
     * nums1 and nums2 both are sorted in non-decreasing order.
     * 1 <= k <= 104
     * k <= nums1.length * nums2.length
     */

    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        // PriorityQueue<Integer> firstQueue = new PriorityQueue<>();
        // PriorityQueue<Integer> secondQueue = new PriorityQueue<>();
        // for ( int i = 0; i < nums1.length; i++ ) {
        //     firstQueue.add(nums1[i]);
        // }

        // for ( int i = 0; i < nums2.length; i++ ) {
        //     secondQueue.add(nums2[i]);
        // }

        // for ( int i = 0; i < k; i++ ) {
        //     int firstNum = firstQueue.poll();
        //     int secondNum = secondQueue.poll();
        //     //peek the next two smallest numers from both
        //     //add it to the result
        //     List<Integer> pair = new ArrayList<>(2);
        //     pair.add(firstNum);
        //     pair.add(secondNum);
        //     result.add(pair);
        //     //remove from whichever queue there is larger number in the next spot
        //     int nextFirst = firstQueue.peek();
        //     int nextSecond = secondQueue.peek();
        //     //if the sum of the next two is same then delete the bigger one
        //     if (firstNum + nextSecond == secondNum + nextFirst ) {
        //         if ( firstNum > secondNum ) {
        //             secondQueue.add(secondNum);
        //         }
        //         else {
        //             firstQueue.add(firstNum);
        //         }
        //     }
        //     else if ( firstNum + nextSecond < secondNum + nextFirst ) {
        //         firstQueue.add(firstNum);
        //     }
        //     else {
        //         secondQueue.add(secondNum);
        //     }

        // }

        //the two arrays are already sorted so we can use the for loop to get the smallest to largest values

        int pairsGenerated = 0;
        int nums1Index = nums1.length - 1;
        int nums2Index = nums2.length - 1;
        while ( pairsGenerated < k ) {
            //use two pointers in both lists
            int nums1Last = nums1[nums1Index];
            int nums1SecondLast = nums1[nums2Index-1];
            
            int nums2Last = nums2[nums2Index];
            int nums2SecondLast = nums2[nums2Index-1];

            //add the smallest pair to the list first
            List<Integer> pair = new ArrayList<>(2);
            pair.add(nums1Last);
            pair.add(nums2Last);
            result.add(pair);
            pairsGenerated++;
            if ( pairsGenerated >= k ) {
                break;
            }
            //then add the smaller of the two cross pair first
            if ( nums1Last + nums2SecondLast < nums2Last + nums1SecondLast) {
                List<Integer> firstCrossPair = new ArrayList<>(2);
                firstCrossPair.add(nums1Last);
                firstCrossPair.add(nums2SecondLast);
                result.add(firstCrossPair);
                pairsGenerated++;
                if ( pairsGenerated >= k ) {
                    break;
                }

                List<Integer> secondCrossPair = new ArrayList<>(2);
                secondCrossPair.add(nums1SecondLast);
                secondCrossPair.add(nums2Last);
                result.add(secondCrossPair);
                pairsGenerated++;
                if ( pairsGenerated >= k ) {
                    break;
                }
            }
            else {
                List<Integer> firstCrossPair = new ArrayList<>(2);
                firstCrossPair.add(nums1SecondLast);
                firstCrossPair.add(nums2Last);
                result.add(firstCrossPair);
                pairsGenerated++;
                if ( pairsGenerated >= k ) {
                    break;
                }

                List<Integer> secondCrossPair = new ArrayList<>(2);
                secondCrossPair.add(nums1Last);
                secondCrossPair.add(nums2SecondLast);
                result.add(secondCrossPair);
                pairsGenerated++;
                if ( pairsGenerated >= k ) {
                    break;
                }
            }
            //now add the bigger of the two cross pair

            //update the index
            nums1Index--;
            nums2Index--;
            
        }
        return result;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        int[] nums1 = new int[] { 4,3,2,1 };
        int[] nums2 = new int[] { 8,7,6,5 };
        for (List<Integer> pair : s.kSmallestPairs(nums1, nums2, 10)) {
            pair.stream().forEach(e -> System.out.print(e + " "));
            System.out.println();
        }
    }

}
