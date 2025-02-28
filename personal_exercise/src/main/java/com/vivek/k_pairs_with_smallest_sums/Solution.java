package com.vivek.k_pairs_with_smallest_sums;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

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
        PriorityQueue<QueueEntry> queue = new PriorityQueue<>();
        for ( int i = 0; i < nums1.length; i++ ) {
            for ( int j = 0; j < nums2.length; j++ ) {
                QueueEntry entry = new QueueEntry(nums1[i] + nums2[j], nums1[i], nums2[j]);
                queue.add(entry);
            }
        }
        for ( int i = 0; i < k; i++ ) {
            List<Integer> listEntry = new ArrayList<>(2);
            QueueEntry entry = queue.poll();
            listEntry.add(entry.pair.one);
            listEntry.add(entry.pair.two);
            result.add(listEntry);

        }
        return result;
    }

    private static class QueueEntry implements Comparable<QueueEntry>{
        public Integer total;
        public Pair pair;
        public QueueEntry(int sum, int value1, int value2 ) {
            Pair pair = new Pair(value1, value2);
            this.total = sum;
            this.pair = pair;
        }
        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + total;
            result = prime * result + ((pair == null) ? 0 : pair.hashCode());
            return result;
        }
        @Override
        public String toString() {
            return "QueueEntry [total=" + total + ", pair=" + pair + "]";
        }
        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            QueueEntry other = (QueueEntry) obj;
            if (total != other.total)
                return false;
            if (pair == null) {
                if (other.pair != null)
                    return false;
            } else if (!pair.equals(other.pair))
                return false;
            return true;
        }
        @Override
        public int compareTo(QueueEntry o) {
            // TODO Auto-generated method stub
            QueueEntry entry = (QueueEntry)o;
            return this.total.compareTo(entry.total);
        }

        
    }

    private static class Pair {
        public Integer one;
        @Override
        public String toString() {
            return "Pair [one=" + one + ", two=" + two + "]";
        }
        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + one;
            result = prime * result + two;
            return result;
        }
        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            Pair other = (Pair) obj;
            if (one != other.one)
                return false;
            if (two != other.two)
                return false;
            return true;
        }
        public Integer two;
        public Pair(int one, int two) {
            this.one = one;
            this.two = two;
        }
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
