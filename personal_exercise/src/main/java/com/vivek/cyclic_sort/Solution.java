package com.vivek.cyclic_sort;

public class Solution {
    public int[] cyclicSort(int[] arr) {
        int i = 0;
        printArray(arr);
        while ( i < arr.length ) {
            int correctPosition = arr[i] - 1;
            if ( arr[i] != arr[correctPosition] ) {
                int temp = arr[correctPosition];
                arr[correctPosition] = arr[i];
                arr[i] = temp;
                printArray(arr);
            }
            else {
                System.out.println("incrementing i");
                i++;
            }
        }
        return arr;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{4,6,2,8,1,9,7};
        Solution s = new Solution();
        for (int i : s.cyclicSort(arr) ) {
            System.out.print(i + " -> ");
        }
    }

    private void printArray(int[] arr) {
        for ( int i : arr ) {
            System.out.print(i + " -> ");
        }
        System.out.println();
    }
}
