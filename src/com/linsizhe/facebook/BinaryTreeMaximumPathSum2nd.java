package com.linsizhe.facebook;

// https://leetcode.com/problems/binary-tree-maximum-path-sum/
public class BinaryTreeMaximumPathSum2nd {
    int max;
    public int maxPathSum(TreeNode root) {
        max = Integer.MIN_VALUE;
        dfs(root);
        return max;
    }

    // Path sum include cur, not need to reach leaf
    public int dfs(TreeNode root) {
        if (root == null) return 0;
        // if left sum gives negatives, we assume it is not there.
        int left = Math.max(dfs(root.left), 0);
        int right = Math.max(dfs(root.right), 0);

        // this is the same pattern as we solve for Binary Tree diameter
        max = Math.max(left + root.val + right, max);

        return Math.max(left + root.val, right + root.val);
    }
}
