package com.linsizhe.hiring2024;

import java.util.Arrays;

public class MinimumSwapToGroupAll1sTogether1151 {
    public int minSwaps(int[] data) {
        int ones = Arrays.stream(data).reduce(Integer::sum).getAsInt();
        int min = Integer.MAX_VALUE;
        int i = 0;
        int j = 0;
        int curMin = 0;
        // window of size of total number of ones
        // num of zeros is num of swap needed in each subarray
        while (j < data.length && i < data.length) {
            while (j - i < ones) {// j will point to next element so it is j - i
                curMin += data[j++] == 0 ? 1 : 0;
            }
            min = Math.min(curMin, min);// update only when j is in place
            curMin -= data[i++] == 0 ? 1 : 0;
        }
        return min;
    }
}
