package com.linsizhe.facebook;

// https://leetcode.com/problems/range-sum-of-bst/
public class RangeSumOfBST {
    int cum = 0;
    public int rangeSumBST(TreeNode root, int low, int high) {
        dfs(root, low, high);
        return cum;
    }

    public void dfs(TreeNode root, int low, int high) {
        if (root == null) return;
        if (root.val > high) {
            dfs(root.left, low, high);
        }  else if (root.val < low) {
            dfs(root.right, low, high);
        } else {
            cum += root.val;
            dfs(root.left, low, high);
            dfs(root.right, low, high);
        }
    }
}
