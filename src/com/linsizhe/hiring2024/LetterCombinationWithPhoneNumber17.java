package com.linsizhe.hiring2024;

import java.util.ArrayList;
import java.util.List;

// https://leetcode.com/problems/letter-combinations-of-a-phone-number/
public class LetterCombinationWithPhoneNumber17 {
    String[] dict = new String[]{"", "", "abc", "def", "ghi", "jkl", "mno", "qprs", "tuv", "wxyz"};
    public List<String> letterCombinations(String digits) {
        List<String> res = new ArrayList();
        if (digits.isEmpty()) {
            return res;
        }
        dfs(0, digits, res, new StringBuilder());
        return res;
    }

    void dfs(int idx, String digits, List<String> res, StringBuilder sb) {
        if (idx == digits.length()) {
            res.add(sb.toString());
            return;
        }
        String cur = dict[digits.charAt(idx) - '0'];
        for (char c : cur.toCharArray()) {
            sb.append(c);
            dfs(idx + 1, digits, res, sb);
            sb.deleteCharAt(idx);
        }
    }
}
