package com.linsizhe.plaid;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CentralBankBalance {
    public List<String> simplify(List<String> inputs) {
        int[] toA = new int[255];
        int[] aTo = new int[255];

        char from, to;
        int amount;
        for (String txn : inputs) {
            from = txn.charAt(0);
            to = txn.charAt(1);
            amount = txn.charAt(2) - '0';
            if (to == 'a') {
                toA[from] += amount;
            } else if (from == 'a') {
                toA[to] -= amount;
            } else {
                // B to A, A to C
                toA[from] += amount;
                aTo[to] += amount;
            }
        }

        List<String> res = new ArrayList<>();
        for (int i = 0; i < toA.length; i++) {
            if (toA[i] > 0) {
                String out =  (char) i + "a" + toA[i];
                res.add(out);
            } else if (toA[i] < 0) {
                String out =  "a" + (char) i + -toA[i];
                res.add(out);
            }
            if (aTo[i] > 0) {
                String out = "a" + (char) i + aTo[i];
                res.add(out);
            } else if (aTo[i] < 0) {
                String out = (char) i + "a" + -aTo[i];
                res.add(out);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        List<String> input = Arrays.asList("ab1", "ba2", "bc3", "cd8", "ab7");
        CentralBankBalance cb = new CentralBankBalance();
        System.out.println(cb.simplify(input));
    }
}
