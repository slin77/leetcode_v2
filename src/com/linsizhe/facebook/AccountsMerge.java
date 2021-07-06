package com.linsizhe.facebook;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.TreeSet;

// https://leetcode.com/problems/accounts-merge/
public class AccountsMerge {
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        int[] parent = new int[accounts.size()];
        for (int i = 0; i < parent.length; i++) {
            parent[i] = i;
        }
        TreeSet<String>[] accts = new TreeSet[accounts.size()];
        for (int i = 0; i < accounts.size(); i++) {
            accts[i] = new TreeSet();
            for (int j = 1; j < accounts.get(i).size(); j++) {
                accts[i].add(accounts.get(i).get(j));
            }
        }
        for (int i = 0; i < accounts.size(); i++) {
            for (int j = i + 1; j < accounts.size(); j++) {
                if (isSame(i, j, accts)) {
                    int idi = find(i, parent);
                    int idj = find(j, parent);
                    parent[idj] = idi;
                }
            }
        }
        HashSet<Integer> ids = new HashSet<Integer>();
        for (int i = 0; i < parent.length; i++) {
            int id = find(i, parent);
            TreeSet pt = accts[id];
            if (i != id) {
                TreeSet nt = accts[i];
                pt.addAll(nt);
            }
            ids.add(id);
        }
        ArrayList<List<String>> out = new ArrayList();
        for (int i : ids) {
            List<String> ls = new ArrayList();
            ls.add(accounts.get(i).get(0));
            ls.addAll(accts[i]);
            out.add(ls);
        }
        return out;
    }

    private boolean isSame(int ac1, int ac2, TreeSet<String>[] accts) {
        TreeSet<String> act1 = accts[ac1];
        TreeSet<String> act2 = accts[ac2];

        for (String str : act1) {
            if (act2.contains(str)) return true;
        }
        return false;
    }

    private int find(int id, int[] parents) {
        while (parents[id] != id) {
            parents[id] = parents[parents[id]];
            id = parents[id];
        }
        return id;
    }
}
