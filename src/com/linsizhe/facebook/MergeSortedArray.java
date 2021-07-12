package com.linsizhe.facebook;

// https://leetcode.com/problems/merge-sorted-array/
public class MergeSortedArray {
    class Solution {
        public void merge(int[] nums1, int m, int[] nums2, int n) {
            int i = m - 1;
            int j = n - 1;
            int k = m + n - 1;
            // making from backward, so we can make it in-place.
            while (i >= 0 || j >= 0) {
                // when i , j  reach end
                // note here that condition is < 0 when it already crossed boundary!
                if (i < 0) {
                    nums1[k--] = nums2[j--];
                } else if (j < 0) {
                    nums1[k--] = nums1[i--];
                } else if (nums1[i] > nums2[j]) {
                    nums1[k--] = nums1[i--];
                } else {
                    nums1[k--] = nums2[j--];
                }
            }
        }
    }
}
