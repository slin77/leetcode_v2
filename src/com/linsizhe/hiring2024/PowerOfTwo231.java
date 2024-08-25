package com.linsizhe.hiring2024;

// https://leetcode.com/problems/power-of-two/description/
public class PowerOfTwo231 {
    public boolean isPowerOfTwo(int n) {
        if (n <= 0) return false;
        while (n > 2) {
            if (n % 2 != 0)  {
                return false;
            }
            n = n / 2;
        }
        return true;
    }
}
