package com.linsizhe.facebook;

// https://leetcode.com/problems/valid-word-abbreviation/
public class ValidWordAbbreviation {
    public boolean validWordAbbreviation(String word, String abbr) {
        int i = 0;
        int j = 0;
        while (i < word.length() && j < abbr.length()) {
            int cum = 0;
            // no leading 0;
            if (abbr.charAt(j) == '0') return false;
            // tip: use whie loop to parse all digits
            while (j < abbr.length() && Character.isDigit(abbr.charAt(j))) {
                cum = cum * 10 + abbr.charAt(j) - '0';
                j++;
            }
            if (cum != 0){
                i += cum;
            } else if (word.charAt(i) != abbr.charAt(j)) {
                return false;
            } else {
                i++;
                j++;
            }
        }
        return i == word.length() && j == abbr.length();
    }
}
