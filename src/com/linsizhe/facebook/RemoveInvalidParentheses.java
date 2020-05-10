package com.linsizhe.facebook;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class RemoveInvalidParentheses {
    public List<String> removeInvalidParentheses(String s) {
        int left = 0;
        int right = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                left++;
            } else if (s.charAt(i) == ')') {
                if (left > 0) {
                    left--;
                } else {
                    right++;
                }
            }
        }
        HashSet<String> out = new HashSet<>();
        dfs(s, 0, left, right, out, "");
        return new ArrayList<>(out);
    }

    public void dfs(String s, int idx, int left, int right, Set<String> output, String curStr) {
        if (idx == s.length()) {
            if (left == 0 && right == 0 && isValid(curStr)) {
                output.add(curStr);
            }
            return;
        }
        // terminated;
        if (left == 0 && right == 0) {
            String res = curStr + s.substring(idx);
            if (isValid(res)) {
                output.add(res);
            }
            return;
        }
        char cur = s.charAt(idx);
        if (cur == '(' && left > 0) {
            // abort left option
            dfs(s, idx+1, left-1, right, output, curStr);


        }
        if (cur == ')' && right > 0) {
            // abort right option
            dfs(s, idx+1, left, right-1, output, curStr);

        }
        dfs(s, idx+1, left, right, output, curStr+cur);
    }

    public boolean isValid(String str) {
        int counter = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '(') {
                counter++;
            } else if (str.charAt(i) == ')') {
                counter--;
                if (counter < 0) {
                    return false;
                }
            }
        }
        return counter == 0;
    }
}