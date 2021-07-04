package com.linsizhe.facebook;

import java.util.LinkedList;
import java.util.Queue;

public class AlienDictionary {
    public String alienOrder(String[] words) {
        int[][] adj = new int[26][26];
        int[] indegree = new int[26];

        for (int i = 0; i < 26; i++) {
            indegree[i] = -1001;
        }

        // keep track of total
        int total = 0;
        for (String str : words) {
            for (char c : str.toCharArray()) {
                if (indegree[c - 'a'] == -1001) {
                    indegree[c - 'a'] = 0;
                    total++;
                }
            }
        }

        for (int i = 0; i < words.length - 1; i++) {

            String a = words[i];
            String b = words[i + 1];

            int idx = 0;
            while (idx < Math.min(a.length(), b.length()) && a.charAt(idx) == b.charAt(idx)) {
                idx++;
            }
            if (idx == Math.min(a.length(), b.length())) {
                if (a.length() > b.length()) return "";
                continue;
            }
            // a -> b： a < b
            char charA = a.charAt(idx);
            char charB = b.charAt(idx);

            // we can have a -> b in multiple times,
            // do not over count indegree
            if (adj[charA - 'a'][charB - 'a'] == 0) {
                adj[charA - 'a'][charB - 'a'] = 1;
                indegree[charB - 'a']++;
            }
        }

        // topo sort using bfs
        // enqueue all indegree become zeros node!
        Queue<Integer> q = new LinkedList();
        for (int i = 0; i < 26; i++) {
            if (indegree[i] == 0) {
                q.add(i);
            }
        }
        StringBuilder out = new StringBuilder();
        while (!q.isEmpty()) {
            int c = q.poll();
            out.append((char) ('a' + c));
            for (int i = 0; i < 26; i++) {
                if (adj[c][i] == 1) {
                    int de = i;
                    indegree[i]--;
                    if (indegree[i] == 0) {
                        q.add(i);
                    }
                }
            }
        }
        if (out.length() != total) {
            return "";
        }
        return out.toString();
    }
}
