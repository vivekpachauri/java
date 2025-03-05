package com.vivek.insertion_sort;

public class Solution {
    public int[] sort(int[] arr) {
        for ( int i = 1; i < arr.length; i++ )
        {
            int j = i;
            while (j > 0) {
                if (arr[j] < arr[j-1]) {
                    System.out.println("before");
                    printArr(arr);
                    int temp = arr[j];
                    arr[j] = arr[j-1];
                    arr[j-1] = temp;
                    System.out.println("end");
                    printArr(arr);
                }
                j--;
            }
        }
        return arr;
    }

    public void printArr(int[] arr) {
        for (int i : arr)  {
            System.out.print(i + " -> ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] arr = new int[] {5,2,5,6,8,4,1,9,7,3,6,0,3};
        Solution s = new Solution();
        s.printArr(s.sort(arr));
    }
}
