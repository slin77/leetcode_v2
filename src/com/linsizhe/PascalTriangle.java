package com.linsizhe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PascalTriangle {
    public List<List<Integer>> generate(int numRows) {
        ArrayList<List<Integer>> out = new ArrayList<>();
        if (numRows == 0) {
            return out;
        }
        out.add(Arrays.asList(1));
        for (int i = 1; i < numRows; i++) {
            List<Integer> preRow = out.get(i - 1);
            List<Integer> curRow = new ArrayList<>();
            for (int j = 0; j <= i; j++) {
                int a = j - 1 < 0 ? 0 : preRow.get(j - 1);
                int b = j >= preRow.size() ? 0 : preRow.get(j);
                curRow.add(a + b);
            }
            out.add(curRow);
        }
        return out;
    }
}
