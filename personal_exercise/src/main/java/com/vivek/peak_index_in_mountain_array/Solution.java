package com.vivek.peak_index_in_mountain_array;

class Solution {
    public int peakIndexInMountainArray(int[] arr) {
        int test = 0;
        if ( test == 0 ) return alternateSolution(arr);
        int start = 0, end = arr.length - 1;
        while ( start < end ) {
            int mid = start + ((end - start) / 2);
            if ( arr[mid] < arr[mid+1] ) {
                start = mid+1;
            }
            else if ( arr[mid] > arr[mid+1] ) {
                end = mid;
            }
        }
        return start;
    }

    public int alternateSolution(int[] arr) {
        int start = 0,end = arr.length - 1, mid = 0;
        do {
            mid = start + ((end - start) / 2);
            if ( (arr[mid-1] < arr[mid]) && (arr[mid] > arr[mid+1]) ) {
                return mid;
            }
            else if ( (arr[mid-1] < arr[mid]) && (arr[mid] < arr[mid+1]) ) {
                //search to the right
                start = mid;
            }
            else {
                end = mid;
            }
        } 
        while ( end - start >= 2 );
        return -1;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        int[] arr = new int[] {1,2,3,4,3,2};
        System.out.println(s.peakIndexInMountainArray(arr));
    }

}
