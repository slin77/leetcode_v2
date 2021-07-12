package com.linsizhe.facebook;

import java.util.ArrayList;
import java.util.List;

//https://leetcode.com/problems/all-nodes-distance-k-in-binary-tree/
public class AllNodesDistanceKInBinaryTree {
    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        ArrayList<Integer> out = new ArrayList<>();
        getDepth(root, target, k, out);
        return out;
    }

    public int getDepth(TreeNode root, TreeNode target, int k, List<Integer> res) {
        if (root == target) {
            dfs(root, k, res);
            return 0;
        }
        if (root == null) return -1;
        int left = getDepth(root.left, target, k, res);
        int right = getDepth(root.right, target, k, res);
        int dis = -1;
        if (left >= 0) {
            dis = left + 1;
            if (k - dis - 1 >= 0) {
                dfs(root.right, k - dis - 1, res);
            }
        } else if (right >= 0) {
            dis = right + 1;
            if (k - dis - 1 >= 0) {
                dfs(root.left, k - dis - 1, res);
            }
        }
        if (dis == k) {
            res.add(root.val);
        }
        return dis;
    }

    // start from root, add all node that in depth k to res
    public void dfs(TreeNode root, int k, List<Integer> res) {
        if (k < 0) return;
        if (root == null) return;
        if (k == 0) {
            res.add(root.val);
            return;
        }
        dfs(root.left, k - 1, res);
        dfs(root.right, k - 1, res);
    }
}
