package com.linsizhe.facebook;

//https://leetcode.com/problems/design-add-and-search-words-data-structure/submissions/
public class DesignAddAndSearchWord {
    class TrieNode {
        TrieNode[] nexts;
        boolean isLeaf;
        public TrieNode() {
            nexts = new TrieNode[26];
            isLeaf = false;
        }
    }

    TrieNode root;

    /** Initialize your data structure here. */
    public DesignAddAndSearchWord() {
        root = new TrieNode();
    }

    public void addWord(String word) {
        TrieNode cur = root;
        // each char will take a node.
        // so for a string of length n,
        // depth will be n + 1 (including root).
        for (char c: word.toCharArray()) {
            int idx = c - 'a';
            if (cur.nexts[idx] == null) {
                cur.nexts[idx] = new TrieNode();
            }
            cur = cur.nexts[idx];
        }
        cur.isLeaf = true;
    }

    public boolean search(String word) {
        return search(word, root, 0);
    }

    public boolean search(String word, TrieNode cur, int idx) {
        if (cur == null) return false;
        // cur is the previous idx's char's node, so idx == length()
        // we already reach end
        if (idx == word.length()) {
            return cur.isLeaf;
        }
        char c = word.charAt(idx);
        if (c == '.') {
            for (int i = 0; i < 26; i++) {
                if (search(word, cur.nexts[i], idx+1)) return true;
            }
            return false;
        } else {
            return search(word, cur.nexts[c - 'a'], idx+1);
        }
    }
}
