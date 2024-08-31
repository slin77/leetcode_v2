package com.linsizhe.hiring2024;

//https://leetcode.com/problems/remove-all-adjacent-duplicates-in-string/
public class RemoveAllAdjacentDuplicatesInString1047 {
    // // TLE due to recursion and char copy!
    // public String removeDuplicates(String s) {
    //     StringBuilder res = new StringBuilder();
    //     int i = 0;
    //     while (i < s.length()) {
    //         // i to j is duplicated;
    //         int j = i;
    //         while (j < s.length() - 1 && s.charAt(j) == s.charAt(j+1)) {
    //             j++;
    //         }
    //         if (i == j || (j - i + 1) % 2 == 1) res.append(s.charAt(i));
    //         i = j + 1;
    //     }
    //     if (res.length() == s.length()) {
    //         return res.toString();
    //     }
    //     return removeDuplicates(res.toString());
    // }

    int pointer = 0;

    public String removeDuplicates(String s) {
        for (int i = pointer; i < s.length() - 1; i++) {
            if (s.charAt(i) == s.charAt(i + 1)) {
                pointer = i == 0 ? 0 : i - 1; // we need need to process anything before i again
                // removed is [i, i + 1] we can start scan from left of idx i
                return removeDuplicates(s.substring(0, i) + s.substring(i + 2, s.length()));
            }
        }
        return s;
    }
}
