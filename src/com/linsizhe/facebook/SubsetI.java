package com.linsizhe.facebook;

import java.util.ArrayList;
import java.util.List;

// https://leetcode.com/problems/subsets/
public class SubsetI {
    public List<List<Integer>> subsets(int[] nums) {
        ArrayList<List<Integer>> out = new ArrayList();
        ArrayList<Integer> cur = new ArrayList();
        for (int i = 0; i <= nums.length; i++) {
            dfs(nums, 0, out, cur, i);
        }
        return out;
    }

    private void dfs(int[] nums, int idx, List<List<Integer>> out, List<Integer> cur, int length) {
        if (cur.size() == length) {
            out.add(new ArrayList(cur));
            return;
        }
        // always use the next one!
        for (int i = idx; i < nums.length; i++) {
            cur.add(nums[i]);
            dfs(nums, i + 1, out, cur, length);// note it is i + 1 not idx + 1
            cur.remove(cur.size() - 1);
        }
    }
}
