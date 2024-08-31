package com.linsizhe.hiring2024;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// https://leetcode.com/problems/product-of-two-run-length-encoded-arrays/
public class ProductOfTwoLengthRunEncodedArrays1868 {
    public List<List<Integer>> findRLEArray(int[][] encoded1, int[][] encoded2) {
        int ptr1 = 0, ptr2 = 0;
        int f1 = -1, f2 = -1; // remain number in each pointer,
        // idea: update pointer when remaining f is zero
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        while (ptr2 < encoded2.length || ptr1 < encoded1.length) {
            // update f if any f was zero
            // pointers should be updated in prev interation
            if (f1 <= 0) {
                f1 = encoded1[ptr1][1];
            }

            if (f2 <= 0) {
                f2 = encoded2[ptr2][1];
            }

            int f = Math.min(f1, f2);
            int num = encoded1[ptr1][0] * encoded2[ptr2][0];
            if (res.size() >= 1) {
                List<Integer> prev = res.get(res.size() - 1);
                if (prev.get(0) == num) {
                    res.remove(res.size() - 1);
                    res.add(Arrays.asList(num, prev.get(1)  + f));
                } else {
                    res.add(Arrays.asList(num, f));
                }
            } else {
                res.add(Arrays.asList(num, f));
            }

            // update pointer if f is not enough
            f1 -= f;
            if (f1 <= 0) ptr1++;
            f2 -= f;
            if (f2 <= 0) ptr2++;
        }
        return res;
    }

    public List<List<Integer>> findRLEArray2(int[][] encoded1, int[][] encoded2) {
        int[] arr1 = decode(encoded1);
        int[] arr2 = decode(encoded2);
        int[] res = new int[arr1.length];
        for (int i = 0; i < arr1.length; i++) {
            res[i] = arr1[i] * arr2[i];
        }
        return encode(res);
    }

    private List<List<Integer>> encode(int[] in) {
        List<List<Integer>> res = new ArrayList();
        int count = 0;
        int prev = -1;
        for (int i = 0; i < in.length; i++) {
            if (in[i] != prev && count != 0) {
                res.add(Arrays.asList(in[i - 1], count));
                count = 1;
            } else {
                count++;
            }
            prev = in[i];
        }
        res.add(Arrays.asList(in[in.length - 1], count));
        return res;
    }

    int[] decode(int[][] encode) {
        int l = 0;
        for (int[] arr: encode) {
            l += arr[1];
        }
        int[] res = new int[l];
        int ptr = 0;
        for (int[] arr : encode) {
            for (int i = 0; i < arr[1]; i++) {
                res[ptr++] = arr[0];
            }
        }
        return res;
    }
}
