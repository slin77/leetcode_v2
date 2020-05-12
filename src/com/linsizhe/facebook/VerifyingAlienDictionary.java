package com.linsizhe.facebook;

import java.util.HashMap;

// https://leetcode.com/problems/verifying-an-alien-dictionary/
public class VerifyingAlienDictionary {
    public boolean isAlienSorted(String[] words, String order) {
        HashMap<Character, Integer> idx = new HashMap();
        for (int i = 1; i <= order.length(); i++) {
            idx.put(order.charAt(i - 1), i);
        }
        for (int i = 0; i < words.length - 1; i++) {
            if (isLess(words[i], words[i + 1], idx)) {
                continue;
            }
            return false;
        }
        return true;
    }

    private boolean isLess(String word1, String word2, HashMap<Character, Integer> idx) {
        for (int i = 0; i < Math.max(word1.length(), word2.length()); i++) {
            int idx1 = i < word1.length() ? idx.get(word1.charAt(i)) : 0;
            int idx2 = i < word2.length() ? idx.get(word2.charAt(i)) : 0;
            if (idx1 < idx2) {
                return true;
            }
            if (idx1 == idx2) {
                continue;
            }
            return false;
        }
        return true;
    }
}
