package com.vivek.min_depth_binary_tree;

import com.vivek.TreeNode;

public class Solution {
    private int maxDepthRec(TreeNode root, int currentDepth) {
        if ( root.left == null && root.right == null ) {
            return currentDepth + 1;
        }
        else if ( root.left != null && root.right != null ) {
            //currentDepth++;
            //maxDepth = maxDepth > currentDepth ? maxDepth : currentDepth;
            int maxDepthLeft = maxDepthRec(root.left, currentDepth+1);
            int maxDepthRight = maxDepthRec(root.right, currentDepth+1);
            return Math.min(maxDepthLeft, maxDepthRight);
        }
        else if ( root.left == null && root.right != null ) {
            return maxDepthRec(root.right, currentDepth+1);
        }
        else {
            return maxDepthRec(root.left, currentDepth+1);
        }
    }

    public int minDepth(TreeNode root) {
        return maxDepthRec(root, 0);
    }
}
