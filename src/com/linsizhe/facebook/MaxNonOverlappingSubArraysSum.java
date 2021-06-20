package com.linsizhe.facebook;

import java.util.HashSet;

// https://leetcode.com/problems/maximum-number-of-non-overlapping-subarrays-with-sum-equals-target/
public class MaxNonOverlappingSubArraysSum {
    public int maxNonOverlapping(int[] nums, int target) {
        // two sum!
        int[] cum = new int[nums.length];
        cum[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            cum[i] = cum[i - 1] + nums[i];
        }
        int ans = 0;

        // now is interval schedule problem.
        // to schedule most internval in
        // use greedy, sorted by finished time!
        // schedule on after another.
        HashSet<Integer> validPrev = new HashSet();
        validPrev.add(0); // dont forget 0! otherwise those sum from begining wont count!
        for (int i = 0; i < cum.length; i++) {
            int cur = cum[i];
            if (validPrev.contains(cur - target)) {
                // after we schedule interval in
                // all previous index can not be used as
                // start idx
                validPrev = new HashSet();
                ans++;
            }
            validPrev.add(cur);
        }
        return ans;
    }
}
