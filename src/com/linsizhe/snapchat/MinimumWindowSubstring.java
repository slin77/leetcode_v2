package com.linsizhe.snapchat;

public class MinimumWindowSubstring {
    public String minWindow(String s, String t) {
        int[] map = new int[255];
        for (char c : t.toCharArray()) {
            map[c]++;
        }
        int n = t.length();
        int i = 0;
        int j = 0;
        int min = 100001;
        String res = "";
        //
        for (i = 0; i < s.length(); i++) {
            while (j < s.length() && n > 0) {
                char cur = s.charAt(j);
                if (map[cur] > 0) {
                    n--;
                }
                map[cur]--;
                j++;
            }
            if (n == 0) {
                if (j - i < min) {
                    min = j - i;
                    res = s.substring(i, j);
                }
            }
            map[s.charAt(i)]++;
            if (map[s.charAt(i)] > 0) {
                n++;
            }
        }
        return res;
    }
}
