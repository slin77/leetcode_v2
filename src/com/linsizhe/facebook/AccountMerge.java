package com.linsizhe.facebook;

import java.util.*;

// https://leetcode.com/problems/accounts-merge/

// union find
public class AccountMerge {
    private class Account {
        int id;
        String name;
        TreeSet<String> emails;

        public Account(int id, List<String> account) {
            this.id = id;
            this.name = account.get(0);
            this.emails = new TreeSet<String>();
            for (int i = 1; i < account.size(); i++) {
                emails.add(account.get(i));
            }
        }
    }

    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        List<Account> accts = new ArrayList<>();
        for (int i = 0; i < accounts.size(); i++) {
            accts.add(new Account(i, accounts.get(i)));
        }
        int[] idMap = new int[accts.size()];
        for (int i = 0; i < idMap.length; i++) {
            idMap[i] = i;
        }
        for (int i = 0; i < accts.size(); i++) {
            Account ac1 = accts.get(i);
            for (int j = i + 1; j < accts.size(); j++) {
                Account ac2 = accts.get(j);
                if (overlap(ac1, ac2)) {
                    union(i, j, idMap);
                }
            }
        }
        HashMap<Integer, Account> map = new HashMap();
        for (int i = 0; i < idMap.length; i++) {
            int parent = find(i, idMap);
            Account acct = accts.get(i);
            if (map.containsKey(parent)) {
                map.get(parent).emails.addAll(acct.emails);
            } else {
                map.put(parent, acct);
            }
        }
        List<List<String>> out = new ArrayList<List<String>>();
        for (Map.Entry<Integer, Account> entry : map.entrySet()) {
            List<String> li = new ArrayList<>();
            li.add(entry.getValue().name);
            li.addAll(entry.getValue().emails);
            out.add(li);
        }
        return out;
    }

    private boolean overlap(Account acct1, Account acct2) {
        for (String email: acct1.emails) {
            if (acct2.emails.contains(email)) {
                return true;
            }
        }
        return false;
    }

    private void union(int a, int b, int[] parent) {
        int ap = find(a, parent);
        int bp = find(b, parent);
        parent[bp] = ap;
    }

    private int find(int a, int[] parent) {
        while (a != parent[a]) {
            parent[a] = parent[parent[a]];
            a = parent[a];
        }
        return a;
    }
}
