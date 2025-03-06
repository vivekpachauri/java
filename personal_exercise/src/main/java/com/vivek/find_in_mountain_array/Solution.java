package com.vivek.find_in_mountain_array;

public class Solution {
    public int findInMountainArrayIncorrect(int target, MountainArray mountainArr) {
        int start = 0, end = mountainArr.length() - 1, mid;
        // do {
        //     mid = start + ((end - start) / 2);
        //     //check if in ascending or descending part
        //     if ( mountainArr.getValue(mid) )
        // } while ((end - start) >= 2);
        while ( start < end ) {
            mid = start + ((end - start) / 2);
            int midValue = mountainArr.get(mid);
            if ( midValue == target) {
                return mid;
            }
            //check if in ascending or descending part
            else if ( midValue < mountainArr.get(mid+1) ) {
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

    public int findInMountainArray(int target, MountainArray mountainArr) {
        int start = 0, end = mountainArr.length() - 1;
        int peak = findPeak(mountainArr, start, end, target);
        // System.out.println("peak index " + peak);
        int result = -1;
        result = searchAsc(mountainArr, 0, peak, target);
        if ( result == -1 ) {
            // System.out.println("not found in ascending part");
            result = searchDesc(mountainArr, peak + 1, end, target);
        }
        return result;
    }

    private int findPeak(MountainArray mtn, int start, int end, int target) {
        // int peak = 0;
        int peakIndex = 0;
        while (start <= end ) {
            int mid = ((end - start) / 2 ) + start;
            int midValue = mtn.get(mid);
            if ( midValue > mtn.get(mid+1) ) {
                // peak = midValue;
                peakIndex = mid;
                end = mid - 1;
            }
            else {
                start = mid + 1;
            }
        }
        return peakIndex;
    }

    private int searchAsc(MountainArray mtn, int start, int end, int target) {
        int result = -1;
        while ( start <= end ) {
            int mid = start + ((end - start) / 2);
            int midValue = mtn.get(mid);
            if ( midValue == target ) {
                return mid;
            }
            else if ( midValue < target ) {
                //look to the right
                start = mid + 1;
            }
            else {
                //look to the left
                end = mid - 1;
            }
        }
        return result;
    }

    private int searchDesc(MountainArray mtn, int start, int end, int target) {
        int result = -1;
        while ( start <= end ) {
            int mid = start + ((end - start) / 2);
            int midValue = mtn.get(mid);
            if ( midValue == target ) {
                return mid;
            }
            else if ( midValue < target ) {
                //look to the left
                end = mid - 1;
            }
            else {
                //look to the right
                start = mid + 1;
            }
        }
        return result;
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
    public int length();
    public int get(int index);
}

class MountainImpl implements MountainArray {
    private int[] array;

    public MountainImpl(int[] array) {
        this.array = array;
    }

    @Override
    public int length() {
        return array.length;
    }

    @Override
    public int get(int index) {
        return array[index];
    }
}
