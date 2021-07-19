package com.linsizhe.facebook;

// https://leetcode.com/problems/convert-sorted-array-to-binary-search-tree/
public class ConvertSortedArrayToBinarySearchTree {
    public TreeNode sortedArrayToBST(int[] nums) {
        return sortedArrayToBST(nums, 0, nums.length - 1);
    }

    public TreeNode sortedArrayToBST(int[] nums, int start, int end) {
        if (start > end) {
            return null;
        }
        int mid = start + (end - start) / 2;
        TreeNode node = new TreeNode(nums[mid]);
        TreeNode left = sortedArrayToBST(nums, start, mid - 1);
        TreeNode right = sortedArrayToBST(nums, mid + 1, end);
        node.left = left;
        node.right = right;
        return node;
    }
}
