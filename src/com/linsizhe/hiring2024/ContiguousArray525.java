package com.linsizhe.hiring2024;

import java.util.HashMap;

// https://leetcode.com/problems/contiguous-array/description/
public class ContiguousArray525 {
    public int findMaxLength(int[] nums) {
        int count = 0;
        HashMap<Integer, Integer> ctToIdx = new HashMap();
        int res = 0;
        ctToIdx.put(0, -1);
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                count++;
            } else {
                count--;
            }
            if (!ctToIdx.containsKey(count)) {
                ctToIdx.put(count, i);
            } else {
                res = Math.max(res, i - ctToIdx.get(count));
            }
        }
        return res;
    }
}
