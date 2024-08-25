package com.linsizhe.hiring2024;

// https://leetcode.com/problems/merge-strings-alternately/
public class MergeStringAlternatively1768 {
    public String mergeAlternately(String word1, String word2) {
        char[] res = new char[word1.length() + word2.length()];
        int pt1 = 0;
        int pt2 = 0;
        int pt = 0;
        while (pt < res.length) {
            // two pointers, handle bondary case first so we dont need to worry about it later
            if (pt2 == word2.length()) {
                res[pt++] = word1.charAt(pt1++);
            } else if (pt1 == word1.length()) {
                res[pt++] = word2.charAt(pt2++);
            } else if (pt2 >= pt1) {
                res[pt++] = word1.charAt(pt1++);
            } else {
                res[pt++] = word2.charAt(pt2++);
            }
        }

        return new String(res);
    }
}
