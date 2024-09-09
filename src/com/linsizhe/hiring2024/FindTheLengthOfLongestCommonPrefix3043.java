package com.linsizhe.hiring2024;

//  https://leetcode.com/problems/find-the-length-of-the-longest-common-prefix/
public class FindTheLengthOfLongestCommonPrefix3043 {
    class TrieNode {
        public TrieNode[] next;
        public boolean isLeaf = false;
        public int length;

        TrieNode() {
            next = new TrieNode[10];
        }
    }
    public int longestCommonPrefix(int[] arr1, int[] arr2) {
        TrieNode root = new TrieNode();
        root.length = 0;
        int max = 0;
        for (int num : arr1) {
            String str = String.valueOf(num);
            TrieNode cur = root;
            for (char c : str.toCharArray()) {
                int l = cur.length  + 1;
                if (cur.next[c - '0'] == null) {
                    cur.next[c - '0'] = new TrieNode();
                }
                cur = cur.next[c - '0'];
                cur.length = l;
                cur.isLeaf = true;
            }
        }
        for (int num : arr2) {
            String str = String.valueOf(num);
            TrieNode cur = root;
            for (char c : str.valueOf(num).toCharArray()) {
                cur = cur.next[c - '0'];
                if (cur != null && cur.isLeaf) {
                    max = Math.max(max, cur.length);
                } else {
                    break;
                }
            }
        }
        return max;
    }
}
