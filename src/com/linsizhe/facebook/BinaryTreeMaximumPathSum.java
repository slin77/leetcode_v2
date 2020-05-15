package com.linsizhe.facebook;

// note:
// a "path" in this problem is sth follow child-parent-child 一笔画

// key: recursion do one thing, while accumulator do another within recursion.
public class BinaryTreeMaximumPathSum {
    private int maxSoFar;
    public int maxPathSum(TreeNode root) {
        maxSoFar = Integer.MIN_VALUE;
        maxToIncludeCur(root);
        return maxSoFar;
    }

    // max down one way path we can get if we MUST have cur included
    public int maxToIncludeCur(TreeNode cur) {
        if (cur == null) {
            return 0;
        }

        int left = maxToIncludeCur(cur.left); // left plus max of left
        int right = maxToIncludeCur(cur.right); // right plus max of right

        int getFromLeft = Math.max(left, 0); // abandon the branch if left < 0
        int getFromRight = Math.max(right, 0); // abandon the branch if right < 0

        // we can utilize result (return value) for each recursion to get what we want for final answer.
        maxSoFar = Math.max(maxSoFar, getFromLeft + getFromRight + cur.val);

        return Math.max(getFromLeft, getFromRight) + cur.val;
    }
}
