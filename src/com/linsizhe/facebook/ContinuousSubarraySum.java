package com.linsizhe.facebook;

import java.util.HashMap;

// https://leetcode.com/problems/continuous-subarray-sum/
public class ContinuousSubarraySum {
    // a, b, c, d, e, f, g
    // (b + c + d) mod k = 0 <=> sum(a ... g) mod k - (a) mod k = 0
    public boolean checkSubarraySum(int[] nums, int k) {
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (k != 0) {
                nums[i] = sum % k;
            } else {
                nums[i] = sum;
            }
        }
        HashMap<Integer, Integer> cum = new HashMap();
        // for case of sum at i is a valid case!
        cum.put(0, -1);
        // nums[j] - nums[i] == k; Wrong!
        // nums[j] - nums[i] == 0; !!!!
        for (int i = 0; i < nums.length; i++) {
            int idx = cum.getOrDefault(nums[i], Integer.MAX_VALUE);
            if (i - idx > 1) {
                return true;
            }
            cum.put(nums[i], Math.min(idx, i));
        }
        return false;
    }

    // Similar of two sum but module!
    public boolean checkSubarraySum2(int[] nums, int k) {
        //presum of mods
        // note that still we need track idx, since presum(i - (i - 1)) will be
        // just array size of 1 !!!1
        HashMap<Integer, Integer> presum = new HashMap();
        presum.put(0, -1);
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            nums[i] = sum % k;
            int idx = presum.getOrDefault(nums[i], i + 1);
            if (i - idx > 1) return true;
            presum.put(nums[i], Math.min(i, idx));
        }
        return false;
    }
}
