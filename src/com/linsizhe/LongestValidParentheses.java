package com.linsizhe;

import java.util.Arrays;

//32.
public class LongestValidParentheses {
    public int longestValidParentheses(String s) {

        char[] chars = s.toCharArray();
        int[] dp = new int[chars.length];

        int max = 0;
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '(') {
                dp[i] = 0;
            } else {
                if (i != 0 && chars[i - 1] == '(') {
                    dp[i] = i == 1 ? 2 : dp[i - 2] + 2;
                } else if (i != 0 && chars[i - 1] == ')') {
                    if (dp[i - 1] == 0) {
                        dp[i] = 0; // [missing] [.......] ) ) => missing cover from left
                    } else if (i - dp[i - 1] - 1 >= 0 && chars[i - dp[i - 1] - 1] == '(') {
                        // [....] ( [.........] ) ) => note that dp[i - 1] already cover ')' on i - 1
                        // so the left one is just for you!
                        dp[i] = ((i - dp[i - 1] - 1 == 0) ? 0 :  dp[i - dp[i - 1] - 2]) + dp[i - 1] + 2;
                    }
                }
            }
            if (dp[i] > max) max = dp[i];
        }

        System.out.println(Arrays.toString(dp));
        return max;
    }

    public static void main(String[] args) {
        String input = "(())";
        LongestValidParentheses lvp = new LongestValidParentheses();
        lvp.longestValidParentheses(input);
    }
}
