package com.linsizhe.snapchat;

//https://leetcode.com/problems/word-search/
public class WordSearch {
    public boolean exist(char[][] board, String word) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (dfs(board, i, j, 0, word)) {
                    return true;
                }
            }
        }
        return false;
    }

    boolean dfs(char[][] board, int i, int j, int idx, String word) {
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length) {
            return false;
        }
        if (word.charAt(idx) != board[i][j]) {
            return false;
        }
        if (idx == word.length() - 1) {
            return true;
        }
        boolean res = false;
        char cur = board[i][j];
        board[i][j] = '*';
        res = dfs(board, i + 1, j, idx + 1, word)
                || dfs(board, i, j+ 1, idx + 1, word)
                || dfs(board, i - 1, j, idx + 1, word)
                || dfs(board, i, j - 1, idx + 1, word);
        board[i][j] = cur;
        return res;
    }
}
