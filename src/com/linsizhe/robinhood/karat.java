package com.linsizhe.robinhood;

import java.util.*;

public class karat {
    List<String> findMidCourse(String[][] courses) {
        Map<String, List<String>> reqs = new HashMap<>();
        HashMap<String, Integer> indeg = new HashMap<>();
        for (String[] c : courses) {
            if (c[1] == "Course") continue;
            reqs.putIfAbsent(c[0], new ArrayList<>());
            reqs.get(c[0]).add(c[1]);
            indeg.putIfAbsent(c[0], 0);
            indeg.put(c[1], indeg.getOrDefault(c[1], 0) + 1);
        }
        String cur = null;
        ArrayList<String> out = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : indeg.entrySet()) {
            if (entry.getValue() == 0) {
                dfs(entry.getKey(), new ArrayList<>(), out, reqs, indeg);
            }
        }
        return out;
    }


    private void dfs(String cur, List<String> curList, List<String> output, Map<String, List<String>> graph,
                     Map<String, Integer> indeg) {
        if (cur == null) {
            String mid = curList.get((curList.size() - 1) / 2);
            if (!output.contains(mid)) {
                output.add(mid);
            }
            return;
        }

        curList.add(cur);
        List<String> nexts = graph.get(cur);
        if (nexts == null) {
            dfs(null, curList, output, graph, indeg);
        } else {
            for (String next : nexts) {
                dfs(next, curList, output, graph, indeg);
            }
        }
        curList.remove(cur);
    }

    public static void main(String[] args) {
        String[][] inputs = new String[][] {{"Course_3", "Course_7"}, {"Course_0", "Course_1"}, {"Course_1", "Course_2"},
                {"Course_2", "Course_3"}, {"Course_3", "Course_4"}, {"Course_4", "Course_5"},
                {"Course_5", "Course_6"}};
        karat k = new karat();
        System.out.println(k.findMidCourse(inputs));
}
}
