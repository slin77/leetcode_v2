package com.linsizhe.hiring2024;

import java.util.Arrays;

// https://leetcode.com/problems/3sum-closest/
public class ThreeSumClosest {
    // 3sum still two pointers
    // fix one pointer and  move two other pointers
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int min = Integer.MAX_VALUE;
        int res = -1;
        for (int i =  0;  i < nums.length; i++) {
            int l = i + 1;
            int r = nums.length  - 1;
            while (l < r) {
                int sum = nums[i] + nums[l] + nums[r];
                int diff = Math.abs(sum - target);
                if (diff < min) {
                    min = diff;
                    res = sum;
                }
                if (sum > target) {
                    r--;
                } else if (sum < target) {
                    l++;
                } else {
                    return sum;
                }
            }
        }

        return res;
    }
}
