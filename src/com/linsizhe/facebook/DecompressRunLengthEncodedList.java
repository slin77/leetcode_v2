package com.linsizhe.facebook;

// https://leetcode.com/problems/decompress-run-length-encoded-list/
public class DecompressRunLengthEncodedList {
    public int[] decompressRLElist(int[] nums) {
        int total = 0;
        for (int i = 0; i < nums.length; i+=2) {
            total += nums[i];
        }
        int[] out = new int[total];
        int ptr = 0;
        for (int i = 0; i < nums.length; i += 2) {
            for (int j = 0; j < nums[i]; j++) {
                out[ptr++] = nums[i + 1];
            }
        }
        return out;
    }
}
