package com.linsizhe.facebook;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// https://leetcode.com/problems/subsets-ii/
public class SubsetII {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> out = new ArrayList<>();
        ArrayList<Integer> cur = new ArrayList();
        for (int i = 0; i <= nums.length; i++) {
            dfs(nums, 0, out, cur, i);
        }
        return new ArrayList<>(out);
    }

    private void dfs(int[] nums, int idx, List<List<Integer>> out, List<Integer> cur, int length) {
        if (cur.size() == length) {
            out.add(new ArrayList(cur));
            return;
        }
        // always use the next one!
        for (int i = idx; i < nums.length; i++) {
            // skip for dup, but dont skip for start of iteration
            if (i != idx && nums[i] == nums[i - 1]) {
                continue;
            }
            cur.add(nums[i]);
            dfs(nums, i + 1, out, cur, length);
            cur.remove(cur.size() - 1);
        }
    }
}
