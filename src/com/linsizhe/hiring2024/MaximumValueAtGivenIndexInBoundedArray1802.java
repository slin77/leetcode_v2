package com.linsizhe.hiring2024;

// https://leetcode.com/problems/maximum-value-at-a-given-index-in-a-bounded-array/
public class MaximumValueAtGivenIndexInBoundedArray1802 {
    public int maxValue(int n, int index, int maxSum) {
        long l = 1;
        long r = maxSum;
        long idx = index;
        while (l <= r) {
            long mid = l + (r - l) / 2;

            // TLE
            long sum = 0;
            long cur = mid;

            for (long i = idx; i >= 0; i--) {
                if (cur == 1) {
                    sum += i - 0 + 1;
                    break;
                }
                sum += cur--;
            }
            cur = mid - 1;
            for (long i = idx + 1; i < n; i++) {
                if (cur == 1) {
                    sum += n - 1 - i + 1;
                    break;
                }
                sum += cur--;
            }

            if (sum <= maxSum) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return (int) r;
    }
}
