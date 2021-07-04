package com.linsizhe.facebook;

// https://leetcode.com/problems/find-kth-bit-in-nth-binary-string/
public class FindKthBiInNthBinaryString {
    public char findKthBit(int n, int k) {
        // 1, 3, 7, 15, 31, 63
        int size = (int) Math.pow(2, n) - 1;
        if (size == 1) return '0';
        if (k - 1 == size / 2) {
            return '1';
        } else if (k - 1 < size / 2) {
            return findKthBit(n - 1, k);
        } else {
            // if in another half, count from end. and invert
            return invert(findKthBit(n - 1, size - k + 1));
            // size - (k - 1)
        }
    }

    char invert(char c) {
        if (c == '0') return '1';
        return '0';
    }
}
