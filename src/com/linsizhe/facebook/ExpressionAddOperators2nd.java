package com.linsizhe.facebook;

import java.util.ArrayList;
import java.util.List;

//https://leetcode.com/problems/expression-add-operators/
public class ExpressionAddOperators2nd {
    public List<String> addOperators(String num, int target) {
        ArrayList<String> out = new ArrayList();
        // init as + 0th
        int curNum = 0;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < num.length(); i++) {
            sb.append(num.substring(0, i + 1));
            dfs(num, target, i + 1, 0, 1, Long.valueOf(num.substring(0, i+1)), sb, out);
            sb.delete(0, sb.length());
            if (i == 0 && num.charAt(i) == '0') {
                break;
            }
        }
        return out;
    }

    public void dfs(String num, int target, int idx, long curValue, int sign, long cum, StringBuilder sb, List<String> out) {
        if (idx == num.length()) {
            curValue += cum * sign;
            if (curValue == target) {
                out.add(sb.toString());
            }
            return;
        }

        int curNum = 0;
        int insize = sb.length();
        for (int i = idx; i < num.length(); i++) {
            int cur = num.charAt(i) - '0';

            curNum = curNum * 10 + cur;

            // + cur
            sb.append('+');
            sb.append(String.valueOf(curNum));
            dfs(num, target, i + 1, curValue + cum * sign, 1, curNum, sb, out);
            sb.delete(insize, sb.length());


            // - cur
            sb.append('-');
            sb.append(String.valueOf(curNum));
            dfs(num, target, i + 1, curValue + cum * sign, -1, curNum, sb, out);
            sb.delete(insize, sb.length());

            // * cur
            sb.append('*');
            sb.append(String.valueOf(curNum));
            dfs(num, target, i + 1, curValue, sign, cum * curNum, sb, out);
            sb.delete(insize, sb.length());

            if (i == idx && cur == 0) {
                break;
            }
        }
    }
}
