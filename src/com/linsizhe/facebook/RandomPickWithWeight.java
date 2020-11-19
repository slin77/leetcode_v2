package com.linsizhe.facebook;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

// https://leetcode.com/problems/random-pick-with-weight/submissions/
public class RandomPickWithWeight {
    private int[] cum;
    private int sum;
    private Random rand;

    public RandomPickWithWeight(int[] w) {
        cum = new int[w.length];
        cum[0] = w[0];
        sum += w[0];
        for (int i = 1; i < w.length; i++) {
            sum += w[i];
            cum[i] = sum;
        }
        this.rand = new Random();
    }

    public int pickIndex() {
        int num = rand.nextInt(sum) + 1;
        int idx = 0;
        int left = 0;
        int right = cum.length - 1;
        // return first idx equal to or greater
        // for binary search: (see BinarySearchAndVariantForDetails)
        // right ( left: push left take left
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (cum[mid] > num) {
                right = mid - 1;
            } else if (cum[mid] < num) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }

    public static void main(String[] args) {
        int[] arr = {0, 1, 1, 1};
        RandomPickWithWeight rp = new RandomPickWithWeight(arr);
        System.out.println(rp.pickIndex());
        System.out.println(rp.pickIndex());
        System.out.println(rp.pickIndex());
        System.out.println(rp.pickIndex());
        System.out.println(rp.pickIndex());
        System.out.println(rp.pickIndex());
        System.out.println(rp.pickIndex());
        System.out.println(rp.pickIndex());
        System.out.println(rp.pickIndex());
        System.out.println(rp.pickIndex());
        System.out.println(rp.pickIndex());
        System.out.println(rp.pickIndex());
    }
}
