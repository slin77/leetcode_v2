package com.linsizhe.hiring2024;

//https://leetcode.com/problems/find-the-index-of-the-first-occurrence-in-a-string/description/
public class FindingIndexOfFirstOccuranceOfString28 {
    public int strStr(String haystack, String needle) {
        int pt1 = 0;

        if (needle.length() > haystack.length()) return -1;

        while (pt1 <= haystack.length() - needle.length()) {
            while (pt1 < haystack.length() && haystack.charAt(pt1) != needle.charAt(0)) {
                pt1++;
            }

            if (pt1 > haystack.length() - needle.length()) return -1;
            if (haystack.substring(pt1, pt1 + needle.length()).equals(needle)) {
                return pt1;
            } else {
                pt1++;
            }
        }

        return -1;

    }
}
