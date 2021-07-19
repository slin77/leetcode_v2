package com.linsizhe.facebook;

//https://leetcode.com/problems/minimum-add-to-make-parentheses-valid/
public class MinimumAddToMakeParenthesesValid {
    public int minAddToMakeValid(String s) {
        int count = 0;
        int needed = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                // still unclosing ')', already handled bellow
                // we reset since all opening are coverred.
                if (count < 0) {
                    count = 0;
                }
                count++;
            } else if (s.charAt(i) == ')') {
                count--;
                // when we have more closing after this,
                // need to add opening
                if (count < 0) {
                    needed++;
                }
            }
        }
        // opening is addressed in the last.
        if (count > 0) needed += count;
        return needed;
    }
}
