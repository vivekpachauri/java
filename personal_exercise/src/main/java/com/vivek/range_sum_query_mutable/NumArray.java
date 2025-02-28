package com.vivek.range_sum_query_mutable;

public class NumArray {
    private int[] nums;
    private SegmentTree segmentTree;
    public NumArray(int[] nums) {
        this.nums = new int[nums.length];
        this.nums[0] = nums[0];
        for ( int i = 1; i < nums.length; i++ ) {
            this.nums[i] = nums[i] + this.nums[i-1];
        }
        this.segmentTree = new SegmentTree(nums);
    }
    
    public void updateLinear(int index, int val) {
        int diff = index == 0 ? this.nums[index] - val : (this.nums[index] - this.nums[index - 1]) - val;
        for ( int i = index; i < this.nums.length; i++ ) {
            this.nums[i] -= diff;
        }
    }
    
    public int sumRange(int left, int right) {
        return this.segmentTree.sumRange(left, right);
    }
    public void update(int index, int val) {
        this.segmentTree.update(index, val);
    }
    public int sumRangeAsArray(int left, int right) {
        if ( left == 0 ) {
            return this.nums[right];
            
        }
        return this.nums[right] - this.nums[left-1];
    }

    public static void main(String[] args) {
        int[] nums = {1, 3, 5};
        NumArray numArray = new NumArray(nums);

        System.out.println(numArray.sumRange(0, 2)); // Output: 9
        //numArray.update(1, 2);
        //System.out.println(numArray.sumRange(0, 2)); // Output: 8

        //numArray.updateLinear(1, 2);
        //System.out.println(numArray.sumRangeAsArray(0, 2)); // Output: 8

        int[] nums2 = {-2, 0, 3, -5, 2, -1};
        NumArray numArray2 = new NumArray(nums2);

        System.out.println(numArray2.sumRange(0, 2)); // Output: 1
        System.out.println(numArray2.sumRange(2, 5)); // Output: -1
        System.out.println(numArray2.sumRange(0, 5)); // Output: -3
    }

    private static class SegmentTree {
        public int nums[];
        public SegmentTree(int[] nums) {
            this.nums = new int[nums.length * 4];
            initRec(0, 0, nums.length - 1, this.nums, nums);
        }

        public int initRec(int node, int start, int end, int[] arr, int[] source) {
            //if root then initialize the lett and right and then assign the current value to the sum of the values returned
            //by the left and right sub-array
            //if called for a single value then simply assign and return
            if ( start >= end ) {
                arr[node] = source[start];
                return source[start];
            }
            else {
                //call left
                int leftNodeIndex = 2 * node + 1;
                int rightNodeIndex = 2 * node + 2;
                //mid is length/2 = (end + 1) - start
                int mid = (end + 1  - start) / 2; // 0 indexed so to get the length we have to add 1 to end
                int leftSum = initRec(leftNodeIndex, start, (mid - 1), arr, source);
                //call right
                int rightSum = initRec(rightNodeIndex, (start + mid), end, arr, source);
                //use the sum to initialize this
                arr[node] = leftSum + rightSum;
                return leftSum + rightSum;
            }
        }

        public int sumRange(int left, int right) {
            /*
             * we know the range at the root, it is 0 to length(nums) / 4 then return root value
             */
            return this.sumRangeRec(left, right, 0, ((this.nums.length / 4) - 1), 0);
        }

        private void update(int index, int val) {

        }

        private int sumRangeRec(int left, int right, int leftEdge, int rightEdge, int level) {
            //0 to num.length - 1 --- level 0 and return the value at root
            //if entirely in the left child then make one rec call and return the result
            //if entirely in the right child then make one rec call and return the result
            //if in both left and right child then make two calls and return the sum
            //how to calculate the left and right edge of the segment
            //level 0 left = 0 right = num.length - 1
            //level 1 left child -> left = 0 right = mid - 1
            if ( left > right ) {
                return 0;
            }
            if ( left == leftEdge && right == rightEdge) {
                return this.nums[level];
            }
            int mid = ((rightEdge + 1 - leftEdge) / 2) - 1 + leftEdge;
            if ( right <= mid ) {
                //the range doesn't exist in the right sub tree of this one, only need to make one call
                return sumRangeRec(Math.max(left, leftEdge), right, leftEdge, mid, 2 * level + 1);
            }
            else if ( left > mid) {
                //the range doesn't exist in the left sub-tree of this one, only need to make one call in the sub tree to the 
                return sumRangeRec(left, Math.min(right, rightEdge), mid + 1, rightEdge, 2 * level + 2);
            }
            else {
                int leftResult = sumRangeRec(Math.max(left, leftEdge), right, leftEdge, mid, 2 * level + 1);
                int rightResult = sumRangeRec(left, Math.min(right, rightEdge), mid + 1, rightEdge, 2 * level + 2);
                return leftResult + rightResult;
            }

        }

    }
}
/* 

    [1,2,3,4,5,6] (start = 0, end = 5)
    [-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1]
--------------------------------------------------------
    [1,2,3] [start = 0, end = 2]
    [-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1]

    [4,5,6] [start = 3, 5]
    [-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1]
    --------------------------------------------------------
    [1] [start = 0, end = 0]
    [-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1]

    [2,3] [start = 1, end = 2]
    [-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1]

    [4, 4]
    [-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1]

    [5,6]
    [-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1]
    --------------------------------------------------------

    [1,2,3,4,5,6,7,8,9,10,11,12]
    [0,1,2,3,4,5,6,7,8,9,10,11]
    by index
    end - start / 2 = 11 - 0 / 2 = 5
    
    by n
    end - start = 12 / 2 = 6
    0 to (6-1=5)

    [7,8,9,10,11,12]
    [6,7,8,9,10,11]
    by n
    6 / 2 = 3
    6 to (start + mid -1)
    [mid to end]

    [1,2,3,4,5,6] (start = 0, end = 5)
    [-1,-1,-1,-1,-1,-1
    ,-1,-1,-1,-1-1,-1
    ,-1,-1,-1,-1-1,-1
    ,1,2,3,4,5,6]


                                             n-0
                          [1,2,3]-1                              [4,5,6]-2
                 [1]-3                [2,3]-4                   [4]-5            [5,6]-6
                                  [2]-9      [3]-10                          [5]-13    [6]-14




                                                        n-0
                                  [1,2,3]-1                              [4,5,6]-2
                         [1]-6                [2,3]-4                   [4]-9            [5,6]-6
                                          [2]-7      [3]-8                          [5]-10    [6]-11

[-1 -1 -1 -1 -1, 11, 1, 2, 3, 4, 5, 6]
                                          

            
                                             n-0 [1,3,5]
                          [1]-1                              [3,5]-2
                 [1]-3                [2,3]-4                   [4]-5            [5,6]-6
                                  [2]-9      [3]-10                          [5]-13    [6]-14




                                                        n-0
                                  [1,2,3]-1                              [4,5,6]-2
                         [1]-6                [2,3]-4                   [4]-9            [5,6]-6
                                          [2]-7      [3]-8                          [5]-10    [6]-11

[-1 -1 -1 -1 -1, 11, 1, 2, 3, 4, 5, 6] */