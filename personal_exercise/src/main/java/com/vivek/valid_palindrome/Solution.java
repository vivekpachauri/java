package com.vivek.valid_palindrome;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Solution {
    /*
     * A phrase is a palindrome if, after converting all uppercase letters into
     * lowercase letters and removing all non-alphanumeric characters, it reads the
     * same forward and backward. Alphanumeric characters include letters and
     * numbers.
     * 
     * Given a string s, return true if it is a palindrome, or false otherwise.
     * 
     * 
     * 
     * Example 1:
     * 
     * Input: s = "A man, a plan, a canal: Panama"
     * Output: true
     * Explanation: "amanaplanacanalpanama" is a palindrome.
     * Example 2:
     * 
     * Input: s = "race a car"
     * Output: false
     * Explanation: "raceacar" is not a palindrome.
     * Example 3:
     * 
     * Input: s = " "
     * Output: true
     * Explanation: s is an empty string "" after removing non-alphanumeric
     * characters.
     * Since an empty string reads the same forward and backward, it is a
     * palindrome.
     * 
     * 
     * Constraints:
     * 
     * 1 <= s.length <= 2 * 10^5
     * s consists only of printable ASCII characters.
     */
    public boolean isPalindrome(String s) {

        String trimmed = s.replaceAll("[^a-z^A-Z^0-9]", "").toLowerCase();
        return isPalindromeWithIter(trimmed);

    }

    private boolean isPalindromeWithIter(String s) {
                /*
         * keep one index at the first position and one index at the last position.
         * for each step keep checking the character and progressing the pointer, until the pointers meet or pass each other
         */
        int beginning = 0;
        int last = s.length() - 1;

        boolean isSame = true;
        while ( beginning < last ) {
            if ( s.charAt(beginning) != s.charAt(last) ) {
                isSame = false;
                break;
            }
            beginning++;
            last--;
        }
        return isSame;
    }

    private boolean isPalindromeWithStringReverse(String s) {
    
        List<String> reversed = Arrays.asList(s);
        Collections.reverse(reversed);
        return reversed.equals(Arrays.asList(s));
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        
        String test1 = "A man, a plan, a canal: Panama";
        System.out.println("Test case 1: " + solution.isPalindrome(test1)); // Output: true
        
        String test2 = "race a car";
        System.out.println("Test case 2: " + solution.isPalindrome(test2)); // Output: false
        
        String test3 = "0P";
        System.out.println("Test case 3: " + solution.isPalindrome(test3)); // Output: true
    }
}
