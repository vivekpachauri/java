package com.vivek.max_depth_binary_tree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import com.vivek.TreeNode;

public class Solution {

    private int maxDepthRec(TreeNode root, int currentDepth) {
        if ( root == null ) {
            return currentDepth;
        }
        else if ( root.left == null && root.right == null ) {
            return currentDepth + 1;
        }
        else {
            //currentDepth++;
            //maxDepth = maxDepth > currentDepth ? maxDepth : currentDepth;
            int maxDepthLeft = maxDepthRec(root.left, currentDepth+1);
            int maxDepthRight = maxDepthRec(root.right, currentDepth+1);
            return Math.max(maxDepthLeft, maxDepthRight);
        }
    }

    public int maxDepth(TreeNode root) {
        return maxDepthRec(root, 0);
    }

    public int maxDepthIncorrect(TreeNode root) {

        if ( root == null ) {
            return 0;
        }
        int i = 0;
        if ( i == 0 ) {
            return maxDepthRec(root, 0);
        }

        // the core idea is to perform DFS on the tree and maintain a length variable
        // and to correctly modify it when going into a child node
        int depth = 1;
        int maxDepth = 1;

        // bfs
        Queue<TreeNode> queue = new LinkedList<>();
        //Stack<TreeNode> stack = new Stack<>();
        queue.add(root);
        // while stack is not empty

        // remove a node from the stack, process the children until they are leaf and
        // keep increasing or decreasing the depth variable
        while (queue.isEmpty() == false) {
            TreeNode node = queue.poll();
            // process node, if it has children then
            if (node.left == null && node.right == null) {
                // this is a leaf and we will be going back up so decrease the depth
                depth--;
                maxDepth = maxDepth > depth ? maxDepth : depth;
                System.out.println("leaf node " + node.val);
            } else {
                depth++;
                maxDepth = maxDepth > depth ? maxDepth : depth;
                if (node.right != null) {
                    queue.add(node.right);
                }
                if ( node.left != null ) {
                    queue.add(node.left);
                }
            }
        }
        return maxDepth;

    }
    public static void main(String[] args) {
        Solution solution = new Solution();

        // Test case 1: Tree with single node
        TreeNode root1 = new TreeNode(1);
        System.out.println("Max Depth (Test case 1): " + solution.maxDepth(root1)); // Expected: 1

        // Test case 2: Tree with multiple levels
        TreeNode root2 = new TreeNode(1);
        root2.left = new TreeNode(2);
        root2.right = new TreeNode(3);
        root2.right.left = new TreeNode(4);
        root2.right.right = new TreeNode(5);
        System.out.println("Max Depth (Test case 2): " + solution.maxDepth(root2)); // Expected: 3

        // Test case 3: Empty tree
        TreeNode root3 = null;
        System.out.println("Max Depth (Test case 3): " + solution.maxDepth(root3)); // Expected: 0

        TreeNode root4 = new TreeNode(1);
        root4.left = new TreeNode(2);
        root4.right = new TreeNode(3);
        root4.left.left = new TreeNode(4);
        root4.left.right = new TreeNode(5);
        System.out.println("Max Depth (Test case 4): " + solution.maxDepth(root4)); // Expected: 3
    }

}
