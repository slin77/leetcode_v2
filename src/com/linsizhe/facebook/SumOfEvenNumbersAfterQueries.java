package com.linsizhe.facebook;


// https://leetcode.com/problems/sum-of-even-numbers-after-queries/
public class SumOfEvenNumbersAfterQueries {
    public int[] sumEvenAfterQueries(int[] nums, int[][] queries) {
        int total = 0;
        int[] out = new int[queries.length];
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] % 2 == 0) {
                total += nums[i];
            }
        }
        int idx = 0;
        for (int[] query : queries) {
            int i = query[1];
            int val = query[0];

            if (val % 2 == 0) {
                if (nums[i] % 2 == 0) {
                    total += val;
                }
            } else {
                if (nums[i] % 2 != 0) {
                    total += val + nums[i];
                } else {
                    total -= nums[i];
                }
            }
            nums[i] += val;
            out[idx++] = total;
        }
        return out;
    }
}
