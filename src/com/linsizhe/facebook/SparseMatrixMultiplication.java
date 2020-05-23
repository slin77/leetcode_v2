package com.linsizhe.facebook;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// https://leetcode.com/problems/sparse-matrix-multiplication/
//
public class SparseMatrixMultiplication {
    public int[][] multiply(int[][] A, int[][] B) {
        // represent B as sparse matrix, list of columns of B. each column is
        // just a tuple2 of non-zero entries.
        List<HashMap<Integer, Integer>> sparseB = new ArrayList<>();
        for (int j = 0; j < B[0].length; j++) {
            HashMap<Integer, Integer> map = new HashMap();
            sparseB.add(map);
            for (int i = 0; i < B.length; i++) {
                if (B[i][j] != 0) {
                    map.put(i, B[i][j]);
                }
            }
        }
        int[][] out = new int[A.length][B[0].length];
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < sparseB.size(); j++) {
                if (sparseB.get(j).isEmpty()) {
                    continue;
                }
                out[i][j] += mult(A[i], sparseB.get(j));
            }
        }
        return out;
    }

    private int mult(int[] arr, Map<Integer, Integer> map) {
        int sum = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            sum += arr[entry.getKey()] * entry.getValue();
        }
        return sum;
    }

    // "smart solution"
    public int[][] multiply2(int[][] A, int[][] B) {
        int m = A.length, n = A[0].length, nB = B[0].length;
        int[][] C = new int[m][nB];

        for(int i = 0; i < m; i++) {
            // brute force was iterate nB then nA
            // switch the order of nested loop
            // so any entry in nA with zero can used to skip entire
            // nB
            // Why ? ： because if we do nB, we can only skip if we know that nB is ALL empty.
            for(int k = 0; k < n; k++) { // brute force was nB
                if (A[i][k] != 0) {
                    for (int j = 0; j < nB; j++) {// brute force was nA
                        if (B[k][j] != 0) C[i][j] += A[i][k] * B[k][j];
                    }
                }
            }
        }
        return C;
    }
}
