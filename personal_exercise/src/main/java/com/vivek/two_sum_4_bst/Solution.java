package com.vivek.two_sum_4_bst;

import com.vivek.TreeNode;

public class Solution {
    public boolean findTarget(TreeNode root, int k) {
        
        //convert the tree into sorted list
        int countNodes = countNodes(root);
        int[] sortedArray = new int[countNodes];
        //perform pre-order traversal and store the elements in the array
        int index = 0;
        preOrderRec(root, sortedArray, index);
        System.out.println(sortedArray);
        return twoSumArray(sortedArray, k);

    }

    private int countNodes(TreeNode node) {
        if ( node == null ) {
            return 0;
        }
        else {
            return 1 + countNodes(node.left) + countNodes(node.right);
        }
    }

    private int preOrderRec(TreeNode node, int[] arr, int index) {
        if ( node == null ) {
            return index;
        }
        else {
            index = preOrderRec(node.left, arr, index);
            arr[index++] = node.val;
            index = preOrderRec(node.right, arr, index);
            return index;
        }
    }

    private boolean twoSumArray(int[] values, int k) {
        int beginning = 0, end = values.length - 1;
        while ( beginning < end ) {
            int sum = values[beginning] + values[end];
            if ( sum == k ) {
                return true;
            }
            else if ( sum > k ) {
                //if the sum is greater than k then move the right index to left
                end--;
                
            }
            else {
                //if the sum is less than k then move the left index to right
                beginning++;
            }
        }
        return false;
    }
    public static void main(String[] args) {
        Solution solution = new Solution();

        // Test case 1
        TreeNode root1 = new TreeNode(5);
        root1.left = new TreeNode(3);
        root1.right = new TreeNode(6);
        root1.left.left = new TreeNode(2);
        root1.left.right = new TreeNode(4);
        root1.right.right = new TreeNode(7);
        int k1 = 9;
        System.out.println("Test case 1: " + solution.findTarget(root1, k1)); // Expected output: true

        // Test case 2
        TreeNode root2 = new TreeNode(5);
        root2.left = new TreeNode(3);
        root2.right = new TreeNode(6);
        root2.left.left = new TreeNode(2);
        root2.left.right = new TreeNode(4);
        root2.right.right = new TreeNode(7);
        int k2 = 28;
        System.out.println("Test case 2: " + solution.findTarget(root2, k2)); // Expected output: false

        // Test case 3
        TreeNode root3 = new TreeNode(2);
        root3.left = new TreeNode(1);
        root3.right = new TreeNode(3);
        int k3 = 4;
        System.out.println("Test case 3: " + solution.findTarget(root3, k3)); // Expected output: true

        // Test case 4
        TreeNode root4 = new TreeNode(2);
        root4.left = new TreeNode(1);
        root4.right = new TreeNode(3);
        int k4 = 1;
        System.out.println("Test case 4: " + solution.findTarget(root4, k4)); // Expected output: false
    }
}
