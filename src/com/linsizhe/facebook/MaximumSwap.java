package com.linsizhe.facebook;


// https://leetcode.com/problems/maximum-swap/

// record the latest occurrance of 0 - 9 in the num
// for each digit from left to right traverse the digit large than it
// if any have last occur later than cur idx
// we can swap.
public class MaximumSwap {

    public int maximumSwap(int num) {
        char[] chars = String.valueOf(num).toCharArray();
        int[] digits = new int[chars.length];
        for (int i = 0; i < digits.length; i++) {
            digits[i] = chars[i] - '0';
        }
        // last idx of 1 - 9
        int[] idx = new int[10];
        for (int i = 0; i < idx.length; i++) {
            idx[i] = -1;
        }
        for (int i = 0; i < digits.length; i++) {
            idx[digits[i]] = i;
        }
        for (int i = 0; i < digits.length; i++) {
            int cur = digits[i];
            for (int j = 9; j > cur; j--) {
                if (idx[j] > i) {
                    return swap(digits, i, idx[j]);
                }
            }
        }
        return num;
    }

    public int swap(int[] digits, int a, int b) {
        int temp = digits[a];
        digits[a] = digits[b];
        digits[b] = temp;
        int out = 0;
        for (int i = 0; i < digits.length; i++) {
            out = out*10 + digits[i];
        }
        return out;
    }
}
