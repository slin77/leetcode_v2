package com.linsizhe.hiring2024;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

// https://leetcode.com/problems/top-k-frequent-words/
public class TopKFrequentWords692 {
    public List<String> topKFrequent(String[] words, int k) {
        List[] freq  = new List[words.length];// we can use trie to store words at each freq.O(N) for addting and getting them out!
        HashMap<String, Integer> map = new HashMap();
        for (String str : words) {
            map.put(str, map.getOrDefault(str, 0) + 1);
        }
        for (String s : map.keySet()) {
            int i = map.get(s);
            if (freq[i] == null) {
                freq[i] = new ArrayList<String>();
            }
            freq[i].add(s);
        }
        List<String> res = new ArrayList();
        for (int i = freq.length - 1; i >=0; i--) {
            List<String> li = freq[i];
            if (li != null) {
                Collections.sort(li);
                for (int j = 0; j < li.size(); j++) {
                    res.add(li.get(j));
                    if (res.size() == k) return res;
                }
            }
        }
        return res;
    }
}
