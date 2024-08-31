package com.linsizhe.hiring2024;

// https://leetcode.com/problems/sort-colors/
public class SortColors75 {
    // counting sort
    public void sortColors(int[] nums) {
        int r = 0;
        int w = 0;
        int b = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) r++;
            if (nums[i] == 1) w++;
            if (nums[i] == 2) b++;
        }
        for (int i = 0 ; i < nums.length; i++) {
            if (r != 0) {
                nums[i] = 0;
                r--;
            }  else if  (w != 0) {
                nums[i] = 1;
                w--;
            } else if (b != 0) {
                nums[i] = 2;
                b--;
            }
        }
    }
}
