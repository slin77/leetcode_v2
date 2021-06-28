package com.linsizhe.facebook;

import java.util.ArrayList;
import java.util.List;

// https://leetcode.com/problems/binary-tree-right-side-view/
public class BinaryTreeRightSideView {
    public List<Integer> rightSideView(TreeNode root) {
        if (root == null) return new ArrayList();

        List<Integer> left = rightSideView(root.left);
        List<Integer> right = rightSideView(root.right);

        if (right.size() < left.size()) {
            // think twice when it comes size and idx~
            for (int i = right.size(); i < left.size(); i++) {
                right.add(left.get(i));
            }
        }
        right.add(0, root.val);
        return right;
    }
}
