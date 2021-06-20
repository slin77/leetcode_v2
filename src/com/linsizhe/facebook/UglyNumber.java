package com.linsizhe.facebook;

// https://leetcode.com/problems/ugly-number/submissions/
public class UglyNumber {
    public boolean isUgly(int n) {
        if (n == 0) return false;
        if (n == 1 || n == 3 || n == 2 || n == 5) return true;

        if (n % 2 == 0) {
            return isUgly(n / 2);
        }

        if (n % 3 == 0) {
            return isUgly(n / 3);
        }

        if (n % 5 == 0) {
            return isUgly(n / 5);
        }

        return false;

    }
}
