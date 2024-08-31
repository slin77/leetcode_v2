package com.linsizhe.hiring2024;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
//https://leetcode.com/problems/minimum-time-to-collect-all-apples-in-a-tree/
public class MinimumTimeToCollectAppleFromTree1443 {
    HashSet<String> marked =  new HashSet();
    int n;

    // path to get a apple is (from root to node) * 2
    // if two apple have  common acester, common just count once, use hashset to store
    // so not double counting will be there.
    public int minTime(int n, int[][] edges, List<Boolean> hasApple) {
        HashMap<Integer, List<Integer>> adj = new HashMap();
        this.n = n;
        for  (int[] edge: edges) {// undirecteds so doubly linked
            adj.putIfAbsent(edge[0], new ArrayList());
            adj.get(edge[0]).add(edge[1]);
            adj.putIfAbsent(edge[1], new ArrayList());
            adj.get(edge[1]).add(edge[0]);
        }
        dfs(0, adj, hasApple, new ArrayList(), -1, 0);
        HashSet<String> marked1 = marked;
        return marked.size() * 2;
    }

    void dfs(int i, HashMap<Integer, List<Integer>> adj, List<Boolean> hasApple, List<String> curPath, int parent, int num) {
        if (hasApple.get(i)) {
            num++;
            for (String p : curPath) {
                marked.add(p);
            }
            if (num == n) return;
        }
        if (adj.containsKey(i)) {
            for (int next : adj.get(i)) {
                if (next != parent) {// make sure not go to parent for cycle
                    curPath.add(i+ ""+  next);
                    dfs(next, adj, hasApple, curPath, i, n);
                    curPath.remove(curPath.size() - 1);
                }
            }
        }

    }
}
