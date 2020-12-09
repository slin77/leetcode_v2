package com.linsizhe.facebook;

// https://leetcode.com/problems/max-consecutive-ones-iii/
// similar: leetcode 485, 487
// using general template for sliding window algorithm.
public class MaxConsecutiveOnesIII {
    public int longestOnes(int[] A, int K) {
        int start = 0;
        int end = 0;
        int max = 0;
        int zeros = 0;
        while (end < A.length) {
            // update end state (what state changed when end move)
            if (A[end] == 0) {
                zeros++;
            }
            // condition for moving start pointer to keep a "good window"
            while (zeros > K) {
                // update start state
                if (A[start] == 0) {
                    zeros--;
                }
                // update start pointer
                start++;
            }
            // a good window after "while"
            max = Math.max(max, end - start + 1);
            // update end pointer
            end++;
        }
        return max;
    }
}
