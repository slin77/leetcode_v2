package com.linsizhe.amazonOS;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class ValidSoduku {
    public boolean isValidSudoku(char[][] board) {
        List<HashSet<Integer>> rows = new ArrayList();
        List<HashSet<Integer>> cols = new ArrayList();
        List<HashSet<Integer>> grids = new ArrayList();

        for (int i = 0; i < 9; i++) {
            rows.add(new HashSet<Integer>());
            cols.add(new HashSet<Integer>());
            grids.add(new HashSet<Integer>());
        }

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == '.') {
                    continue;
                }
                int num = board[i][j] - '0';
                HashSet<Integer> row = rows.get(i);
                HashSet<Integer> col = cols.get(j);
                HashSet<Integer> grid = grids.get((i / 3) * 3 + j / 3);

                if (!row.add(num) || !col.add(num) || !grid.add(num)) {
                    return false;
                }
            }
        }
        return true;
    }
}
