package com.linsizhe.snapchat;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

// https://leetcode.com/problems/sliding-window-median/
public class SlidingWindowMedian {
    public double[] medianSlidingWindow(int[] nums, int k) {
        PriorityQueue<Integer> low = new PriorityQueue<Integer>(Comparator.reverseOrder());
        PriorityQueue<Integer> hi = new PriorityQueue<Integer>(Comparator.naturalOrder());
        int l = 0;
        int r = l + k - 1;
        //Arrays.sort(nums, l, r + 1);
        int[] start = Arrays.copyOfRange(nums, l, r + 1);
        Arrays.sort(start);
        for (int i = 0; i <= r; i++) {
            if (i <= (l + r) / 2) {
                low.add(start[i]);
            } else {
                hi.add(start[i]);
            }
        }
        double median = 0;
        if (low.size() == hi.size()) {
            median = (double) ( (double) low.peek() + (double) hi.peek()) / 2;
        } else {
            median = low.peek();
        }
        double[] out = new double[nums.length - k + 1];
        int ptr = 0;
        out[ptr++] = median;
        while (r < nums.length - 1) {
            l++;
            r++;

            int remove = nums[l - 1];
            int add = nums[r];

            if (remove <= median) {
                low.remove(remove);
            } else {
                hi.remove(remove);
            }

            if (add <= median) {
                low.add(add);
            } else {
                hi.add(add);
            }

            // rebalance
            if (k % 2 == 0) {
                if (hi.size() > low.size()) {
                    low.add(hi.poll());
                } else if(low.size() > hi.size()) {
                    hi.add(low.poll());
                }
            } else {
                if (low.size() - hi.size() >= 2) {
                    hi.add(low.poll());
                } else if (hi.size() >= low.size()) {
                    low.add(hi.poll());
                }
            }

            if (low.size() == hi.size()) {
                median = (double) ( (double) low.peek() + (double) hi.peek()) / 2;
            } else {
                median = low.peek();
            }
            out[ptr++] = median;
        }
        return out;
    }
}
