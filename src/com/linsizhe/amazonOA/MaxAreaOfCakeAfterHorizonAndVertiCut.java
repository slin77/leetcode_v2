package com.linsizhe.amazonOA;

import java.util.Arrays;

public class MaxAreaOfCakeAfterHorizonAndVertiCut {
    private static int MOD = 1000000007;
    public int maxArea(int h, int w, int[] horizontalCuts, int[] verticalCuts) {
        Arrays.sort(horizontalCuts);
        Arrays.sort(verticalCuts);

        int maxH = Math.max(0, horizontalCuts[0] - 0);
        for (int i = 0; i < horizontalCuts.length; i++) {
            int gap = i == horizontalCuts.length - 1 ? h - horizontalCuts[i] : horizontalCuts[i + 1] - horizontalCuts[i];
            maxH = Math.max(maxH, gap);
        }

        int maxW = Math.max(0, verticalCuts[0] - 0);
        for (int i = 0; i < verticalCuts.length; i++) {
            int gap = i == verticalCuts.length - 1 ? w - verticalCuts[i] : verticalCuts[i + 1] - verticalCuts[i];
            maxW = Math.max(maxW, gap);
        }
        long res = (long) maxH * maxW;
        return (int) (res % MOD);
    }
}
