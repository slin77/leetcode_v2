package com.linsizhe;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Affirm phone
public class WordFrequency {
    Map<Character, String> getFrequency(List<String> words) {
        int[][] count = new int[26][26];// frequency of i, j in the same set
        int[] max = new int[26];// each chars max count

        for (String str : words) {
            boolean[] exist = new boolean[26];
            for (char c : str.toCharArray()) {
                exist[c - 'a'] = true;
            }
            for (int i = 0; i < 26; i++) {
                for (int  j = 0; j < 26; j++) {
                    if (exist[i] && exist[j] && i != j) {
                        count[i][j]++;
                        count[j][i]++;

                        max[i] = Math.max(max[i], count[i][j]);
                        max[j] = Math.max(max[j], count[j][i]);
                    }
                }
            }
        }

        Map<Character, String> out = new HashMap<>();
        for (int i = 0; i < 26; i++) {
            if (max[i] > 0) {
                int maxCt = max[i];
                int[] ct = count[i];
                StringBuilder sb = new StringBuilder();
                for (int j = 0; j < ct.length; j++) {
                    if (ct[j] == maxCt) {
                        sb.append((char)('a' + j));
                    }
                }
                out.put((char)('a' + i), sb.toString());
            }
        }
        return out;
    }

    public static void main(String[] args) {
        List<String> input1 = Arrays.asList("abc", "bcd", "cde");
        WordFrequency wf = new WordFrequency();
        System.out.println(wf.getFrequency(input1));
    }
}
