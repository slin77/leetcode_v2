package com.linsizhe.hiring2024;

import java.util.HashMap;

// https://leetcode.com/problems/longest-consecutive-sequence/
public class LongestConsectiveSequence128 {
    // union find to find parents!
    public int longestConsecutive(int[] nums) {
        HashMap<Integer, Integer> parent = new HashMap();
        for (int i = 0; i < nums.length; i++) {
            parent.put(nums[i], nums[i]);
        }
        for (int i = 0; i < nums.length; i++) {
            if (parent.containsKey(nums[i] - 1)) {
                union(nums[i], nums[i] - 1, parent);
            }
        }
        int max = 0;
        HashMap<Integer, Integer> count = new HashMap();
        for (int i : parent.keySet()) {
            count.put(root(i, parent), count.getOrDefault(root(i, parent), 0) + 1);
            max = Math.max(count.get(root(i, parent)), max);
        }
        return max;
    }

    int root(int i, HashMap<Integer, Integer> parent) {
        if (i == parent.get(i)) {
            return i;
        }
        while (parent.get(i) != i) {
            parent.put(i, parent.get(parent.get(i)));
            i = parent.get(i);
        }
        return i;
    }

    void union(int i, int j, HashMap<Integer, Integer> parent) {
        if (j > i) {
            union(j, i, parent);
        }

        int ri = root(i, parent);
        int rj = root(j, parent);

        parent.put(j, i);
    }
}
