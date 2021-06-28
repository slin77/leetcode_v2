package com.linsizhe.facebook;

import java.util.HashMap;

//https://leetcode.com/problems/continuous-subarray-sum/
public class ContinuousSubarraySum2nd {
    public boolean checkSubarraySum(int[] nums, int k) {
        //presum of mods
        HashMap<Integer, Integer> presum = new HashMap();
        presum.put(0, -1);
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            nums[i] = sum % k;
            int idx = presum.getOrDefault(nums[i], i + 1);
            // i - idx = 1 will be just one char at i.
            if (i - idx > 1) return true;
            presum.put(nums[i], Math.min(i, idx));
        }
        return false;
    }
}
