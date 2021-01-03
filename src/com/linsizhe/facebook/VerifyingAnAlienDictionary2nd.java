package com.linsizhe.facebook;

// https://leetcode.com/problems/verifying-an-alien-dictionary/
public class VerifyingAnAlienDictionary2nd {
    public boolean isAlienSorted(String[] words, String order) {
        int[] occur = new int[26];
        char[] chars = order.toCharArray();
        for (int i = 1; i <= order.length(); i++) {
            occur[chars[i - 1] - 'a'] = i;
        }
        for (int i = 1; i < words.length; i++) {
            String word1 = words[i - 1];
            String word2 = words[i];
            boolean go = false;
            for (int j = 0; j < Math.min(word1.length(), word2.length()); j++) {
                if (occur[word1.charAt(j) - 'a'] < occur[word2.charAt(j) - 'a']) {
                    go = true;
                    break;
                } else if (occur[word1.charAt(j) - 'a'] > occur[word2.charAt(j) - 'a']) {
                    return false;
                }
            }
            if (go) {
                continue;
            }
            if (word1.length() > word2.length()) {
                return false;
            }
        }
        return true;
    }
}
