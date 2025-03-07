package com.vivek.rec_letter_combinations_of_phone_number;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Solution {

    public List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<>();
        if ( (digits == null) || (digits.isEmpty()) || (digits.equals("1")) ) {
            return result;
        }
        HashMap<String, List<String>> charMappings = new HashMap<>();
        List<String> list2 = new ArrayList<>(3);
        list2.add("a");
        list2.add("b");
        list2.add("c");

        List<String> list3 = new ArrayList<>(3);
        list3.add("d");
        list3.add("e");
        list3.add("f");

        List<String> list4 = new ArrayList<>(3);
        list4.add("g");
        list4.add("h");
        list4.add("i");

        List<String> list5 = new ArrayList<>(3);
        list5.add("j");
        list5.add("k");
        list5.add("l");

        List<String> list6 = new ArrayList<>(3);
        list6.add("m");
        list6.add("n");
        list6.add("o");

        List<String> list7 = new ArrayList<>(3);
        list7.add("p");
        list7.add("q");
        list7.add("r");
        list7.add("s");

        List<String> list8 = new ArrayList<>(3);
        list8.add("t");
        list8.add("u");
        list8.add("v");

        List<String> list9 = new ArrayList<>(3);
        list9.add("w");
        list9.add("x");
        list9.add("y");
        list9.add("z");

        charMappings.put("2", list2);
        charMappings.put("3", list3);
        charMappings.put("4", list4);
        charMappings.put("5", list5);
        charMappings.put("6", list6);
        charMappings.put("7", list7);
        charMappings.put("8", list8);
        charMappings.put("9", list9);

        // String[][] charMappings = new String[8][3];
        // char startingChar = 'a';
        // for ( int i = 0; i < 8; i++ ) {
        //     for ( int j = 0; j < 3; j++ ) {
        //         charMappings[i][j] = String.valueOf(startingChar++);
        //     }
        // }
        // printArr(charMappings);
        //charMappings.put("a", )
        return rec(digits, charMappings);
    }

    private void printArr(String[][] arr) {
        for ( int i = 0; i < arr.length; i++ ) {
            for ( int j = 0; j < arr[0].length; j++ ) {
                System.out.print(arr[i][j] + " - ");
            }
            System.out.println();
        }
    }
    private List<String> rec(String digits, HashMap<String, List<String>> charMappings) {
        //base case
        //single digit
        if ( digits.length() == 1 ) {
            return charMappings.get(digits);
        }
        //now for 2 digits
        //iterate over each digit, call recursively
        //this will give us n number of lists
        //then we will have to combine the two lists into a single list and return it
        //how to combine
        //the result should be a set
        //two nested for loops b
        else {
            String firstDigit = digits.substring(0, 1);
            String remainingDigits = digits.substring(1, digits.length());
            List<String> mappingsForFirstDigit = rec(firstDigit, charMappings);
            List<String> mappingsForRemainingDigits = rec(remainingDigits, charMappings);
            //combine and return the result
            List<String> combined = new ArrayList<String>(digits.length() * digits.length());
            for ( String firstDigitWord : mappingsForFirstDigit ) {
                for ( String remainingDigitWord : mappingsForRemainingDigits ) {
                    combined.add(firstDigitWord + remainingDigitWord);
                }
                //combined.addAll(mappingsForRemainingDigits.parallelStream().map(word -> firstDigitWord + word).collect(Collectors.toSet()));
            }
            // List.of(mappingsForRemainingDigits).stream().flatMap(word ->)
            return combined;
        }
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        List<String> result = s.letterCombinations("23");
        for ( String str : result ) {
            System.out.print(str + " - ");
        }
    }
}
