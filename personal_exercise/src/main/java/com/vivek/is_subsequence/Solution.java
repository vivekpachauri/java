package com.vivek.is_subsequence;

public class Solution {
    /*
     * Given two strings s and t, return true if s is a subsequence of t, or false
     * otherwise.
     * 
     * A subsequence of a string is a new string that is formed from the original
     * string by deleting some (can be none) of the characters without disturbing
     * the relative positions of the remaining characters. (i.e., "ace" is a
     * subsequence of "abcde" while "aec" is not).
     * 
     * 
     * 
     * Example 1:
     * 
     * Input: s = "abc", t = "ahbgdc"
     * Output: true
     * Example 2:
     * 
     * Input: s = "axc", t = "ahbgdc"
     * Output: false
     * 
     * 
     * Constraints:
     * 
     * 0 <= s.length <= 100
     * 0 <= t.length <= 104
     * s and t consist only of lowercase English letters.
     * 
     * 
     * Follow up: Suppose there are lots of incoming s, say s1, s2, ..., sk where k
     * >= 109, and you want to check one by one to see if t has its subsequence. In
     * this scenario, how would you change your code?
     */
    public boolean isSubsequence(String s, String t) {
        /*
            create a index over t
         * iterate over s and try to find the next char in t which matches the current char in s, if match found then progress the both the iterators, else only
         * progress the iterator of t
         */
        int sIndex = 0, tIndex = 0;
        boolean matchFound = false;
        while ( sIndex < s.length()) {
            if ( tIndex >= t.length() ) {
                return false;
            }
            matchFound = s.charAt(sIndex) == t.charAt(tIndex) ? true : false;
            while (!matchFound) {
                tIndex++;
                if ( tIndex >= t.length() ) {
                    return false;
                }
                matchFound =  s.charAt(sIndex) == t.charAt(tIndex) ? true : false;
            }
            sIndex++;tIndex++;
        }
        return sIndex == s.length();
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        // Test case 1
        String s1 = "abc";
        String t1 = "ahbgdc";
        System.out.println(solution.isSubsequence(s1, t1)); // Output: true

        // Test case 2
        String s2 = "axc";
        String t2 = "ahbgdc";
        System.out.println(solution.isSubsequence(s2, t2)); // Output: false

        // Additional test cases
        String s3 = "";
        String t3 = "ahbgdc";
        System.out.println(solution.isSubsequence(s3, t3)); // Output: true

        String s4 = "abc";
        String t4 = "";
        System.out.println(solution.isSubsequence(s4, t4)); // Output: false

        String s5 = "ace";
        String t5 = "abcde";
        System.out.println(solution.isSubsequence(s5, t5)); // Output: true
    }
}
