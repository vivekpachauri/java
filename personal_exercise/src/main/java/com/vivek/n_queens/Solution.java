package com.vivek.n_queens;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Solution {

    /*
     * The n-queens puzzle is the problem of placing n queens on an n x n chessboard
     * such that no two queens attack each other.
     * 
     * Given an integer n, return all distinct solutions to the n-queens puzzle. You
     * may return the answer in any order.
     * 
     * Each solution contains a distinct board configuration of the n-queens'
     * placement, where 'Q' and '.' both indicate a queen and an empty space,
     * respectively.
     * 
     * 
     * 
     * Example 1:
     * 
     * 
     * Input: n = 4
     * Output: [[".Q..","...Q","Q...","..Q."],["..Q.","Q...","...Q",".Q.."]]
     * Explanation: There exist two distinct solutions to the 4-queens puzzle as
     * shown above
     * Example 2:
     * 
     * Input: n = 1
     * Output: [["Q"]]
     * 
     * 
     * Constraints:
     * 
     * 1 <= n <= 9
     */
    public List<List<String>> solveNQueens(int n) {
        /*
         * Outline
         * Quenstions:
         * - what if it is not possible, example with a 2x2 board
         * - start with n possibilities in bottom row, n or less of the options will be
         * cancelled out, if a queen cannot be placed then we drop this option
         * - we can either use nxn matrix or mxn matrix where m is the number of rows
         * with valid options
         * - then check which positions are possible in the subsequent row given the
         * current placement of queens
         * - sub problems
         * - given a current board of size nxn and currently the queens placed in the
         * first m rows, can we place a queen in m+1 row
         * - given a current board of size nxn and currently the queens placed in the
         * first m rows, how many different ways queen could be placed in m+1 row,
         * return a new board configuration for each possibility
         * 
         * - how do we represent a queen (boolean - false represent no queen and true
         * represent queen, integer - 0 represent no queen and 1 represent queen, string
         * x for no queen and q for queen)
         */
        //so now rows is our full possible space, we have to modify these one by one and see which could be updated

        //here we identify the problem that the solution space could have more than n options

        //we cannot start with array, we have to use a list so that multiple second row options could be added to each first row possibility

        //so each individual row could be represented as an array but collection of rows must be represented as list of rows
          // a list of row represent a specific board and a list of list of rows will represent multiple boards

        //so let's say that the first row will be represented as a list of row and each row will represent a queen placed in each of the possible index of the array
        
        if ( n == 1 ) {
            return Arrays.asList((Arrays.asList("Q")));
        }
        List<int[][]> boards = new ArrayList<>();
        List<List<String>> toReturn = new ArrayList<>();
        for ( int i = 0; i < n; i++ ) {
            int[][] oneRowBoard = new int[1][n];
            Arrays.fill(oneRowBoard[0], 0);
            oneRowBoard[0][i] = 1;
            boards.addAll(nQueensRec(oneRowBoard));
        }
        if ( boards.isEmpty()) {
            System.out.println("no placement possible");
        }
        else {
            System.out.println("solutions:");
        }
        for ( int[][] board : boards ) {
            //printBoard(board);
            List<String> boardRepresentation = new ArrayList<>();
            for (int[] row : board) {
                StringBuilder sb = new StringBuilder();
                for (int cell : row) {
                    sb.append(cell == 1 ? 'Q' : '.');
                }
                boardRepresentation.add(sb.toString());
            }
            toReturn.add(boardRepresentation);
            System.out.println();
            System.out.println();
        }
        return toReturn;
    }

    private List<int[][]> nQueensRec(int[][] currentBoard) {
        //there could be 0, 1, or more way to add one more row to the current board, therefore we will return empty list, representing that there is no way to add
        //a new queen in the next row, list of 1 element to represent that there is only one way to add a queen the next row, or multiple list elements to represent
        //that there are more than one ways to add a new queen in the next row
        
        //create n boards with a queen in each of the n columns in the first row
        //for each of the board extend the board by placing a new queen in every possible position in the next row

        List<int[][]> newBoards = new ArrayList<>();
        for ( int i = 0; i < currentBoard[0].length; i++ ) {
            int[] nextRow = new int[currentBoard[0].length];
            Arrays.fill(nextRow, 0);
            nextRow[i] = 1;

            //at this point we have the current board and the next possible row
            //check if the next row could be valid for the current board
            //if yes then this next row should be added to the current board and the new board should be part of the list to be returned
            if ( isValid(currentBoard, nextRow) ) {
                newBoards.add(extendBoard(currentBoard, nextRow));
            }
        }
        if ( newBoards.isEmpty()) {
            return newBoards;
        }
        else if (currentBoard.length + 1 == currentBoard[0].length) {
            return newBoards;
        }
        else {
            List<int[][]> toRetrun = new ArrayList<>();
            for ( int[][] board : newBoards ) {
                toRetrun.addAll(nQueensRec(board));
            }
            return toRetrun;
        }
        

    }

    private int[][] extendBoard(int[][] currentBoard, int[] nextRow) {
        assert(currentBoard[0].length == nextRow.length);
        int[][] newBoard = new int[currentBoard.length+1][currentBoard[0].length];
        for ( int i = 0; i < currentBoard.length; i++ ) {
            for ( int j = 0; j < currentBoard[0].length; j++ ) {
                newBoard[i][j] = currentBoard[i][j];
            }
        }
        for ( int i = 0; i < nextRow.length; i++ ) {
            newBoard[newBoard.length-1][i] = nextRow[i];
        }
        return newBoard;
    }

    // private boolean canPlace(int[][] rows) {
    //     /*
    //      * can create an array of booleans
    //      */
    //     for (int i = rows.length; i < 0; i--) {
    //         for (int j = rows[i].length; j < 0; j--) {
    //             System.out.print("|" + rows[i][j]);
    //         }
    //         System.out.println("|");
    //     }
    //     return false;
    // }

    private boolean isValid(int[][] board, int[] nextRow) {
        //given a current board config, check if the queen represented by nextRow is a valid way to place a queen
        //check which index the queen is at in the next row
        //none of the current queens could exist at that row
        //none of the current queens could exist at diagonal
          //if the next queen is at index i
            //there could not be a queen at index i - 1 or i + i or i in the length - 
            //there could not be a queen at index i - 2 or i + 2 or i in the 
        int queenIndex = -1;
        for ( int i = 0; i < nextRow.length; i++ ) {
            if (nextRow[i] == 1)
                queenIndex = i;
        }
        assert(queenIndex >= 0);
        int indexUpdate = 1;
        for (int i = board.length - 1; i >= 0; i-- ) {
            //can not be a queen at index queenIndex or i values to the left or right of queenIndex
            if (board[i][queenIndex] == 1)
                return false;
            //check the diagonal left if possible
            if ( queenIndex - indexUpdate >= 0 ) {
                if (board[i][queenIndex-indexUpdate] == 1 )
                    return false;
            } 
            //check the diagonal right if possible
            if ( queenIndex + indexUpdate < board[i].length ) {
                if ( board[i][queenIndex+indexUpdate] == 1 )
                {
                    return false;
                } 
            }
            indexUpdate++;
        }
        return true;
    }

    private void printBoards(int[][][] rows) {

        for (int k = rows.length - 1; k >= 0; k--) {
            System.out.println((k + 1) + "-->");
            for (int i = rows[k].length - 1; i >= 0; i--) {
                for (int j = rows[k][i].length - 1; j >= 0; j--) {
                    System.out.print("|" + rows[k][i][j]);
                }
                System.out.println("|");
            }
        }
    }

    private void printBoardd(int[][] rows) {
        for (int i = rows.length - 1; i >= 0; i--) {
            for (int j = rows[i].length - 1; j >= 0; j--) {
                System.out.print("|" + rows[i][j]);
            }
            System.out.println("|");
        }
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        s.solveNQueens(5);
    }

}
