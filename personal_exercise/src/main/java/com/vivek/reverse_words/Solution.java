package com.vivek.reverse_words;

class Solution {
    public String reverseWords(String s) {
        String[] words = s.strip().replaceAll("\s+", " ").split("\s");
        for (String word : words ) System.out.println(word.strip());
        String result = words[words.length-1];
        for ( int i = words.length - 2; i >= 0; i-- ) {
            result += " ";
            result += words[i];
        }
        return result;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String result = solution.reverseWords("a good   example");
        System.out.println(result);
    }
}
