package com.linsizhe.facebook;

//https://leetcode.com/problems/largest-bst-subtree/
public class LargestBSTSubtree {
    public int largestBSTSubtree(TreeNode root) {
        return helper(root)[0];
    }

    // keep track of
    // 1. largest size
    // 2. if include cur node *
    // 3. largest cur (for validation at given node)
    // 4. smallest cur (for validation at given node)
    public int[] helper(TreeNode node) {
        if (node == null) {
            return new int[]{0, 1, Integer.MIN_VALUE, Integer.MAX_VALUE};
        }

        int[] left = helper(node.left);
        int[] right = helper(node.right);

        if (node.left == null && node.right == null) {
            return new int[]{1, 1, node.val, node.val};
        }


        if (node.left == null) {
            //
            if (right[1] == 1 && right[3] > node.val) {
                return new int[]{right[0] + 1, 1, Math.max(right[2], node.val), Math.min(right[3], node.val)};
            } else {
                return new int[]{right[0], 0, Integer.MIN_VALUE, Integer.MAX_VALUE};
            }
        }

        if (node.right == null) {
            if (left[1] == 1 && left[2] < node.val) {
                return new int[]{left[0] + 1, 1, Math.max(left[2], node.val), Math.min(left[3], node.val)};
            } else {
                return new int[]{left[0], 0, Integer.MIN_VALUE, Integer.MAX_VALUE};
            }
        }

        // left or right invalid
        if (left[2] >= node.val || right[3] <= node.val || left[1] == 0 || right[1] == 0) {
            return new int[]{Math.max(left[0], right[0]), 0, Integer.MIN_VALUE, Integer.MAX_VALUE};
        }

        return new int[]{left[0] + right[0] + 1, 1, Math.max(Math.max(node.val, left[2]), right[2]), Math.min(Math.min(node.val, left[3]), right[3])};
    }
}
