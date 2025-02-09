package com.vivek.flatten_binary_tree;

import com.vivek.TreeNode;

public class Solution {
    public void flatten(TreeNode root) {
        TreeNode leftChild = root.left;
        TreeNode rightChild = root.right;
        flattenNode(rightChild, flattenNode(leftChild, root));
    }

    public TreeNode flattenNode(TreeNode node, TreeNode target) {
        //target.left = null;
        if ( node == null ) { 
            return target;
        }
        if ( node.left == null && node.right == null) {
            target.left = null;
            target.right = node;
            return target.right;
        }
        TreeNode leftChild = node.left;
        TreeNode rightChid = node.right;
        target.left = null;
        target.right = node;
        return flattenNode(rightChid, flattenNode(leftChild, target.right));
    }
public static void main(String[] args) {
    Solution solution = new Solution();

    // Test case 1
    TreeNode root1 = new TreeNode(1);
    root1.left = new TreeNode(2);
    root1.right = new TreeNode(5);
    root1.left.left = new TreeNode(3);
    root1.left.right = new TreeNode(4);
    root1.right.right = new TreeNode(6);
    solution.flatten(root1);
    printFlattenedTree(root1);

    // Test case 2
    TreeNode root2 = new TreeNode(1);
    root2.left = new TreeNode(2);
    root2.left.left = new TreeNode(3);
    solution.flatten(root2);
    printFlattenedTree(root2);

    // Test case 3
    TreeNode root3 = new TreeNode(1);
    root3.right = new TreeNode(2);
    root3.right.right = new TreeNode(3);
    solution.flatten(root3);
    printFlattenedTree(root3);
}

private static void printFlattenedTree(TreeNode root) {
    TreeNode current = root;
    while (current != null) {
        System.out.print(current.val + " ");
        current = current.right;
    }
    System.out.println();
}
}
