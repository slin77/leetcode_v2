package com.linsizhe.hiring2024;

import java.util.ArrayList;
import java.util.List;


// https://leetcode.com/problems/find-all-duplicates-in-an-array/
public class FindAllDuplicatesInArray442 {
    public List<Integer> findDuplicates(int[] nums) {
        List<Integer> out = new ArrayList();
        int i = 0;
        // cycle sort
        // sorting an array of distict elements;
        while (i < nums.length) {
            int n  = nums[i];
            int actualIdx = nums[i] - 1;
            if (nums[i] != nums[actualIdx]) {
                int temp = nums[i];
                nums[i] = nums[actualIdx];
                nums[actualIdx] = temp;
            } else {
                i++;
            }
        }
        for (int j = 0; j < nums.length; j++) {
            if (nums[j] != j + 1) {
                out.add(nums[j]);
            }
        }
        return out;
    }
}
