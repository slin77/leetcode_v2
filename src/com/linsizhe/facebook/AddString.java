package com.linsizhe.facebook;

// same idea as add binary
// https://leetcode.com/problems/add-strings/
public class AddString {
    public String addStrings(String num1, String num2) {
        int d1, d2, carry, sum;
        carry = 0;
        StringBuilder out = new StringBuilder();
        for (int i = num1.length() - 1, j = num2.length() - 1; i >=0 || j >=0; i--, j--) {
            d1 = i >= 0 ? num1.charAt(i) - '0' : 0;
            d2 = j >= 0 ? num2.charAt(j) - '0' : 0;
            sum = d1 + d2 + carry;
            out.append(sum % 10);
            carry = sum / 10;
        }
        if (carry > 0) {
            out.append('1');
        }
        return out.reverse().toString();
    }
}
