package com.vivek.matrix_rotating_the_box;

class Solution {
    public char[][] rotateTheBox(char[][] boxGrid) {
        int row = boxGrid.length;
        int col = boxGrid[0].length;
        // System.out.println("row " + row + " col " + col);

        char[][] result = new char[col][row];
        //not taking a transpose but instead flipping by 90 degrees
        int boxRow = 0;
        int boxCol = 0;
        //column index for original is the same as row for the result
        int resultRow = boxCol;
        int resultCol = row - 1;
        for ( int i = 0; i < row; i++ ) {
            for ( int j = 0; j < col; j++ ) {
                // System.out.println(j+ "," + ((row - 1) - i) + "=" + i + "," + j);
                result[j][(row-1) - i] = boxGrid[i][j];
                
            }
        }
        // System.out.println("initialized array");
        // printArray(result);

        for ( int i = 0; i < row; i++ ) {
            for ( int j = (col - 1); j > 0; j--) {
                //if the grid contains space then scan forward until a stone or obstacle is found
                // System.out.println("scanning row " + j + " col " + i);
                if ( result[j][i] == '.') {
                    // System.out.println("found space at index " + j + "x" + i);
                    //go from current index to either the top or until a stone or obstacle is found
                    int indexToSwap = -1;
                    int curRow = j-1;
                    while (curRow >= 0) {
                        //if obstacle found then skip
                        if ( result[curRow][i] == '*' ) {
                            //can't do anything with this row, skip
                            break;
                        }
                        else if ( result[curRow][i] == '#' ) {
                            // System.out.println("found object at index " + curRow + "x" + i);
                            indexToSwap = curRow;
                            break;
                        }
                        curRow--;
                    }
                    if ( indexToSwap != -1 ) {
                        // System.out.println("swapping space at index " + j + "x" + i + " with stone at index " + indexToSwap + "x" + i);
                        result[j][i] = '#';//result[indexToSwap][i];
                        result[indexToSwap][i] = '.';
                    }
                }
            }
        }
        return result;
    }
}
