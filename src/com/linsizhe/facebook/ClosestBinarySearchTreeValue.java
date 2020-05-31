package com.linsizhe.facebook;

import java.util.Stack;

// https://leetcode.com/problems/closest-binary-search-tree-value/

// in order traversal
public class ClosestBinarySearchTreeValue {
    double lastDiff;
    int lastVal;

    public int closestValue(TreeNode root, double target) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        lastDiff = Double.MAX_VALUE;
        int lastVal = Integer.MAX_VALUE;
        while (cur != null) {
            stack.add(cur);
            cur = cur.left;
        }
        while (!stack.isEmpty()) {
            cur = stack.pop();
            double curDiff = Math.abs(cur.val - target);
            if (curDiff >= lastDiff) {
                return lastVal;
            }
            lastDiff = curDiff;
            lastVal = cur.val;
            cur = cur.right;
            while (cur != null) {
                stack.add(cur);
                cur = cur.left;
            }
        }
        return lastVal;
    }
}
