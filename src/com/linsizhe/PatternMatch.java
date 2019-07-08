package com.linsizhe;

// Not able to solve
class PatternMatch {
    public boolean isMatch(String s, String p) {
        if (s.length() == 0 && p.length() == 0) {
            return true;
        } else if (s.length() == 0) {
            return p.equals("*");
        } else if (p.length() == 0) {
            return false;
        }

        boolean[][] dp = new boolean[s.length()][p.length()];
        char[] ss = s.toCharArray();
        char[] pp = p.toCharArray();
        for (int i = 0; i < s.length(); i++) {
            for (int j = 0; j < p.length(); j++) {
                char sChar = ss[i];
                char pChar = pp[j];

                // Edge cases? for 0s...
                if (pChar == '*') {
                    if (i == 0 && j == 0) {
                        dp[i][j] = true;
                    } else if (i == 0) {
                        dp[i][j] = dp[i][j - 1];
                    } else if (j == 0) {
                        dp[i][j] = true;
                    } else {
                        dp[i][j] = dp[i - 1][j - 1] || dp[i][j - 1] || dp[i - 1][j];

                    }

                }  else if (pChar == '?' || pChar == sChar) {
                    if (i == 0 && j == 0) {
                        dp[i][j] = true;
                    } else if (i == 0) {
                        dp[i][j] = pp[j - 1] == '*' && dp[i][j - 1];
                    } else if (j == 0) {
                        dp[i][j] = false;
                    } else {
                        dp[i][j] = dp[i - 1][j - 1];
                                //|| pp[j - 1] == '*' && dp[i][j - 1];
                    }
                } else if (pChar != sChar) {
                    dp[i][j] = false;
                }
                boolean b = dp[i][j];
            }
        }
        return dp[ss.length - 1][pp.length - 1];
    }

    public static void main(String[] args) {
//
//        "mississippi"
//        "m??*ss*?i*pi"
        //String s = "mississippi";
        String s = "adceb";
        //String p =  "m??*ss*?i*pi";
        String p =  "*a*b";
        PatternMatch pm = new PatternMatch();
        System.out.println( pm.isMatch(s, p));
    }
}
