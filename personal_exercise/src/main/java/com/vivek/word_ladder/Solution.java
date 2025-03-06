package com.vivek.word_ladder;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class Solution {

    /*
     * A transformation sequence from word beginWord to word endWord using a
     * dictionary wordList is a sequence of words beginWord -> s1 -> s2 -> ... -> sk
     * such that:
     * 
     * Every adjacent pair of words differs by a single letter.
     * Every si for 1 <= i <= k is in wordList. Note that beginWord does not need to
     * be in wordList.
     * sk == endWord
     * Given two words, beginWord and endWord, and a dictionary wordList, return the
     * number of words in the shortest transformation sequence from beginWord to
     * endWord, or 0 if no such sequence exists.
     * 
     * 
     * 
     * Example 1:
     * 
     * Input: beginWord = "hit", endWord = "cog", wordList =
     * ["hot","dot","dog","lot","log","cog"]
     * Output: 5
     * Explanation: One shortest transformation sequence is "hit" -> "hot" -> "dot"
     * -> "dog" -> cog", which is 5 words long.
     * Example 2:
     * 
     * Input: beginWord = "hit", endWord = "cog", wordList =
     * ["hot","dot","dog","lot","log"]
     * Output: 0
     * Explanation: The endWord "cog" is not in wordList, therefore there is no
     * valid transformation sequence.
     * 
     * 
     * Constraints:
     * 
     * 1 <= beginWord.length <= 10
     * endWord.length == beginWord.length
     * 1 <= wordList.length <= 5000
     * wordList[i].length == beginWord.length
     * beginWord, endWord, and wordList[i] consist of lowercase English letters.
     * beginWord != endWord
     * All the words in wordList are unique.
    */

     

    
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {

        boolean exists = false;
        for ( String word : wordList ) {
            if (word.equals(endWord) ) {
                exists = true;
                break;
            }
        }
        if ( !exists ) {
            return 0;
        }

        Set<String> seenNeighbors = new HashSet<>();
        Queue<String> queue = new LinkedList<>();
        int distance = 0;

        queue.add(beginWord);

        while ( !queue.isEmpty() ) {
            //pop the first element
            String thisWord = queue.poll();
            System.out.println("checking next neighbor " + thisWord);
            //retrieve neighbors
            Set<String> neighbors = this.getNeighbors(thisWord, wordList);
            //if the target is present in the neighbors then return the distance + 1
            if ( neighbors.contains(endWord) ) {
                return distance + 1;
            }
            //add all the neighbors to the queue which have not been seen before and increment the distance
            

            //filter to find unseen neighbors
            neighbors.removeAll(seenNeighbors);
            if ( !neighbors.isEmpty() ) {
                
                for ( String neighbour : neighbors) {
                    queue.add(neighbour);
                }
                distance++;
                seenNeighbors.addAll(neighbors);
            }
        }

        return 0;

        //we have the starting word
        //we have the ending word

        //we have a dictionary which contains 1 transformations

        //we can start with words in the dictionary which are 1 away (figure out how to implement this check)
        //this appears to be backtracking problem so we will create a rec function and will include the count in the parameter

        //gather list of words which are 1 away

        //base case 1 if target is present in the adjacent words then return current count + 1


    }

    private boolean isOneAway(String begin, String end) {
        //we can do this fast using two pointers
        int matchCount = 0;
        char[] beginArray = begin.toCharArray();
        char[] endArray = end.toCharArray();
        for ( int i = 0; i < begin.length(); i++ ) {
            if (beginArray[i] == endArray[i]) {
                matchCount++;
            }
        }
        if (matchCount == (begin.length() - 1) ) {
            System.out.println(String.format("words %s and %s are one away", begin, end));
            return true;
        }
        else {
            System.out.println(String.format("words %s and %s are NOT one away", begin, end));
            return false;
        }
    }

    private Set<String> getNeighbors(String begin, List<String> disctionary) {
        Set<String> neighbors = new HashSet<>();
        for ( String word : disctionary ) {
            if (isOneAway(begin, word) ) {
                neighbors.add(word);
            }
        }
        return neighbors;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        List<String> wordList1 = new ArrayList<>();
        wordList1.add("hot");
        wordList1.add("pot");
        wordList1.add("dot");
        wordList1.add("dog");
        wordList1.add("lot");
        wordList1.add("log");
        wordList1.add("cog");

        System.out.println(solution.ladderLength("hit", "cog", wordList1)); // Output: 5

        List<String> wordList2 = new ArrayList<>();
        wordList2.add("hot");
        wordList2.add("dot");
        wordList2.add("dog");
        wordList2.add("lot");
        wordList2.add("log");

        System.out.println(solution.ladderLength("hit", "cog", wordList2)); // Output: 0
    }
}
