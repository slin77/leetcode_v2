package com.linsizhe.hiring2024;

import com.linsizhe.facebook.TreeNode;

import java.util.ArrayList;
import java.util.List;

//  https://leetcode.com/problems/path-sum-ii/description/
public class PathSumII113   {
    List<List<Integer>> out = new ArrayList<>();
    int targetSum;

    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        this.targetSum = targetSum;
        ArrayList<Integer> list = new ArrayList<Integer>();
        dfs(root, 0, new ArrayList<Integer>());
        return out;
    }

    // backtracking, update at current node is easist!
    void dfs(TreeNode cur, int curSum, List<Integer> curPath) {
        if  (cur == null) return;

        curPath.add(cur.val);
        curSum += cur.val;

        if (cur.left == null && cur.right == null) {
            if (curSum == targetSum) {
                out.add(List.copyOf(curPath));
            }
        }

        dfs(cur.left, curSum, curPath);
        dfs(cur.right, curSum, curPath);

        curPath.remove(curPath.size() - 1);
    }
}
