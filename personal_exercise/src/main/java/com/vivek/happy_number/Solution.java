package com.vivek.happy_number;

import java.util.HashSet;
import java.util.Set;

class Solution {
    public boolean isHappy(int n) {
        Set<Integer> numbersSeen = new HashSet<>();
        
        int[] tokenized = tokenize(n);
        int sum = 0;
        boolean seenAlready = false;
        do {
            sum = 0;
            for ( int i = 0; i < tokenized.length; i++ ) {
                sum += tokenized[i];
            }
            seenAlready = numbersSeen.contains(sum);
            if ( seenAlready ) {
                return false;
            }
            numbersSeen.add(sum);
            tokenized = tokenize(sum);
        } while ( (sum != 1) );
        return true;

    }

    private int[] tokenize(int n) {
        char[] charArray = String.valueOf(n).toCharArray();
        int[] intArray = new int[charArray.length];
        for ( int i = 0; i < charArray.length; i++ ) {
            intArray[i] = Integer.parseInt(String.valueOf(charArray[i]));
        }
        return intArray;
    }
public static void main(String[] args) {
    Solution solution = new Solution();
    
    // Test cases
    int[] testCases = {19, 2, 7, 28, 100};
    for (int testCase : testCases) {
        System.out.println("Is " + testCase + " a happy number? " + solution.isHappy(testCase));
    }
}
}
