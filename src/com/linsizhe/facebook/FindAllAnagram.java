package com.linsizhe.facebook;

import java.util.*;

// template question for a sliding window algorithm
public class FindAllAnagram {
    public List<Integer> findAnagrams(String s, String p) {
        int[] map = new int[255];
        int charCount = 0;
        for (char c : p.toCharArray()) {
            if (map[c] == 0) {
                charCount++;
            }
            map[c]++;
        }
        int matched = 0;
        int end = 0;
        int start = 0;
        ArrayList<Integer> out = new ArrayList();
        int[] sMap = new int[255];

        // template for slding window
        // while (end pointer condition)
        //   update end state
        //   while (start pointer/window condition)
        //   ***
        //   "full state of a new window"
        //   ***
        //   update start state
        //   update start pointer
        // update end pointer
        while(end < s.length()) {
            char curEnd = s.charAt(end);
            sMap[curEnd]++;
            if (sMap[curEnd] == map[curEnd]) {
                matched++;
            } // state is updated for moved end before we run start. So while bellow have the state for a "new" window.

            // condition we want to keep for each window.
            // this can be templte for fixed size window
            while (end - start + 1 == p.length()) {
                // end just moved and we just have an condition for a "new window"

                // ** state for a new window with both start end updated
                if (matched == charCount) {
                    out.add(start);
                }
                //**

                char cur = s.charAt(start);
                if (sMap[cur] > 0 && sMap[cur] == map[cur]) {
                    matched--;
                }
                sMap[cur]--;
                // also update state before we update pointer so calculation above is in a new window.
                start++;
            }
            end++;
        }
        return out;
    }

    public static void main(String[] args) {
        int i = Integer.valueOf("-1");
        int j = 0;
        int n = -i;
        int m = -n;

        StringBuilder sb = new StringBuilder();

        sb.append(1);
        String str = sb.toString();
        //System.out.println(str.charAt(0));
        int[] tes = {0, 1};

        boolean a;
        int s = 0 % (-1);
        int h = 0;

        HashSet<Integer> out = new HashSet<>();
        int[] arr = new int[out.size()];
        out.<Integer>toArray();
        int res = 1 / 4;
        System.out.println(res);

    }
}
