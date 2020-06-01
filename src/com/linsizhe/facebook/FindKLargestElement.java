package com.linsizhe.facebook;

// https://leetcode.com/problems/kth-largest-element-in-an-array/
public class FindKLargestElement {
    // public int findKthLargest(int[] nums, int k) {
    //     PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
    //     for (int i : nums) {
    //         pq.add(i);
    //     }
    //     int out = 0;
    //     for (int i = 0; i < k; i++) {
    //         out = pq.poll();
    //     }
    //     return out;
    // }

    // quick select
    //
    public int findKthLargest(int[] nums, int k) {
        k = nums.length - k + 1;
        return findKthSmallest(nums, 0, nums.length - 1, k);
    }

    public int findKthSmallest(int[] nums, int left, int right, int k) {
        if (left == right) {
            return nums[left];
        }
        int idx = partition(nums, left, right);
        int count = idx - left + 1;// # of less + pivot
        if (count == k) {
            return nums[idx];
        } else if (count < k) {
            return findKthSmallest(nums, idx + 1, right, k - count);
        } else {
            return findKthSmallest(nums, left, idx - 1, k);
        }
    }

//    partition as less, pivot, greater
//    return pivot
    public int partition(int[] nums, int left, int right) {
        int pivotVal = nums[right];
        int counter = left;
        // counter points to 1st uncleaned pos in the arr
        // i scan through whole list
        // any fit we put into uncleaned pos. note: right here is idx so dont confused with
        // length (< length - 1 wrong!)
        for (int i = left; i < right; i++) {
            if (nums[i] <= pivotVal) {
                swap(nums, i, counter++);
            }
        }
        swap(nums, right, counter);
        return counter;
    }

    public void swap(int[] nums, int a, int b) {
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }
}
