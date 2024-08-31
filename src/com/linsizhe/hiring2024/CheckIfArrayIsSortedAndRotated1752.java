package com.linsizhe.hiring2024;
// https://leetcode.com/problems/check-if-array-is-sorted-and-rotated/description/
public class CheckIfArrayIsSortedAndRotated1752 {
    public boolean check(int[] nums) {
        boolean found = false;
        int count = 0;
        // element smaller than prev can only occur once
        for  (int i = 0; i < nums.length -1; i++) {
            if (nums[i + 1] < nums[i]) count++;
            if (count >  1) return false;
        }
        if (count == 1) {
            return nums[nums.length - 1] <= nums[0];
        }
        return true;
    }
}
