package com.linsizhe.facebook;

import java.util.Stack;

// https://leetcode.com/problems/remove-all-adjacent-duplicates-in-string-ii/submissions/
public class RemoveDuplicates {
    public String removeDuplicates(String s, int k) {
        Stack<Character> stack = new Stack();
        int[] count = new int[s.length()];
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (!stack.isEmpty() && c == stack.peek()) {
                // For each element on stack, we keep track of its count num
                // update by comparing with last element idx.
                // use stack.size() - 1 as pointer to the top stack element's count
                count[stack.size()] = count[stack.size() - 1] + 1; // about insert's char's count
                if (count[stack.size()] == k) {
                    for (int j = 0; j < k - 1; j++) {
                        stack.pop();
                    }
                    continue;
                }
            } else {
                // this is a new element
                count[stack.size()] = 1;
            }
            stack.push(c);
        }
        StringBuilder sb = new StringBuilder();
        for (char c : stack) {
            sb.append(c);
        }
        return sb.toString();
    }
}
