package com.linsizhe.facebook;

public class KthLargetElementInAnArray {
    public int findKthLargest(int[] nums, int k) {
        return findKthLargest(nums, k, 0, nums.length - 1);
    }

    public int findKthLargest(int[] nums, int k, int start, int end) {
        int pivot = quickSelect(nums, start, end);
        if (nums.length - pivot == k) {
            return nums[pivot];
        } else if (nums.length - pivot < k) {
            return findKthLargest(nums, k, start, pivot - 1);
        } else {
            return findKthLargest(nums, k, pivot + 1, end);
        }
    }

    public int quickSelect(int[] nums, int start, int end) {
        int pivot = nums[start];
        while (start < end) {
            while (start < end && nums[end] > pivot) end--;
            nums[start] = nums[end];
            while (start < end && nums[start] <= pivot) start++;
            nums[end] = nums[start];
        }
        nums[start] = pivot;
        // start left <= pivot value.
        return start;
    }
}
