package com.linsizhe.facebook;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

//https://leetcode.com/problems/expression-add-operators/
//
public class ExpresssionAddOperators {
    public List<String> addOperators(String num, int target) {
        LinkedList<String> added = new LinkedList();
        HashSet<String> out = new HashSet<>();
        dfs(num, 0, added, 0, 0, target, out);
        return new ArrayList<>(out);
    }
    public void dfs(String num, int curPos, LinkedList<String> added, long curValue, long lastAdded, long target, HashSet<String> out) {
        if (curPos == num.length()) {
            if (curValue == target) {
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < added.size(); i++) {
                    sb.append(added.get(i));
                }
                out.add(sb.toString());
            }
        }
        long curDigit = 0;

        for (int i = curPos; i < num.length(); i++) {
            curDigit = curDigit * 10 + (num.charAt(i) - '0');
            // start number can't add operator before it but wee need to accumulate it for
            // multiple digit number e.g. 1 2 3 -> 12 3
            // note this might introduce wrong result if we have a leading '-'
            if (curPos == 0) {
                added.add(Long.toString(curDigit));
                dfs(num, i + 1, added, curValue + curDigit, curDigit, target, out);
                added.removeLast();
            } else {
                // +
                added.add("+");
                added.add(Long.toString(curDigit));
                dfs(num, i + 1, added, curValue + curDigit, curDigit, target, out);
                added.removeLast();
                added.removeLast();
                //-
                added.add("-");
                added.add(Long.toString(curDigit));
                dfs(num, i + 1, added, curValue - curDigit, -curDigit, target, out);
                added.removeLast();
                added.removeLast();
                //*
                // * case is just remove what we last time added from current value
                // and add cur * lastTime added to the current
                // the A * B whole will be new last added.
                long newLastAdded = lastAdded * curDigit;
                long newCurValue = (curValue - lastAdded) + lastAdded * curDigit;
                added.add("*");
                added.add(Long.toString(curDigit));
                dfs(num, i + 1, added, newCurValue, newLastAdded, target, out);
                added.removeLast();
                added.removeLast();
            }
            // If start digit is 0, we can not keep adding digit to its tail.
            if (i == curPos && curDigit == 0) {
                break;
            }
        }
    }
}
