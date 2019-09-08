package com.linsizhe;

import java.util.Stack;


// point 1: -1 = + (-1) so each is actually an accumulator
// thought actually is recursion. Stack is for context switch. context variable: res, sign
public class BasicCalculator {
    public int calculate(String s) {
        char[] chars = s.toCharArray();
        int curValue = 0;
        int curSign = 1;
        int res = 0;
        Stack<Integer> saved = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = chars[i];
            if (c == ' ') {
                continue;
            }
            if (Character.isDigit(c)) {
                curValue = curValue * 10 + (c - '0');
            }

            if (c == '+') {
                res += curSign * curValue;
                curSign = 1;
                curValue = 0;
            }

            if (c == '-') {
                res += curSign * curValue;
                curSign = -1;
                curValue = 0;
            }

            if (c == '(') {
                saved.push(res);
                saved.push(curSign);
                res = 0;
                curSign = 1;
            }

            if (c == ')') {
                res += curSign * curValue; // we are still evaluating within the ()!
                curSign = saved.pop();
                curValue = res;
                res = saved.pop();
            }
        }
        res += curSign * curValue;
        return res;
    }

    // recursive solution similar to stack based. View (....) as a whole so it is still sign * (....) added to result.
    // key point: see a "(" try to get to its balancing ")" and call calculate recursively.
    public int calculateRecur(String s) {
        char[] chars = s.toCharArray();
        int curNum = 0;
        int sign = 1;
        int res = 0;

        int i = 0;
        while (i < s.length()) {
            char c = chars[i];
            if (Character.isDigit(c)) {
                curNum  = curNum * 10 + (c - '0');
            }

            if (c == '+') {
                res += curNum * sign;
                curNum = 0;
                sign = 1;
            }

            if (c == '-') {
                res += curNum * sign;
                curNum = 0;
                sign = -1;
            }

            if (c == '(') {
                int end = getEnd(s, i);
                res += sign * calculateRecur(s.substring(i + 1, end));
                sign = 0;
                i = end;
            }
            i++;
        }
        res += curNum * sign;
        return res;
    }

    private int getEnd(String s, int curPar) {
        int count = 0;
        for (int i = curPar; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                count++;
            }
            if (s.charAt(i) == ')') {
                count--;
            }
            if (count == 0) {
               return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        BasicCalculator bc = new BasicCalculator();
        //System.out.println(bc.calculate("1 - (1 + 3 + 5 - (10 + 1000))"));
        System.out.println(bc.calculateRecur("1 - (1 + 3 + 5 - (10 + 1000))"));
    }
}
