package com.linsizhe;

import java.util.*;

class PalindromePairs {
    class TrieNode {
        TrieNode[] nexts;
        int idx;
        boolean isLeaf;
        char curChar;
        List<Integer> cans;
        TrieNode() {
            this.nexts = new TrieNode[26];
            this.idx = -1;
            this.isLeaf = false;
            cans = new ArrayList();
        }
    }

    public List<List<Integer>> palindromePairs(String[] words) {
        TrieNode root = makeTrie(words);
        ArrayList<List<Integer>> out = new ArrayList();
        for (int i = 0; i < words.length; i++) {
            char[] chars = words[i].toCharArray();
            TrieNode cur = root;
            for (int j = 0 ; j < chars.length; j++) {
                if (cur == null) {
                    break;
                }

                if (cur.isLeaf && isPalindrome(chars, j + 1, chars.length - 1)) {
                    out.add(Arrays.asList(i, cur.idx));
                }

                cur = cur.nexts[chars[j] - 'a'];
            }
            if (cur != null) {
                if (cur.isLeaf) {
                    if (i != cur.idx) {
                        out.add(Arrays.asList(i, cur.idx));
                    }
                }
                for (Integer can : cur.cans) {
                    if (i != can) out.add(Arrays.asList(i, can));
                }
            }
        }
        return out;
    }

    public TrieNode makeTrie(String[] words) {
        TrieNode root = new TrieNode();
        for (int i = 0; i < words.length; i++) {
            char[] wordChars = reverse(words[i].toCharArray());
            TrieNode cur = root;
            List<TrieNode> candidates = new ArrayList();
            for (int level = 0; level < wordChars.length; level++) {
                char curChar = wordChars[level];
                int idx = wordChars[level] - 'a';
                if (cur.nexts[idx] == null) {
                    cur.nexts[idx] = new TrieNode();
                }
                if (isPalindrome(wordChars, level + 1, wordChars.length - 1)) {
                    candidates.add(cur);
                }
                cur = cur.nexts[idx];
            }
            // Keep track of nodes under this that rest from the word form a palindrome. @@@@@.
            for (TrieNode node : candidates) {
                node.cans.add(i);
            }
            cur.idx = i;
            cur.isLeaf = true;
        }
        return root;
    }

    public char[] reverse(char[] in) {
        for (int i = 0, j = in.length - 1; i < j; i++, j--) {
            char temp = in[i];
            in[i] = in[j];
            in[j] = temp;
        }
        return in;
    }

    public TrieNode getNode(String word, TrieNode root) {
        TrieNode cur = root;
        for (char c : word.toCharArray()) {
            if (cur == null) {
                return null;
            }
            cur = cur.nexts[c - 'a'];
        }
        return cur;
    }

    public boolean isPalindrome(char[] inputs, int start, int end) {
        if (inputs.length == 1) {
            return true;
        }
        if (start > end) {
            return false;
        }
        for (int i = start, j = end; i < j; i++, j--) {
            if (inputs[i] != inputs[j]) {
                return false;
            }
        }
        return true;
    }


    public static void main(String[] args) {
        //String[] inputs = {"a", ""};
        String[] inputs = {"abcd","dcba","lls","s","sssll"};
        PalindromePairs pp = new PalindromePairs();
        System.out.println(pp.palindromePairs(inputs));
        System.out.println('0' - 0);
        int[][] tests = {{1, 2}, {3, 4}};

    }
}