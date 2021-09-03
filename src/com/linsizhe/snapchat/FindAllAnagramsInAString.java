package com.linsizhe.snapchat;

import java.util.ArrayList;
import java.util.List;

public class FindAllAnagramsInAString {
    public List<Integer> findAnagrams(String s, String p) {
        //int[] sMap = new int[255];
        int[] pMap = new int[255];
        for (char c : p.toCharArray()) {
            pMap[c]++;
        }
        int i = 0;
        int j = 0;
        ArrayList<Integer> out = new ArrayList();
        // way to get anagram, create char count map
        // decrease from positive count!
        // also this need to check window size!
        int n = p.length();
        for (i = 0; i < s.length(); i++) {
            while (j < s.length() && n > 0) {
                // positive, the char p still
                // need decrease
                // 0, negative, we reach target
                // further decrease wont count
                if (pMap[s.charAt(j)] > 0) {
                    n--;
                }
                pMap[s.charAt(j)]--;
                j++;
            }
            // we can still miss the case when n is 0 but
            // there are other chars in the window
            // need additional check for window size!
            // j-th idx is not in the window!
            if (n == 0 && j - i == p.length()) {
                out.add(i);
            }

            pMap[s.charAt(i)]++;
            if (pMap[s.charAt(i)] == 1) {
                n++;
            }
        }
        return out;
    }
}
