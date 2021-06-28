package com.linsizhe.facebook;

import java.util.HashSet;

// https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree-iv/
public class LowestCommonAncestorOfBSTIV {
    TreeNode lca = null;
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode[] nodes) {
        HashSet<Integer> tags = new HashSet();
        for (TreeNode n : nodes) {
            tags.add(n.val);
        }
        //return lca(root, tags);
        dfsSolve(root, tags);
        return lca;
    }

    public int dfsSolve(TreeNode root, HashSet<Integer> tags) {

        if (root == null) return 0;
        int left = dfsSolve(root.left, tags);
        int right = dfsSolve(root.right, tags);

        // either size is 0, then this node can not be first time LCA
        if (left == tags.size() || right == tags.size()) {
            return tags.size();
        }

        // both non 0m this is first time LCA
        if (left + right == tags.size()) {
            lca = root;
            return tags.size();
        }

        // when cur node also count in LCA
        if (tags.contains(root.val)) {
            if (left + right + 1 == tags.size()) {
                lca = root;
            }
            return left + right + 1;
        }

        return left + right;
    }

    public TreeNode lca(TreeNode root, HashSet<Integer> tags) {

        if (root == null) {
            return null;
        }

        if (tags.contains(root.val)) return root;

        // little waste of time since dfs will take traverse, lca will do again for
        // the branch that contains things.
        boolean l = dfs(root.left, tags);
        boolean r = dfs(root.right, tags);

        // any of left or right does not have any tags, that branch can not have LCA node.
        if (l && r) {
            return root;
        } else if (!l) {
            return lca(root.right, tags);
        } else if (!r) {
            return lca(root.left, tags);
        }

        return root;
    }

    boolean dfs(TreeNode root, HashSet<Integer> tags) {
        if (root == null) return false;
        if (tags.contains(root.val)) return true;
        return dfs(root.left, tags) || dfs(root.right, tags);
    }
}
