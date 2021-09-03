package com.linsizhe.snapchat;

import java.util.ArrayList;
import java.util.List;

// https://leetcode.com/problems/employee-free-time/
public class EmployeeFreeTime {
    class Interval {
        public int start;
        public int end;

        public Interval() {}

        public Interval(int _start, int _end) {
            start = _start;
            end = _end;
        }
    };
    public List<Interval> employeeFreeTime(List<List<Interval>> schedule) {
        List<Interval> sorted = partitionAndMerge(schedule, 0, schedule.size() - 1);
        int i = 0;
        Interval cur = sorted.get(0);
        ArrayList<Interval> out = new ArrayList();
        //String str = printArr(sorted);
        // standard merge interval, single pointer mover
        while (i < sorted.size()) {
            while (i < sorted.size() && cur.end >= sorted.get(i).start) {
                cur.end = Math.max(cur.end, sorted.get(i).end);
                i++;
            }

            int curS = cur.start;
            int curE = cur.end;

            if (i < sorted.size()) {
                if (sorted.get(i).start - cur.end >= 1) {
                    out.add(new Interval(cur.end, sorted.get(i).start));
                }
                cur = sorted.get(i);
            }

        }
        return out;

    }

    // sorted by start time
    public List<Interval> partitionAndMerge(List<List<Interval>> schedule, int start, int end) {
        if (start >= end) {
            return schedule.get(start);
        }
        int mid = start + (end -start) / 2;
        List<Interval> l1 = partitionAndMerge(schedule, start, mid);
        List<Interval> l2 = partitionAndMerge(schedule, mid + 1, end);
        return merge(l1, l2);
    }

    public List<Interval> merge(List<Interval> l1, List<Interval> l2) {
        List<Interval> out = new ArrayList<>();
        int i = 0;
        int j = 0;
        // sorted by start time
        // like merge k sorted list, pairwise compare from frontier and update frontier
        while (i < l1.size() || j < l2.size()) {
            if (i == l1.size()) {
                out.add(l2.get(j++));
            } else if (j == l2.size()) {
                out.add(l1.get(i++));
            } else if (l1.get(i).start <= l2.get(j).start) {
                out.add(l1.get(i++));
            } else {
                out.add(l2.get(j++));
            }
        }
        return out;
    }
}
