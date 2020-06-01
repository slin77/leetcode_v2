package com.linsizhe.facebook;

// https://leetcode.com/problems/add-and-search-word-data-structure-design/
class WordDictionary {
    private class TrieNode {
        TrieNode[] nexts;
        // dont forget about leaf indicator, otherwise will be error for
        // substring cases.
        boolean isLeaf;
        public TrieNode() {
            this.nexts = new TrieNode[26];
            this.isLeaf = false;
        }
    }

    private TrieNode root;

    private class MyBoolean {
        boolean found = false;
    }

    /** Initialize your data structure here. */
    public WordDictionary() {
        this.root = new TrieNode();
    }

    /** Adds a word into the data structure. */
    public void addWord(String word) {
        TrieNode cur = root;
        for (char c : word.toCharArray()) {
            if (cur.nexts[c - 'a'] == null) {
                cur.nexts[c - 'a'] = new TrieNode();
            }
            cur = cur.nexts[c - 'a'];
        }
        cur.isLeaf = true;
    }

    /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
    public boolean search(String word) {
        MyBoolean found = new MyBoolean();
        dfs(word, 0, root, found);
        return found.found;
    }

    public void dfs(String word, int idx, TrieNode curNode, MyBoolean found) {
        // think about curNode as trieNode that represent last char from previous
        // dfs invocation. for example dfs("abc", 3, curNode, found),
        // curNode is representing node for 'c'! so root is actually for nothing!
        if (curNode == null) {
            return;
        }
        if (idx == word.length()) {
            if (curNode.isLeaf) {
                found.found = true;
            }
            return;
        }
        char cur = word.charAt(idx);
        if (cur != '.') {
            dfs(word, idx+1, curNode.nexts[cur - 'a'], found);
        } else {
            for (int i = 0; i < 26; i++) {
                dfs(word, idx+1, curNode.nexts[i], found);
            }
        }
    }
}
