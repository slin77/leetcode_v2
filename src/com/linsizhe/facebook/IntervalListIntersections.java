package com.linsizhe.facebook;

import java.util.ArrayList;

// https://leetcode.com/problems/interval-list-intersections/
public class IntervalListIntersections {
    public int[][] intervalIntersection(int[][] firstList, int[][] secondList) {
        int i = 0;
        int j = 0;
        ArrayList<int[]> arrList = new ArrayList();
        // algo: Update pointer to the one with earlier end time.
        // standard two pointer template!
        // when either still have space to move
        while (i < firstList.length || j < secondList.length) {
            // if one already crossed boundary, move the other
            if (i == firstList.length) {
                j++;
                continue;
            }

            if (j == secondList.length) {
                i++;
                continue;
            }
            // either in boundary, start calculation.
            int[] interval = getIntersect(firstList[i], secondList[j]);

            if (interval != null) {
                arrList.add(interval);
            }


            if (firstList[i][1] <= secondList[j][1]) {
                i++;
            } else {
                j++;
            }
        }

        int[][] out = new int[arrList.size()][2];
        arrList.toArray(out);
        return out;
    }

    int[] getIntersect(int[] first, int[] second) {
        if (first[0] < second[0]) {
            if (first[1] >= second[0]) {
                return new int[]{second[0], Math.min(first[1], second[1])};
            }
        } else if (first[0] == second[0]) {
            return new int[]{first[0], Math.min(first[1], second[1])};
        } else {
            if (second[1] >= first[0]) {
                return new int[] {first[0], Math.min(second[1], first[1])};
            }
        }
        return null;
    }
}
