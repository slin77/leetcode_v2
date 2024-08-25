package com.linsizhe.hiring2024;

import java.util.HashMap;

// https://leetcode.com/problems/maximum-size-subarray-sum-equals-k/
public class MaxSubarraySizeEqualToK325 {
    public int maxSubArrayLen(int[] nums, int k) {
        // nums[i] - nums[j] = k
        HashMap<Integer, Integer> sumToIdx = new HashMap();
        int max = 0;
        int count = 0;
        //[1, 0, 5, 3, 6]
        sumToIdx.put(0, -1);
        for (int i = 0; i < nums.length; i++) {
            count += nums[i];
            if (!sumToIdx.containsKey(count)) {
                sumToIdx.put(count, i);
            }
            if (sumToIdx.containsKey(count - k)) {
                max = Math.max(max, i - sumToIdx.get(count - k));
            }
        }
        return max;
    }
}
