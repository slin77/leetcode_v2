package com.linsizhe.facebook;

// https://leetcode.com/problems/remove-element/
public class RemoveElement {
    public int removeElement(int[] nums, int val) {
        int start = 0;
        int end = nums.length;
        while (start < end) {
            if (nums[start] == val) {
                // tip we dont update start here
                // so if we swap with the target number
                // next pass will still do (but tail is different!)
                nums[start] = nums[end - 1];
                end--;
            } else {
                start++;
            }
        }
        return end;
    }
}
