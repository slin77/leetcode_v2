package com.linsizhe.facebook;

import java.util.ArrayList;
import java.util.List;

public class findBuildings {
    // Keep a highestSoFar var!
    public int[] findBuildings(int[] heights) {
        List<Integer> out = new ArrayList();
        int maxSoFar = heights[heights.length - 1];
        out.add(heights.length - 1);
        for (int i = heights.length - 2; i >= 0; i--) {
            if (heights[i] > maxSoFar) {
                out.add(i);
                maxSoFar = heights[i];
            }
        }
        int[] outArr = new int[out.size()];
        for (int i = 0; i < out.size(); i++) {
            outArr[i] = out.get(out.size() - 1 - i);
        }
        return outArr;
    }
}
