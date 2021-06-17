package com.linsizhe.facebook;

//https://leetcode.com/problems/additive-number/
public class AdditiveNumber {
    public boolean isAdditiveNumber(String num) {
        for (int i = 1; i < num.length() - 1; i++) {
            String str1 = num.substring(0, i);
            if (!str1.equals("0") && str1.charAt(0) == '0') break;
            // from just two nums we can deduce the whole list.
            for (int j = i + 1; j < num.length() ; j++) {
                String str2 = num.substring(i, j);
                if (!str2.equals("0") && str2.charAt(0) == '0') continue;
                if (canGenerate(str1, str2, num, str1.length() + str2.length())) {
                    return true;
                } else {
                    continue;
                }
            }
        }
        return false;

    }

    boolean canGenerate(String a, String b, String num, int idx) {
        if (idx >= num.length()) return true;
        long num1 = Long.valueOf(a);
        long num2 = Long.valueOf(b);
        String next = String.valueOf(num1 + num2);
        if (num.substring(idx).startsWith(next)) {
            return canGenerate(b, next, num, idx + next.length());
        } else {
            return false;
        }
    }
}
