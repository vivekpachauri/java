package com.vivek.kth_factor;

public class Driver {

    public int kthFactor(int n, int k) {
        /*
         * Option 1 -> we find all the factors and then find the kth value
         * Simple option -> iterate from 1 to n and find the numbers which are factors
         * and keep track
         * of the number of factors found
         * Option 2 -> we find the only up to k number of factors
         */
        int factorCount = 0;
        int factor = -1;
        for (int i = 1; i <= n; i++) {
            if (n % i == 0) {
                factorCount++;
                factor = i;
                if (factorCount == k)
                    return factor;
            }
        }
        return -1;
    }
    public static void main (String[] args) {
        Driver driver = new Driver();
        int n = 12;
        int k = 3;
        int result = driver.kthFactor(n, k);
        System.out.println("The " + k + "th factor of " + n + " is: " + result);
    }

}
