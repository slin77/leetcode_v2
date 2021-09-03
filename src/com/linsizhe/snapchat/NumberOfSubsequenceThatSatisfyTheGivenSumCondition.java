package com.linsizhe.snapchat;

import java.util.Arrays;

// https://leetcode.com/problems/number-of-subsequences-that-satisfy-the-given-sum-condition/
public class NumberOfSubsequenceThatSatisfyTheGivenSumCondition {
    // note:1. 2 pointers need sort
    //      2. Subsequences == subset == order_does_not_matter.
    public int numSubseq(int[] nums, int target) {
        // order does not matter
        // subsequence <=> subset in array!
        // so order does not matter
        // problem become any i, j <= target --> sub array of element within i, j

        // two pointers like how we solve 2-sum.
        Arrays.sort(nums);
        int right = nums.length - 1;
        int left = 0;
        int out = 0;
        int mod = (int)1e9 + 7;
        int[] pow = new int[nums.length];
        pow[0] = 1;
        for (int i = 1; i < pow.length; i++) {
            pow[i] = (pow[i - 1] * 2) % mod;
        }
        // use two pointers to solve two sum
        while (left <= right) {
            if (nums[left] + nums[right] <= target) {
                // any number within left and right
                // can be take or throw
                // 2 ^ (right - left)
                out = (out + pow[right - left]) % mod;
                left++;
            } else {
                right--;
            }
        }
        return out;
    }
}
