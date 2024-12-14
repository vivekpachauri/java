package com.vivek.stock_profit;

import java.util.Arrays;

public class Driver {
    /*
     * You are given an integer array prices where prices[i] is the price of a given
     * stock on the ith day.
     * 
     * On each day, you may decide to buy and/or sell the stock. You can only hold
     * at most one share of the
     * stock at any time. However, you can buy it then immediately sell it on the
     * same day.
     * 
     * Find and return the maximum profit you can achieve.
     * 
     * 
     * 
     * Example 1:
     * 
     * Input: prices = [7,1,5,3,6,4]
     * Output: 7
     * Explanation: Buy on day 2 (price = 1) and sell on day 3 (price = 5), profit =
     * 5-1 = 4.
     * Then buy on day 4 (price = 3) and sell on day 5 (price = 6), profit = 6-3 =
     * 3.
     * Total profit is 4 + 3 = 7.
     * Example 2:
     * 
     * Input: prices = [1,2,3,4,5]
     * Output: 4
     * Explanation: Buy on day 1 (price = 1) and sell on day 5 (price = 5), profit =
     * 5-1 = 4.
     * Total profit is 4.
     * Example 3:
     * 
     * Input: prices = [7,6,4,3,1]
     * Output: 0
     * Explanation: There is no way to make a positive profit, so we never buy the
     * stock to achieve the maximum profit of 0.
     * 
     * 
     * Constraints:
     * 
     * 1 <= prices.length <= 3 * 104
     * 0 <= prices[i] <= 104
     */

     /*
      * Solution strategy
      each entry is the price
      therefore there could be an nxn array created to represent the distance from that number to every subsequent number
      example
      [1,2,3,4,5]
      [0,1,2,3,4]
      [-,0,1,2,3]
      [-,-,0,1,2]
      [-,-,-,0,1]
      [-,-,-,-,0]
      */

    public int maxProfit(int[] prices) {
        int[][] priceDifference = new int[prices.length][prices.length];
        for ( int i = 0; i < prices.length; i++ ) {
            Arrays.fill(priceDifference[i], -1);
        }
        // Arrays.fill(priceDifference, -1);
        printArray(priceDifference);
        for ( int i = 0; i < prices.length; i++ ) {
            for (int j = i; j < prices.length; j++ ) {
                priceDifference[i][j] = Math.max(prices[j] - prices[i], 0);
            }
        }
        printArray(priceDifference);

        int max = 0;
        // for ( int i = 0; i < priceDifference.length; i++ ) {
        //     for ( int j = 0; j < priceDifference[i].length; j++ ) {
                
        //     }
        // }
        int purchaseDay = 0, profitDay = 0, maxProfitPurchaseDay = 0;

        while ( purchaseDay < priceDifference.length) {
            while ( profitDay < priceDifference[purchaseDay].length ) {
                if ( max < priceDifference[purchaseDay][profitDay] ) {
                    max = priceDifference[purchaseDay][profitDay];
                    maxProfitPurchaseDay = purchaseDay;
                }
            }

        }
        return 0;
    }

    private int calculateMaxProfit(int[][] priceDifference, int buyDay) {
        //check for each day how much money could be made if the stock is sold from the beginning until that day

        //total number of days are n
        //therefore is stock is sold on the nth day and purchased on the first day then nothing more is left
        //if the stock is sold on n-1st day and then purchased back then how much money could be made on nth day
        for ( int i = 0, j = 0; i < priceDifference.length; i++, j++ ) {

        }
        return 0;
    }

    private void printArray(int[][] toPrint) {
        for ( int i = 0; i < toPrint.length; i++ ) {
            for ( int j = 0; j < toPrint[i].length; j++ ) {
                System.out.print("|   " + toPrint[i][j] + "     |");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Driver driver = new Driver();
        int[] prices = new int[] { 7,2,1,6,5,8 };
        System.out.println("total profit = " + driver.maxProfit(prices));
    }
}
