package com.vivek.optimal_string_partition;

import java.util.HashSet;
import java.util.Set;

class Solution {
    /*
     * Given a string s, partition the string into one or more substrings such that
     * the characters in each substring are unique. That is, no letter appears in a
     * single substring more than once.
     * 
     * Return the minimum number of substrings in such a partition.
     * 
     * Note that each character should belong to exactly one substring in a
     * partition.
     * 
     * 
     * 
     * Example 1:
     * 
     * Input: s = "abacaba"
     * Output: 4
     * Explanation:
     * Two possible partitions are ("a","ba","cab","a") and ("ab","a","ca","ba").
     * It can be shown that 4 is the minimum number of substrings needed.
     * Example 2:
     * 
     * Input: s = "ssssss"
     * Output: 6
     * Explanation:
     * The only valid partition is ("s","s","s","s","s","s").
     * 
     * 
     * Constraints:
     * 
     * 1 <= s.length <= 105
     * s consists of only English lowercase letters.
     */
    public int partitionString(String s) {
        return partitionStringGreedy(s);
    }

    private int partitionStringGreedy(String s) {
        /*
         * start with the beginning
         * create a split and 
         */
        String[] initialSplit = this.createSplit(s);
        String secondPart = initialSplit[1];
        int numSplits = 1;
        while (secondPart.length() > 0) {
            secondPart = this.createSplit(secondPart)[1];
            numSplits++;
        }
        return numSplits;
    }

    private int partitionStringRec(String s) {
        if (s.length() == 0 ) {
            return 0;
        }
        else if (s.length() == 1 ) {
            return 1;
        }
        int countWithOneRemoved = 1 + partitionStringRec(s.substring(1));
        int countWithSplit = 1 + partitionStringRec(createSplit(s)[1]);
        return (countWithOneRemoved < countWithSplit) ? ( countWithOneRemoved) : ( countWithSplit);
    }

    private String[] createSplit(String input) {
        //split the input into two string, s1 and s2, such that no characters in s1 are duplicate
        Set<Character> seen = new HashSet<>();
        seen.add(input.charAt(0));
        int startingIndex = 1;
        for ( int i = 1; i < input.length(); i++ ) {
            if (seen.contains(input.charAt(i))) {
                return new String[] {input.substring(0, i), input.substring(i, input.length())};
            }
            else {
                seen.add(input.charAt(i));
                startingIndex++;
            }
        }
        return new String[] {input.substring(  0, startingIndex), input.substring(startingIndex, input.length())};
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.partitionString("lvkmzlaeaxbprczpfarnlaptfvmutkfsatyywnxpmkpduwoqeeiltbdjipwihqi")); // Example usage
    }
}
