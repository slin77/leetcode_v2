package com.linsizhe;

public class lowestCommonAncestor {
    public class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        return lca(root, p, q);
    }


    // Return the LCA from given node for p and q OR either is found.
    public TreeNode lca(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return null;
        }
        //
        if (root.val == p.val || root.val == q.val) {
            return root;
        }
        TreeNode left = lca(root.left, p, q);
        TreeNode right = lca(root.right, p, q);
        if (left == null && right == null) return null;
        if (left == null) return right;
        if (right == null) return left;
        // Key point here:
        // 1> p in left 2> q in right
        // we have this unreal condition here since we loose our condition on line 21
        if (left != null && right != null) {
            return root;
        }
        return null;
    }
}
