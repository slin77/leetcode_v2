package com.linsizhe.hiring2024;

// https://leetcode.com/problems/integer-to-english-words/
public class IntegerToEnglishWords273 {
    String[] p1 = {"", "One", "Two",  "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};

    String[] p2 = {"", "", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};

    public String numberToWords(int num) {

        if (num == 0) {
            return "Zero";
        }
        //123 123 123
        String res  = "";
        if (num % 1000 != 0) {
            res += helper(num % 1000);
        }

        //123 123
        num = num / 1000;
        if (num % 1000 != 0) {
            res = helper(num % 1000) + " Thousand " + res;
        }

        num = num / 1000;
        if (num % 1000 != 0) {
            res = helper(num % 1000) + " Million " + res;
        }

        num = num / 1000;
        if (num % 1000 != 0) {
            res = helper(num % 1000) + " Billion " + res;
        }
        return res.trim();
    }

    // deal with < 1000
    private String helper(int num) {
        String res = "";
        if (num >= 100) {
            res += p1[num / 100];
            res += " Hundred";
        }
        num = num % 100;
        if (num < 20) {
            res += " " + p1[num];
        }  else {
            res +=" " + p2[num/10] + " ";
            res += p1[num%10];
        }
        return res.trim();
    }
}
