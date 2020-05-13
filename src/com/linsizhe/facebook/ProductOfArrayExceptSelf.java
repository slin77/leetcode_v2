package com.linsizhe.facebook;

// one iteration from left to right as product of all lefts
// another from right to left as product of all rights
// https://leetcode.com/problems/product-of-array-except-self/
public class ProductOfArrayExceptSelf {
    public int[] productExceptSelf(int[] nums) {
        int[] res = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            res[i] = i == 0 ? 1 : res[i - 1] * nums[i - 1];
        }
        // a better way:
        // we can use a num to do this rather than another array
        // res[i] * num, and num is cummulative product in each iteration
        // from right.
        int[] res2 = new int[nums.length];
        for (int i = nums.length - 1; i >= 0; i--) {
            res2[i] = i == (nums.length - 1) ? 1 : res2[i + 1] * nums[i + 1];
        }
        //  12 12 4 1
        for (int i = 0; i < res.length; i++) {
            res[i] *=  res2[i];
        }
        return res;
    }
}
