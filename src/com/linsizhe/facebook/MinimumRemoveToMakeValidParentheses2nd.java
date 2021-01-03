package com.linsizhe.facebook;

import java.util.Stack;

// https://leetcode.com/problems/minimum-remove-to-make-valid-parentheses/
public class MinimumRemoveToMakeValidParentheses2nd {
    public String minRemoveToMakeValid(String s) {
        boolean[] removed = new boolean[s.length()];
        Stack<Integer> idx = new Stack();

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                idx.add(i);
            } else if (s.charAt(i) == ')') {
                if (idx.isEmpty()) {
                    removed[i] = true;
                } else {
                    idx.pop();
                }
            }
        }

        while (!idx.isEmpty()) {
            removed[idx.pop()] = true;
        }

        StringBuilder out = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (!removed[i]) {
                out.append(s.charAt(i));
            }
        }
        return out.toString();
    }
}
