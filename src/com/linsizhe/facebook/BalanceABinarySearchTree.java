package com.linsizhe.facebook;

import java.util.ArrayList;

//https://leetcode.com/problems/balance-a-binary-search-tree/
public class BalanceABinarySearchTree {
    public TreeNode balanceBST(TreeNode root) {
        ArrayList<TreeNode> sorted = new ArrayList();
        inorder(root, sorted);
        return balanceBST(0, sorted.size() - 1, sorted);
    }

    public void inorder(TreeNode node, ArrayList<TreeNode> sorted) {
        if (node == null) return;
        inorder(node.left, sorted);
        sorted.add(node);
        inorder(node.right, sorted);
    }

    public TreeNode balanceBST(int start, int end, ArrayList<TreeNode> list) {
        if (start > end) return null;
        int mid = start + (end - start) / 2;
        TreeNode node = list.get(mid);
        TreeNode left = balanceBST(start, mid - 1, list);
        TreeNode right = balanceBST(mid + 1, end, list);
        node.left = left;
        node.right = right;
        return node;
    }
}
