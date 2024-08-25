package com.linsizhe.hiring2024;

// https://leetcode.com/problems/reverse-integer/
public class ReverseInteger7 {
    public int reverse(int x) {
        int res = 0;
        boolean isNeg = false;
        if (x < 0) {
            x = x * -1;
            isNeg = true;
        }
        // use x % 10 to script last digit
        // use x / 10 to move to next digit
        while (x > 0) {
            int digit = x % 10;
            // prevent overflow
            if (res > Integer.MAX_VALUE / 10) {
                return 0;
            }
            res = res * 10 + digit;

            x =  x / 10;
        }
        return isNeg ? res * -1 :  res;
    }
}
