package com.linsizhe.snapchat;

public class LowestCommonAncestorOfABinaryTree {

    public class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
    }

    TreeNode lca;

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        find(root, p, q);
        return lca;
    }

    public int find(TreeNode root, TreeNode p, TreeNode q) {
        // we already find what we need
        // not need to continue calculation
        if (lca != null) return 0;
        if (root == null) return 0;
        int left = find(root.left, p, q);
        int right = find(root.right, p, q);
        if (root == p || root == q) {
            if (left == 1 || right == 1) {
                lca = root;
            }
            return 1;
        }
        if (left == 1 && right == 1) {
            this.lca = root;
        }
        return left + right;
    }
}
