package com.vivek.matrix_valid_tic_tac_toe;

public class Solution {
    /*
     * Given a Tic-Tac-Toe board as a string array board, return true if and only if
     * it is possible to reach this board position during the course of a valid
     * tic-tac-toe game.
     * 
     * The board is a 3 x 3 array that consists of characters ' ', 'X', and 'O'. The
     * ' ' character represents an empty square.
     * 
     * Here are the rules of Tic-Tac-Toe:
     * 
     * Players take turns placing characters into empty squares ' '.
     * The first player always places 'X' characters, while the second player always
     * places 'O' characters.
     * 'X' and 'O' characters are always placed into empty squares, never filled
     * ones.
     * The game ends when there are three of the same (non-empty) character filling
     * any row, column, or diagonal.
     * The game also ends if all squares are non-empty.
     * No more moves can be played if the game is over.
     * 
     * 
     * Example 1:
     * 
     * 
     * Input: board = ["O  ","   ","   "]
     * Output: false
     * Explanation: The first player always plays "X".
     * Example 2:
     * 
     * 
     * Input: board = ["XOX"," X ","   "]
     * Output: false
     * Explanation: Players take turns making moves.
     * Example 3:
     * 
     * 
     * Input: board = ["XOX","O O","XOX"]
     * Output: true
     * 
     * 
     * Constraints:
     * 
     * board.length == 3
     * board[i].length == 3
     * board[i][j] is either 'X', 'O', or ' '.
     */
    public boolean validTicTacToe(String[] board) {
        /*
         * rules to check
         * - the number of x and o should be either equal (o makes the last move and wins) or x can be one more than o (x make the last move and win)
         * - only one can be the winner (both cannot be in the same row, or column or diagonal)
         * - if x the winner then there should be one more x than o
         * 
         * how to check for the winner?
         * scan from left to right and then top to bottom
         * for each column check if there is an x or o in 
         *  - each column of the same row
         *  - each row of the same column
         *  - diagonal
         *  
         */

        boolean xInEachCol = true, oInEachCol = true, xInEachRow = true, oInEachRow = true, xInDiagonal = true, oInDiagonal = true;
        
        boolean xWinner = false, oWinner = false;
        int xCount = 0, oCount = 0;
        Character[][] pieces = new Character[3][3];
        //count xs and os as well during this loop
        for ( int i =  0; i < 3; i++ ) {
            for ( int j = 0; j < 3; j++ ) {
                pieces[i][j] = board[i].toCharArray()[j];
                if ( pieces[i][j] == 'X' ) {
                    xCount++;
                }
                else if ( pieces[i][j] == 'O' ) {
                    oCount++;
                }
            }
        }

        //check for X in top row
        if ( (pieces[0][0] == pieces[0][1]) && (pieces[0][1] == pieces[0][2]) && (pieces[0][2] == 'X') ) {
            xWinner = true;
        }

        //check for x in middle row
        if ( !xWinner ) {
            if ( (pieces[1][0] == pieces[1][1]) && (pieces[1][1] == pieces[1][2]) && (pieces[1][2] == 'X') ) {
                xWinner = true;
            }
        }

        //chekc for x in bottom row
        if ( !xWinner ) {
            if ( (pieces[2][0] == pieces[2][1]) && (pieces[2][1] == pieces[2][2]) && (pieces[2][2] == 'X') ) {
                xWinner = true;
            }
        }
        //check for x in left column
        if ( !xWinner ) {
            if ( (pieces[0][0] == pieces[1][0]) && (pieces[1][0] == pieces[2][0]) && (pieces[2][0] == 'X') ) {
                xWinner = true;
            }
        }
        //check for x in middle column
        if ( !xWinner ) {
            if ( (pieces[0][1] == pieces[1][1]) && (pieces[1][1] == pieces[2][1]) && (pieces[2][1] == 'X') ) {
                xWinner = true;
            }
        }
        //check for x in right column
        if ( !xWinner ) {
            if ( (pieces[0][2] == pieces[1][2]) && (pieces[1][2] == pieces[2][2]) && (pieces[2][2] == 'X') ) {
                xWinner = true;
            }
        }
        //check for x in left to right diagonal
        if ( !xWinner ) {
            if ( (pieces[0][0] == pieces[1][1]) && (pieces[1][1] == pieces[2][2]) && (pieces[2][2] == 'X') ) {
                xWinner = true;
            }
        }
        //check for x in right to left diagonal
        if ( !xWinner ) {
            if ( (pieces[0][2] == pieces[1][1]) && (pieces[1][1] == pieces[2][0]) && (pieces[2][0] == 'X') ) {
                xWinner = true;
            }
        }
        //repeat above checks for o
        if ( !oWinner ) {
            //check for O in top row
            if ( (pieces[0][0] == pieces[0][1]) && (pieces[0][1] == pieces[0][2]) && (pieces[0][2] == 'O') ) {
                oWinner = true;
            }

            //check for O in middle row
            if ( !oWinner ) {
                if ( (pieces[1][0] == pieces[1][1]) && (pieces[1][1] == pieces[1][2]) && (pieces[1][2] == 'O') ) {
                    oWinner = true;
                }
            }

            //chekc for o in bottom row
            if ( !oWinner ) {
                if ( (pieces[2][0] == pieces[2][1]) && (pieces[2][1] == pieces[2][2]) && (pieces[2][2] == 'O') ) {
                    oWinner = true;
                }
            }
            //check for o in left column
            if ( !oWinner ) {
                if ( (pieces[0][0] == pieces[1][0]) && (pieces[1][0] == pieces[2][0]) && (pieces[2][0] == 'O') ) {
                    oWinner = true;
                }
            }
            //check for o in middle column
            if ( !oWinner ) {
                if ( (pieces[0][1] == pieces[1][1]) && (pieces[1][1] == pieces[2][1]) && (pieces[2][1] == 'O') ) {
                    oWinner = true;
                }
            }
            //check for o in right column
            if ( !oWinner ) {
                if ( (pieces[0][2] == pieces[1][2]) && (pieces[1][2] == pieces[2][2]) && (pieces[2][2] == 'O') ) {
                    oWinner = true;
                }
            }
            //check for o in left to right diagonal
            if ( !oWinner ) {
                if ( (pieces[0][0] == pieces[1][1]) && (pieces[1][1] == pieces[2][2]) && (pieces[2][2] == 'O') ) {
                    oWinner = true;
                }
            }
            //check for o in right to left diagonal
            if ( !oWinner ) {
                if ( (pieces[0][2] == pieces[1][1]) && (pieces[1][1] == pieces[2][0]) && (pieces[2][0] == 'O') ) {
                    oWinner = true;
                }
            }
        }

        //if x winner
        //x count should be 1 greater than o and should be at least 3
        if ( xWinner ) {
            System.out.println("x winner");
            if ( xCount < 3 ) {
                System.out.println(1);
                return false;
            }
            if ( xCount != (1 + oCount) ) {
                System.out.println(2);
                return false;
            }
        }

        //if o winner

        if ( oWinner ) {
            System.out.println("o winner");
            if ( oCount < 3 ) {
                System.out.println(3);
                return false;
            }
            if ( oCount != xCount ) {
                System.out.println(4);
                return false;
            }
        }

        //if no winner
        //either the game finished or it is in progress
        //if the game finished the the counts should be equal
        //if the game in progress then 
        if ( (xCount != oCount) && (xCount != (1 + oCount)) ) {
            System.out.println(5);
            return false;
        } 


        return true;

    }

}
