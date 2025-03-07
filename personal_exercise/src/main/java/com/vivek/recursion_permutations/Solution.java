package com.vivek.recursion_permutations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Solution {
    // public Set<String> permutations(String word) {
    //     /*
    //      * "abc" -> 
    //      * if length is n then n number of rec call need to be made
    //      */
    //     Set<String> result = permutationsRec(word);
    //     return result;

    // }

    public Set<String> permutations(String word) {
        //base case
        //if single character word then return single character word
        if ( word.length() == 1 ) {
            return Set.of(word);
        }
        else 
        {
            //add the first character before as a prefix and suffix to every word returned by rec call
            // String firstChar = word.substring(0,1);
            // String remaining = word.substring(1, word.length());
            
            //for every character in the word, we have to make sub calls without that character
            //in a for loop and extract each character and the remaining, for each remaining part of the word 
            //call the permutation function and append and prepend the remaining char

            Set<String> updated = new HashSet<>();
            for ( int i = 0; i < word.length(); i++ ) {
                String firstChar = word.substring(i, i+1);
                String remaining = word.substring(0, i) + word.substring(i+1, word.length());
                System.out.println("first char " + firstChar + " remaining " + remaining);
                Set<String> subResult = permutations(remaining);
                updated.addAll(subResult.stream().flatMap(e -> Arrays.<String>stream(new String[]{firstChar+e, e+firstChar})).collect(Collectors.toSet()));
            }

            for ( String s : updated ) {
                System.out.println(s);
            }
            return updated;
        }
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        String word = "abcd";
        Set<String> result = s.permutations("abcd");
        int factorial = 1;
        for ( int i = 0; i < word.length(); i++ ) {
            factorial *= (i+1);
        }
        assert(result.size() == factorial);
        System.out.println("result");
        for ( String r : result ) {
            System.out.println(r);
        }
    }
}
