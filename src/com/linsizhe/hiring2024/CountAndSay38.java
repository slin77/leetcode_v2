package com.linsizhe.hiring2024;

//https://leetcode.com/problems/count-and-say/
public class CountAndSay38 {
    public String countAndSay(int n) {
        if (n == 1) return "1";
        String str = countAndSay(n - 1);
        StringBuilder sb = new StringBuilder();
        int i = 0;
        while (i < str.length()) {
            int j = i;
            // add bounday check first
            while (j + 1 < str.length() && str.charAt(j + 1) == str.charAt(j)) {
                j++;
            }
            sb.append(j - i + 1);
            sb.append(str.charAt(i));
            i = j + 1;
        }
        return sb.toString();
    }
}
