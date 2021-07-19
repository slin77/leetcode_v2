package com.linsizhe.facebook;

//https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/
public class FindFirstAndTheLastPositionofElementInSortedArray {
    public int[] searchRange(int[] nums, int target) {
        if (nums.length == 0) return new int[]{-1, -1};
        int start = 0;
        int end = nums.length - 1;
        int[] out = new int[2];
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] >= target) {
                end = mid - 1;
            } else if (nums[mid] < target) {
                start = mid + 1;
            }
        }
        // end is the first element smaller than target.
        // not found cases. 1.[....end]start. 2. end[start......] 3.[.....start end......]
        if (start >= nums.length || start < 0 || nums[start] != target) {
            return new int[]{-1, -1};
        }
        // start is the first element equal to target.
        out[0] = start;
        start = 0;
        end = nums.length - 1;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] > target) {
                end = mid - 1;
            } else if (nums[mid] <= target) {
                start = mid + 1;
            }
        }
        // end is the last element equal to target
        out[1] = end;
        return out;
    }
}
