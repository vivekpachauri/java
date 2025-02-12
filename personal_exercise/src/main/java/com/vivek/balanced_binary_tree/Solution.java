package com.vivek.balanced_binary_tree;

import com.vivek.TreeNode;

public class Solution {
    public boolean isBalanced(TreeNode root) {
        if ( root == null ) {
            return true;
        }
        else {

            int leftDepth = depth(root.left, 1);
            int rightDepth = depth(root.right, 1);
            if ( Math.abs(leftDepth - rightDepth) > 1 ) {
                return false;
            }
            else {

                return isBalanced(root.left) && isBalanced(root.right);
            }
        }
        /*
         * 
         */
    }

    public boolean isBalancedddd(TreeNode node) {
        //if current node is null then return true
        if (node == null ) {
            return true;
        }
        else if ( node.left == null && node.right == null ) {
            return true;
        }
        else {
            if ( node.left == null ) {
                if (isParent(node.right) ) {
                    return false;
                }
            }
            else if ( node.right == null ) {
                if ( isParent(node.left) ) {
                    return false;
                }
            }
            return isBalanced(node.left) && isBalanced(node.right);
        }
        //if left and right child are empty then return true

    }
    /*
     * at every level the difference in the left and right height should not be more than 1
     * therefore at every node if one child is null then the other cannot be a parent
     */

     private boolean isParent(TreeNode node) {
        if ( node == null ) {
            return false;
        }
        if ( node.left != null || node.right != null ) {
            return true;
        }
        else {
            return false;
        }
     }

    private int depth(TreeNode node, int depth) {
        if ( node == null ) {
            return depth;
        }
        else if ( node.left == null && node.right == null ) {
            return depth + 1;
        }
        else {
            int leftDepth = depth(node.left, depth+1);
            int rightDepth = depth(node.right, depth+1);
            return Math.max(leftDepth, rightDepth);
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        // Test case 1: Balanced tree
        TreeNode balancedRoot = new TreeNode(1);
        balancedRoot.left = new TreeNode(2);
        balancedRoot.right = new TreeNode(3);
        balancedRoot.left.left = new TreeNode(4);
        balancedRoot.left.right = new TreeNode(5);
        balancedRoot.right.left = new TreeNode(6);
        balancedRoot.right.right = new TreeNode(7);
        System.out.println("Test case 1 (Balanced): " + solution.isBalanced(balancedRoot)); // Expected: true

        // Test case 2: Unbalanced tree
        TreeNode unbalancedRoot = new TreeNode(1);
        unbalancedRoot.left = new TreeNode(2);
        unbalancedRoot.left.left = new TreeNode(3);
        unbalancedRoot.left.left.left = new TreeNode(4);
        System.out.println("Test case 2 (Unbalanced): " + solution.isBalanced(unbalancedRoot)); // Expected: false

        // Test case 3: Empty tree
        TreeNode emptyRoot = null;
        System.out.println("Test case 3 (Empty): " + solution.isBalanced(emptyRoot)); // Expected: true


        // Test case 4: Unbalanced tree from given values [1,2,2,3,null,null,3,4,null,null,4]
        TreeNode testCase4Root = new TreeNode(1);
        testCase4Root.left = new TreeNode(2);
        testCase4Root.right = new TreeNode(2);
        testCase4Root.left.left = new TreeNode(3);
        testCase4Root.right.right = new TreeNode(3);
        testCase4Root.left.left.left = new TreeNode(4);
        testCase4Root.right.right.right = new TreeNode(4);
        System.out.println("Test case 4 (Unbalanced from given values): " + solution.isBalanced(testCase4Root)); // Expected: false

        // Test case 5: Balanced tree from given values [1,2,2,3,3,null,null,4,4]
        TreeNode testCase5Root = new TreeNode(1);
        testCase5Root.left = new TreeNode(2);
        testCase5Root.right = new TreeNode(2);
        testCase5Root.left.left = new TreeNode(3);
        testCase5Root.left.right = new TreeNode(3);
        testCase5Root.left.left.left = new TreeNode(4);
        testCase5Root.left.left.right = new TreeNode(4);
        System.out.println("Test case 5 (Balanced from given values): " + solution.isBalanced(testCase5Root)); // Expected: true
    }
}

/*

                         1
        2                               2
    3       nil                     null    3
4                                               4 
                                            null

                                         */