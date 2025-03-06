package com.vivek.find_in_mountain_array;

public class Solution {
    public int findInMountainArray(int target, MountainArray mountainArr) {
        int start = 0, end = mountainArr.getLength() - 1, mid;
        // do {
        //     mid = start + ((end - start) / 2);
        //     //check if in ascending or descending part
        //     if ( mountainArr.getValue(mid) )
        // } while ((end - start) >= 2);
        while ( start < end ) {
            mid = start + ((end - start) / 2);
            int midValue = mountainArr.getValue(mid);
            if ( midValue == target) {
                return mid;
            }
            //check if in ascending or descending part
            else if ( midValue < mountainArr.getValue(mid+1) ) {
                //in the ascending part
                //if the target is smaller than mid then look to the left
                if ( midValue > target ) {
                    end = mid;
                }
                else {
                    //target is greater than mid, search to the right
                    start = mid + 1;
                }
            }
            else {
                //in the descending part
                //if the target is greater than mid value then search to the left
                if ( midValue < target ) {
                    end = mid;
                }
                //if the target is smaller than mid value then search to the right
                else {
                    start = mid + 1;
                }
            }
        }
        return -1;
    }


    public static void main(String[] args) {
        MountainArray mountainArr = new MountainImpl(new int[]{1, 2, 3, 4, 5, 3, 1});
        Solution solution = new Solution();
        
        int target = 3;
        int result = solution.findInMountainArray(target, mountainArr);
        System.out.println("Index of target " + target + ": " + result);
        
        target = 5;
        result = solution.findInMountainArray(target, mountainArr);
        System.out.println("Index of target " + target + ": " + result);
        
        target = 1;
        result = solution.findInMountainArray(target, mountainArr);
        System.out.println("Index of target " + target + ": " + result);
        
        target = 6;
        result = solution.findInMountainArray(target, mountainArr);
        System.out.println("Index of target " + target + ": " + result);
    }
}


abstract interface MountainArray {
    public int getLength();
    public int getValue(int index);
}

class MountainImpl implements MountainArray {
    private int[] array;

    public MountainImpl(int[] array) {
        this.array = array;
    }

    @Override
    public int getLength() {
        return array.length;
    }

    @Override
    public int getValue(int index) {
        return array[index];
    }
}
