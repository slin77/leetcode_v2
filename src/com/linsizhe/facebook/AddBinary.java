package com.linsizhe.facebook;

// https://leetcode.com/problems/add-binary/submissions/
public class AddBinary {
    public String addBinary(String a, String b) {
        int digitA, digitB, carry, sum;
        carry = 0;
        StringBuilder out = new StringBuilder();
        // since both counter from right; Need 2 pointers so 2 variable for loop will do it!.
        for (int i = a.length() - 1, j = b.length() - 1; i >=0 || j >=0; i--, j--) {
            digitA = i >=0 ? a.charAt(i) - '0' : 0;
            digitB = j >=0 ? b.charAt(j) - '0' : 0;
            sum = digitA + digitB + carry;
            out.append(sum % 2);
            carry = sum / 2;
        }
        if (carry > 0) {
            out.append('1');
        }
        return out.reverse().toString();
    }
}
