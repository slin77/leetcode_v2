package com.linsizhe.facebook;

//https://leetcode.com/problems/closest-binary-search-tree-value/
public class ClosestBinarySearchTreeValue2nd {
    public int closestValue(TreeNode root, double target) {
        if (root == null) return Integer.MAX_VALUE;
        if (root.left == null && root.right == null) return root.val;
        // left tree can only be smaller, exclude
        if (root.val < target || root.left == null) {
            int right = closestValue(root.right, target);
            return getCloser(right, root.val, target);
            // right can only be even greater, exclude
        } else if (root.val > target || root.right == null) {
            int left = closestValue(root.left, target);
            return getCloser(left, root.val, target);
        } else {
            return root.val;
        }

    }

    private int getCloser(int a, int b, double target) {
        if (Math.abs((double) a - target) < Math.abs((double) b - target)) {
            return a;
        }
        return b;
    }
}
