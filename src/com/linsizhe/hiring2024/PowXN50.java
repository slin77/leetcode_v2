package com.linsizhe.hiring2024;

// https://leetcode.com/problems/powx-n/?envType=company&envId=facebook&favoriteSlug=facebook-six-months
public class PowXN50 {
    public double myPow(double x, int n) {

        if (n == Integer.MIN_VALUE) {
            return 1 / (myPow(x, Integer.MAX_VALUE) * x);
        }
        if (n < 0) {
            return 1 / myPow(x, Math.abs(n));
        }
        if (n == 1) {
            return x;
        }
        if (n == 0) {
            return 1;
        }
        if (n % 2 == 0) {
            return myPow(x * x, n / 2);
        }
        return x * myPow(x, n - 1);
    }
}
