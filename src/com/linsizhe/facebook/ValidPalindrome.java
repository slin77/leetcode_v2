package com.linsizhe.facebook;

// 2 pointers.
// When we see any diff during comparing. We either skip left one or skip right one.
// and continue comparing
public class ValidPalindrome {
    public boolean validPalindrome(String s) {
        boolean skip = false;
        boolean valid = true;
        for (int i = 0, j = s.length() - 1; j >= i; i++, j--) {
            if (s.charAt(i) != s.charAt(j)) {
                if (skip) {
                    valid = false;
                    break;
                } else {
                    i--;
                    skip = true;
                }
            }
        }
        if (valid) return true;
        skip = false;
        valid = true;
        for (int i = 0, j = s.length() - 1; j >= i; i++, j--) {
            if (s.charAt(i) != s.charAt(j)) {
                if (skip) {
                    valid = false;
                    break;
                } else {
                    j++;
                    skip = true;
                }
            }
        }
        return valid;
    }
}
