package com.linsizhe.facebook;

import java.util.*;

public class TaskScheduler {
    private class TaskCount {
        int count;
        char task;
        TaskCount(char task, int count) {
            this.count = count;
            this.task = task;
        }

        @Override
        public String toString() {
            return "count:" + count + " task:" + task;
        }
    }

    public int leastInterval(char[] tasks, int n) {
        int minLength = n + 1; // min length we have to fill within each group.
        int time = 0;
        HashMap<Character, Integer> map = new HashMap<>();
        PriorityQueue<TaskCount> queue = new PriorityQueue<>(Comparator.<TaskCount>comparingInt(tk -> tk.count).reversed());
        for (char task : tasks) {
            map.put(task, map.getOrDefault(task, 0) + 1);
        }
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            queue.add(new TaskCount(entry.getKey(), entry.getValue()));
        }
        int diff = 0;
        int remain = tasks.length;
        while (!queue.isEmpty()) {
            ArrayList<TaskCount> buf = new ArrayList<>();
            while (diff < minLength) {
                if (!queue.isEmpty()) {
                    TaskCount newTask = queue.poll();
                    diff++;
                    newTask.count--;
                    time++;
                    remain--;
                    if (remain == 0) {
                        return time;
                    }
                    if (newTask.count > 0) {
                        buf.add(newTask);
                    }
                } else {
                    // fill in idle
                    diff++;
                    time++;
                }

            }
            for (TaskCount tc : buf) {
                queue.add(tc);
            }
            diff = 0;
        }
        return time;
    }
}
