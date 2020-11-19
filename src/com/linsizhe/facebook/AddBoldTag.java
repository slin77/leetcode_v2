package com.linsizhe.facebook;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

//https://leetcode.com/problems/add-bold-tag-in-string/
// Same as merge intervals of merging bold tags.
public class AddBoldTag {
    public String addBoldTag(String s, String[] dict) {
        ArrayList<int[]> intervals = new ArrayList();
        for (String str : dict) {
            int start = s.indexOf(str);
            if (start >= 0) {
                intervals.add(new int[]{start, start + str.length()});
            }
            // for repeated substring.
            while (start >= 0) {
                start = s.indexOf(str, start + 1);
                if (start >=0 ) {
                    intervals.add(new int[]{start, start + str.length()});
                }
            }
        }

        HashMap<Integer, Integer> bolds = mergeInterval(intervals);
        char[] out = new char[bolds.size() * 7 + s.length()];
        int idx = 0;
        int ptr = 0;
        char[] sArr = s.toCharArray();
        while (ptr < out.length) {
            if (bolds.containsKey(idx)) {
                int len = bolds.get(idx) - idx;
                out[ptr++] = '<';
                out[ptr++] = 'b';
                out[ptr++] = '>';
                for (int i = 0; i < len; i++) {
                    out[ptr++] = sArr[idx++];
                }
                out[ptr++] = '<';
                out[ptr++] = '/';
                out[ptr++] = 'b';
                out[ptr++] = '>';
            } else {
                out[ptr++] = sArr[idx++];
            }
        }
        return new String(out);
    }

    private HashMap<Integer, Integer> mergeInterval(ArrayList<int[]> intervals) {
        if (intervals.isEmpty()) {
            return new HashMap();
        }
        Collections.sort(intervals, Comparator.comparingInt(a -> a[0]));
        HashMap<Integer, Integer> out = new HashMap();
        int start = (intervals.get(0))[0];
        int end = (intervals.get(0))[1];
        for (int[] interval : intervals) {
            if (interval[0] > end) {
                out.put(start, end);
                start = interval[0];
                end = interval[1];
            } else {
                end = Math.max(end, interval[1]);
            }
        }
        out.put(start, end);
        return out;
    }
}
