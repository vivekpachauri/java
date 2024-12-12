package com.vivek.merge_sorted_array;

public class Driver {

    public static void main(String[] args) {
        Driver d = new Driver();
        // int[] marray = new int[] {1,2,3,0,0,0};//{1,2,8,0,0,0};
        // int mSize = 3;
        // int[] narray = new int[] {2,5,6};
        // int nSize = 3;
        int[] marray = new int[] {-1,0,0,3,3,3,0,0,0};//{1,2,8,0,0,0};
        int mSize = 6;
        int[] narray = new int[] {1,2,2};
        int nSize = 3;
        d.merge(marray, mSize, narray, nSize);
        System.out.println("result");
        for (int i : marray) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int index = 0;
        int indexAtNum1 = 0;
        int indexAtNum2 = 0;
        int elementsConsumedFromFirstArray = 0;
        while (index < m+n && (indexAtNum2 < n)) {
            System.out.println("beginning of full index loop for index " + index);

            //need to corner cases, first to check if all the elements in the first array has been processed
            //need to rethink if this corner case is needed
            //second to check if all the elements in the second array has been processed
            // if (indexAtNum2 == n ) {

            // }
            //if the element at indexAtNum1 is zero then we simply copy the value from num2 array at this location
            //if (nums1[indexAtNum1] == 0) {
                if (elementsConsumedFromFirstArray == m) {
                System.out.println("value in nums1 is zero (update all values in first array are consumed) so somply overwriting the value from nums2");
                nums1[indexAtNum1] = nums2[indexAtNum2];
                indexAtNum1++;
                indexAtNum2++;
            }
            //if element in nums 1 is smaller then keep it and move on to
            //the next index, else put it in the index location and move the
            //rest of the item farther
            //ex
            //nums1 [3, 4, 5, 0, 0, 0] nums2[1,2,3]
            //if the value at index in nums1 is lesser then choose it
            //which means the lesser value is in place so don't need to
            //do anything, just move on and update the index
            else if (nums1[indexAtNum1] < nums2[indexAtNum2]) {
                System.out.println("value in nums1 is smaller");
                indexAtNum1++;
                elementsConsumedFromFirstArray++;
            } else if (nums1[indexAtNum1] == nums2[indexAtNum2]) {
                System.out.println("value in nums1 is equal");
                indexAtNum1++;
                elementsConsumedFromFirstArray++;

            } else {
                System.out.println("value in nums2 is smaller");
                //if the value in the big array at index is greater than
                //the value in the smaller array then the value from
                //the smaller array will have to be inserted in the big
                //array which means all the values in the big array will
                //have to be shifted to the right

                //shifting the values to the right
                //the values starting from index until m will have to be
                //moved to the right
                System.out.println("shifting the values starting from index " + indexAtNum1 + " until " + m + " to the right");
                System.out.println("array before shifting");
                for (int i : nums1) {
                    System.out.print(i + " ");
                }
                System.out.println();
                int startingIndexToShift = indexAtNum1;
                // while (startingIndexToShift < m + n) {
                //     int tmp = nums1[startingIndexToShift+1];
                //     nums1[startingIndexToShift+1] = nums1[startingIndexToShift];
                //     nums1[startingIndexToShift+2] = tmp;

                // }
                // for (int i = indexAtNum1; i < m+indexAtNum2; i++ ) {
                //     int tmp = nums1[i+1];
                //     nums1[i+1] = nums1[i];
                //     //nums1[i+2] = tmp;
                // }
                for ( int i = m+indexAtNum2; i > indexAtNum1; i--) {
                    nums1[i] = nums1[i-1];
                }

                System.out.println("array after shifting");
                for (int i : nums1) {
                    System.out.print(i + " ");
                }
                System.out.println();
                //int tmp = nums1[indexAtNum1];
                nums1[indexAtNum1] = nums2[indexAtNum2];
                System.out.println("array after new insert");
                for (int i : nums1) {
                    System.out.print(i + " ");
                }
                System.out.println();
                indexAtNum1++;
                indexAtNum2++;

            }
            System.out.println("end of full index loop for index " + index);
            index++;
        }
    }

}
