package com.linsizhe.robinhood;

/*
We want to find employees who badged into our secured room together often. Given an unordered list of names and access times over a single day, find the largest group of people that were in the room together during two or more separate time periods, and the times when they were all present.
badge_records = [
["Paul", "1214", "enter"],
["Paul", "830", "enter"],
["Curtis", "1100", "enter"],
["Paul", "903", "exit"],
["John", "908", "exit"],
["Paul", "1235", "exit"],
["Jennifer", "900", "exit"],
["Curtis", "1330", "exit"],
["John", "815", "enter"],
["Jennifer", "1217", "enter"],
["Curtis", "745", "enter"],
["John", "1230", "enter"],
["Jennifer", "800", "enter"],
["John", "1235", "exit"],
["Curtis", "810", "exit"],
["Jennifer", "1240", "exit"],
]
Expected output:
John, Paul, Jennifer: 830 to 900, 1230 to 1235
For this input data:
From 830 til 900, the room contains Jennifer, John, and Paul.
From 1230 til 1235, the room contains Curtis, Paul, Jennifer, and John.
The group "Jennifer, John, Paul" exists at both of these times, and is the largest group that exists multiple times.
You should note that the group is a subset of the people in the room from 1230 to 1235
*/

import java.util.*;

public class FindLargestGroup {

    class Stay {
        String name;
        int in;
        int out;
        public Stay(String name, int in, int out) {
            this.name = name;
            this.in = in;
            this.out = out;
        }
    }

    public List<String> mostGroup(String[][] input) {
        HashMap<String, List<String[]>> stays = new HashMap<>();
        for (String[] s : input) {
            stays.putIfAbsent(s[0], new ArrayList<>());
            stays.get(s[0]).add(s);
        }
        for (List<String[]> list : stays.values()) {
            list.sort(Comparator.comparingInt(s -> Integer.valueOf(s[1])));
        }
        ArrayList<Stay> allStays = new ArrayList<>();

        for (Map.Entry<String, List<String[]>> entry : stays.entrySet()) {
            for (int i = 0; i < entry.getValue().size() - 1; i++) {
                allStays.add(new Stay(entry.getKey(),
                        Integer.valueOf(entry.getValue().get(i)[1]),
                        Integer.valueOf(entry.getValue().get(i + 1)[1])));
            }
        }

        allStays.sort(Comparator.comparingInt(s -> s.in));

        HashSet<String> curList = new HashSet<>();
        HashMap<HashSet<String>, Integer> frequent = new HashMap<>();
        int[] cur = new int[2];

        for (Stay s : allStays) {
            if (s.in < cur[1]) {

            }
        }
        return null;
    }
}
