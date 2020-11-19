package com.linsizhe.facebook;

import java.util.Stack;

//https://leetcode.com/problems/minimum-remove-to-make-valid-parentheses/
// key point: valid at a index: all left ( must have a ) to close it at this point
// use stack to track all left and its closure. remaining (s are invlid
// also when stack empty all additional ) are invalid
public class MinimumRemoveToMakeValidParentheses {
    public String minRemoveToMakeValid(String s) {
        Stack<Integer> remove = new Stack();
        boolean[] allRemove = new boolean[s.length()];
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                remove.push(i);
            }
            if (s.charAt(i) == ')') {
                if (!remove.isEmpty()) {
                    remove.pop();
                } else {
                    allRemove[i] = true;
                }
            }
        }
        for (Integer i : remove) {
            allRemove[i] = true;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (!allRemove[i]) {
                sb.append(s.charAt(i));
            }
        }
        return sb.toString();
    }
}
