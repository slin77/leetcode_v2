package com.linsizhe.facebook;

import java.util.HashMap;

// https://leetcode.com/problems/subarray-sum-equals-k/
public class SubArraySumEqualsK {
    public int subarraySum(int[] nums, int k) {
        int sum = 0;
        // note that there can be multiple previous sum of same value, so need hashmap.
        HashMap<Integer, Integer> presumCount = new HashMap();
        presumCount.put(0, 1);// dont forget get start idx!
        int out = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            out += presumCount.getOrDefault(sum - k, 0);
            presumCount.put(sum, presumCount.getOrDefault(sum, 0) + 1);
        }
        return out;
    }
}
