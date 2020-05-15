package com.linsizhe.facebook;

import java.util.ArrayList;
import java.util.List;

// https://leetcode.com/problems/binary-tree-right-side-view/
// dfs post-order traverse:
// right up node will first get visited.
public class RightSideView {
    public List<Integer> rightSideView(TreeNode root) {
        ArrayList<Integer> out = new ArrayList();
        traverse(root, 0, out);
        return out;
    }

    public void traverse(TreeNode node, int level, List<Integer> view) {
        if(node == null) return;
        if (view.size() == level) {
            view.add(node.val);
        }
        traverse(node.right, level+1, view);
        traverse(node.left, level+1, view);
    }
}
