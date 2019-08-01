package com.linsizhe;


// https://leetcode.com/problems/search-in-rotated-sorted-array-ii/
// Similar to rotatedI for determining if left/right is sorted.
// need to handle dup.
public class SearchInRotatedSortedArrayII {
    public boolean search(int[] nums, int target) {
        int lo = 0;
        int hi = nums.length - 1;
        while (lo <= hi) {
            while (lo < hi && nums[lo] == nums[hi]) {
                // preprocess, cut all duplicates at left!!!
                lo++;
            }
            int mid = lo + (hi - lo) / 2;
            if (target == nums[mid]) {
                return true;
            }
            // mid is at left half array. Mid's left is sorted.
            if (nums[mid] > nums[nums.length - 1]) {
                if (nums[lo] <= target && nums[mid] > target) {
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
        return false;
    }
}
