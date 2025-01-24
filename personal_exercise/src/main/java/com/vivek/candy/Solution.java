package com.vivek.candy;

import java.util.Arrays;

class Solution {

    public int candy(int[] ratings) {
        /*
         * There are n children standing in a line. Each child is assigned a rating
         * value given in the integer array ratings.
         * 
         * You are giving candies to these children subjected to the following
         * requirements:
         * 
         * Each child must have at least one candy.
         * Children with a higher rating get more candies than their neighbors.
         * Return the minimum number of candies you need to have to distribute the
         * candies to the children.
         * 
         * 
         * 
         * Example 1:
         * 
         * Input: ratings = [1,0,2]
         * Output: 5
         * Explanation: You can allocate to the first, second and third child with 2, 1,
         * 2 candies respectively.
         * Example 2:
         * 
         * Input: ratings = [1,2,2]
         * Output: 4
         * Explanation: You can allocate to the first, second and third child with 1, 2,
         * 1 candies respectively.
         * The third child gets 1 candy because it satisfies the above two conditions.
         * 
         * 
         * Constraints:
         * 
         * n == ratings.length
         * 1 <= n <= 2 * 10^4
         * 0 <= ratings[i] <= 2 * 10^4
         * 
         * My Example 1:
         * Input: ratings = [1,4,3,2] [1,3,2,1]
         * 
         * Solution strategy:
         * - start with giving 1 candy to each kid
         * - iterate over all kids and find the ones which have higher rating than their
         * neighbor and give them another
         */

        //Kid[] kids = new Kid[ratings.length];
        int[] candies = new int[ratings.length];
        for (int i = 0; i < ratings.length; i++) {
            // kids[i] = new Kid(1, 1, null, null);
            candies[i] = 1;
        }

        boolean updated;// = true;
        do {
            updated = updateCandies(candies, ratings);
        } while (updated);
        // while (!isValid(candies, ratings)) {
        //     updateCandies(candies, ratings);
        // }

        return Arrays.stream(candies).sum();

    }

    // private void updateCandiesOnce(int[] candies, int[] ratings) {
    //     for ( int i = 0; i < ratings.length; i++ ) {

    //     }
    // }

    private boolean updateCandies(int[] candies, int[] ratings) {
        boolean updated = false;
        for (int i = 0; i < ratings.length; i++) {
            // if i has a higher rating but less candies than it's left or right neighbor
            // then increas the number of cadies by the number of candies of the neighbor
            // if 0 then check only the right neighbor
            if (i == 0) {
                if (ratings[i] > ratings[i + 1] && candies[i] <= candies[i + 1]) {
                    candies[i] += 1;// candies[i+1];
                    updated = true;
                }
            }

            // if last item then check only the left neighbor
            else if (i == (ratings.length - 1)) {
                if (ratings[i] > ratings[i - 1] && candies[i] <= candies[i - 1]) {
                    candies[i] += 1;// candies[i-1];
                    updated = true;
                }
            }

            // else check left neighbor and then check right neighbor
            else {
                if (ratings[i] > ratings[i - 1] && candies[i] <= candies[i - 1]) {
                    candies[i] += 1;// candies[i-1];
                    updated = true;
                }
                if (ratings[i] > ratings[i + 1] && candies[i] <= candies[i + 1]) {
                    candies[i] += 1;// candies[i+1];
                    updated = true;
                }
            }
        }
        return updated;
    }

    private boolean isValid(int[] candies, int[] ratings) {
        // check if the candies are valid according to the rules and ratings
        boolean isValid = true;
        for (int i = 0; i < ratings.length; i++) {
            // if rating of i is greater than either left or right neighbor then the number
            // of candies should be greater
            // if index 0 then check only the right neighbor
            if (i == 0) {
                if (ratings[i] > ratings[i + 1]) {
                    if (candies[i] > candies[i + 1]) {
                        System.out.println("all good for index " + i + " and " + (i + 1));
                    } else {
                        System.out.println("invalid -- rating of " + i + ": " + ratings[i]
                                + " is greater than rating of " + (i + 1) + ": " + ratings[i + 1]);
                        return false;
                    }
                } else if (ratings[i] < ratings[i + 1]) {
                    if (candies[i] < candies[i + 1]) {
                        System.out.println("all good for index " + i + " and " + (i + 1));
                    } else {
                        System.out.println("invalid at index " + i);
                        return false;
                    }
                }
            }
            // if index (length - 1) then check only the left neighbor
            else if (i == (ratings.length - 1)) {
                if (ratings[i - 1] > ratings[i]) {
                    if (candies[i - 1] > candies[i]) {
                        System.out.println("all good for index " + (i - 1) + " and " + i);
                    } else {
                        System.out.println("invalid -- rating of " + i + ": " + ratings[i]
                                + " is greater than rating of " + (i + 1) + ": " + ratings[i + 1]);
                        return false;
                    }
                } else if (ratings[i - 1] < ratings[i]) {
                    if (candies[i - 1] < candies[i]) {
                        System.out.println("all good for index " + i + " and " + (i + 1));
                    } else {
                        System.out.println("invalid at index " + i);
                        return false;
                    }
                }
            }
            // else check both left and right neighbor
            else {
                // check left neighbor
                if (ratings[i - 1] > ratings[i]) {
                    if (candies[i - 1] > candies[i]) {
                        System.out.println("all good for index " + (i-1) + " and " + i);
                    } else {
                        System.out
                                .println("invalid -- rating of " + i + ": " + ratings[i] + " is greater than rating of "
                                        + (i + 1) + ": " + ratings[i + 1]);
                        return false;
                    }
                } else if (ratings[i - 1] < ratings[i]) {
                    if (candies[i - 1] < candies[i]) {
                        System.out.println("all good for index " + (i-i) + " and " + i);
                    } else {
                        System.out.println("invalid at index " + i);
                        return false;
                    }
                }
                // check right neighbor
                if (ratings[i] > ratings[i + 1]) {
                    if (candies[i] > candies[i + 1]) {
                        System.out.println("all good for index " + i + " and " + (i + 1));
                    } else {
                        System.out
                                .println("invalid -- rating of " + i + ": " + ratings[i] + " is greater than rating of "
                                        + (i + 1) + ": " + ratings[i + 1]);
                        return false;
                    }
                } else if (ratings[i] < ratings[i + 1]) {
                    if (candies[i] < candies[i + 1]) {
                        System.out.println("all good for index " + i + " and " + (i + 1));
                    } else {
                        System.out.println("invalid at index " + i);
                        return false;
                    }
                }
            }

        }
        return isValid;
    }

    // private class Kid {
    //     private int numCandiesOwned;
    //     private int maxNeighborCandy;
    //     private Kid leftNeighbor;
    //     private Kid rightNeighbor;

    //     public Kid(int numCandiesOwned, int maxNeighborCandy, Kid leftNeighbor, Kid rightNeighbor) {
    //         this.numCandiesOwned = numCandiesOwned;
    //         this.maxNeighborCandy = maxNeighborCandy;
    //         this.leftNeighbor = leftNeighbor;
    //         this.rightNeighbor = rightNeighbor;
    //     }

    //     public int getNumCandiesOwned() {
    //         return numCandiesOwned;
    //     }

    //     public void setNumCandiesOwned(int numCandiesOwned) {
    //         this.numCandiesOwned = numCandiesOwned;
    //     }

    //     public int getMaxNeighborCandy() {
    //         return maxNeighborCandy;
    //     }

    //     public void setMaxNeighborCandy(int maxNeighborCandy) {
    //         this.maxNeighborCandy = maxNeighborCandy;
    //     }

    //     public Kid getLeftNeighbor() {
    //         return leftNeighbor;
    //     }

    //     public void setLeftNeighbor(Kid leftNeighbor) {
    //         this.leftNeighbor = leftNeighbor;
    //     }

    //     public Kid getRightNeighbor() {
    //         return rightNeighbor;
    //     }

    //     public void setRightNeighbor(Kid rightNeighbor) {
    //         this.rightNeighbor = rightNeighbor;
    //     }

    //     @Override
    //     public boolean equals(Object o) {
    //         if (this == o)
    //             return true;
    //         if (o == null || getClass() != o.getClass())
    //             return false;

    //         Kid kid = (Kid) o;

    //         if (numCandiesOwned != kid.numCandiesOwned)
    //             return false;
    //         if (maxNeighborCandy != kid.maxNeighborCandy)
    //             return false;
    //         if (leftNeighbor != null ? !leftNeighbor.equals(kid.leftNeighbor) : kid.leftNeighbor != null)
    //             return false;
    //         return rightNeighbor != null ? rightNeighbor.equals(kid.rightNeighbor) : kid.rightNeighbor == null;
    //     }

    //     @Override
    //     public int hashCode() {
    //         int result = numCandiesOwned;
    //         result = 31 * result + maxNeighborCandy;
    //         result = leftNeighbor != null ? leftNeighbor.hashCode() : 0;
    //         result = 31 * result + (rightNeighbor != null ? rightNeighbor.hashCode() : 0);
    //         return result;
    //     }

    //     @Override
    //     public String toString() {
    //         return "Kid{" +
    //                 "numCandiesOwned=" + numCandiesOwned +
    //                 ", maxNeighborCandy=" + maxNeighborCandy +
    //                 ", leftNeighbor=" + (leftNeighbor != null ? "Kid" : "null") +
    //                 ", rightNeighbor=" + (rightNeighbor != null ? "Kid" : "null") +
    //                 '}';
    //     }
    // }

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.testRatings();
    }

    private void testRatings() {
        testRatings1();
        testRatings2();
        testRatings3();
        testRatings4();
        testRatings5();
    }

    private void testRatings1() {
        int[] ratings1 = { 1, 0, 2 };
        System.out.println("####Minimum candies needed for ratings1: " + candy(ratings1)); // Output: 5
    }

    private void testRatings2() {
        int[] ratings2 = { 1, 2, 2 };
        System.out.println("####Minimum candies needed for ratings2: " + candy(ratings2)); // Output: 4
    }

    private void testRatings3() {
        int[] ratings3 = { 1, 4, 3, 2 };
        System.out.println("####Minimum candies needed for ratings3: " + candy(ratings3)); // Output: 7
    }

    private void testRatings4() {
        int[] ratings4 = { 1, 2, 87, 88, 87, 2, 1 };
        System.out.println("####Minimum candies needed for ratings3: " + candy(ratings4)); // Output: 13
    }

    private void testRatings5() {
        int[] ratings5 = new int[20001];
        for ( int i = 1; i <= 20000; i++ ) {
            ratings5[i] = i;
        }
        System.out.println("####Minimum candies needed for ratings3: " + candy(ratings5)); // Output: 7
    }
}
