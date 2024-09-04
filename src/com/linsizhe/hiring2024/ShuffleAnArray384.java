package com.linsizhe.hiring2024;

import java.util.Random;

// https://leetcode.com/problems/shuffle-an-array/
public class ShuffleAnArray384 {

    int[] original;
    int[] nums;
    Random rand;

    public ShuffleAnArray384(int[] nums) {
        this.original = nums.clone();
        this.nums = nums;
        rand = new Random();
    }

    public int[] reset() {
        this.nums = original.clone();
        return nums;
    }

    public int[] shuffle() {
        for (int i = 0; i < nums.length; i++) {
            // swap i with any behind
            // simiularte drawing a random number at i then remove from list
            int j = rand.nextInt(0, nums.length - i) + i; // i - l -> 0 - (l - i) + i
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }
        return nums;
    }
}
