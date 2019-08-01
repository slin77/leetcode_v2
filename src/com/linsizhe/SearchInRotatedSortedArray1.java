package com.linsizhe;

// https://leetcode.com/problems/search-in-rotated-sorted-array/
public class SearchInRotatedSortedArray1 {
    public int search(int[] nums, int target) {
        int lo = 0;
        int hi = nums.length - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (target == nums[mid]) {
                return mid;
            }
            // mid is at left half array. Mid's left is sorted.
            if (nums[mid] > nums[nums.length - 1]) {
                if (nums[lo] <= target && nums[mid] > target) {
                    // imported! <= otherwise the case when target is at lo (which is still this case)
                    // wouldn't get counted in this case.
                    hi = mid - 1;
                } else {
                    lo = mid + 1;
                }
                // else mid is at right half array. Mid's right is sorted.
            } else {
                if (nums[mid] < target && target <= nums[hi]) {
                    lo = mid + 1;
                } else {
                    hi = mid - 1;
                }
            }
        }
        return -1;
    }
}
