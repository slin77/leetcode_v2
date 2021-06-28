package com.linsizhe.facebook;

import java.util.Random;

// https://leetcode.com/problems/random-pick-with-weight/
public class RandomPickWIthWeight2nd {
    Random rand;
    int[] w;
    public RandomPickWIthWeight2nd(int[] w) {
        for (int i = 1; i < w.length; i++) {
            w[i] = w[i] + w[i - 1];
        }
        this.w = w;
        rand = new Random();
    }

    public int pickIndex() {

        int left = 0;
        int right = w.length - 1;


        int tag = rand.nextInt(w[w.length - 1]);

        // find the first index greater than tag
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (w[mid] <= tag) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }
}
