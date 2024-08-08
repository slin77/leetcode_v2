package com.linsizhe.coinbase;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class CurrencyExchange {
    private double maxCanGet = -1;

    public double getTarget(String[][] rates, String start, String end) {
        HashMap<String, HashMap<String, Double>> graph = new HashMap<>();
        for (String[] rate : rates) {
            graph.putIfAbsent(rate[0], new HashMap<>());
            graph.putIfAbsent(rate[1], new HashMap<>());
            graph.get(rate[0]).put(rate[1], Double.valueOf(rate[3]));
            graph.get(rate[1]).put(rate[0], 1.0 / Double.valueOf(rate[2]));
        }
        dfs(graph, 1, start, end, new HashSet<>());
        return maxCanGet;
    }

    public void dfs(HashMap<String, HashMap<String, Double>> graph, double curAmount, String cur, String target, HashSet<String> visited) {
        if (cur.equals(target)) {
            maxCanGet = Math.max(curAmount, maxCanGet);
            return;
        }

        visited.add(cur);
        for (Map.Entry<String, Double> e : graph.getOrDefault(cur, new HashMap<>()).entrySet()) {
            if (!visited.contains(e.getKey())) {
                dfs(graph, curAmount * e.getValue(), e.getKey(), target, visited);
            }
        }
        visited.remove(cur);
    }

    public static void main(String[] args) {
        CurrencyExchange ce = new CurrencyExchange();
        String[][] rates = new String[][]{{"BTC", "YEN", "7.0", "6.0"}, {"BTC", "CAD", "5.0", "4.0"}, {"CAD", "YEN", "11.0", "10.0"}};
        System.out.println(ce.getTarget(rates, "BTC", "YEN"));
    }
}
