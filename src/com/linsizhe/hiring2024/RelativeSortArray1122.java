package com.linsizhe.hiring2024;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
//https://leetcode.com/problems/relative-sort-array
public class RelativeSortArray1122 {
    public int[] relativeSortArray(int[] arr1, int[] arr2) {
        HashMap<Integer, Integer> m2 = new HashMap(); // frequency for m1
        ArrayList<Integer> l2 = new ArrayList(); // items not in arr2
        ArrayList<Integer> l1 = new ArrayList(); // items in arr2
        for (int i : arr1) {
            m2.put(i, m2.getOrDefault(i, 0) + 1);
        }
        for (int i = 0; i < arr2.length; i++) {// in order for arr2
            if (m2.containsKey(arr2[i])) {
                for (int i1 = 0; i1 < m2.get(arr2[i]); i1++) {
                    l1.add(arr2[i]);// add duplidates
                }
                m2.remove(arr2[i]);// Important! remove so what is left is NOT in arr2, smart!
            }
        }
        for (int i : m2.keySet()) {
            for (int i1 = 0; i1 < m2.get(i); i1++) {
                l2.add(i);
            }
        }
        Collections.sort(l2);
        int idx = 0;
        int[] res = new int[arr1.length];
        for (int i : l1) {
            res[idx++] = i;
        }
        for (int i : l2) {
            res[idx++] = i;
        }
        return res;
    }
}
