package com.linsizhe.facebook;

//https://leetcode.com/problems/clumsy-factorial/
public class ClumsyFactorial {
    public int clumsy(int n) {
        int sign = 1;
        int cur = 0;
        char symbol = ' ';
        int out = 0;
        // basic calculator as state machine.
        for (int i = n; i > 0; i--) {
            if (symbol == ' ') {
                cur = i;
                symbol = '*';
            } else if (symbol == '*') {
                cur = cur * i;
                symbol = '/';

            } else if (symbol == '/') {
                cur = cur / i;
                symbol = '+';
            } else if (symbol == '+') {
                out += sign * cur;
                symbol = '-';
                sign = 1;
                cur = i;
            } else if (symbol == '-') {
                out += sign * cur;
                sign = -1;
                cur = i;
                symbol = '*';
            }
            int s = sign;
        }
        out += sign * cur;
        return out;
    }
}
