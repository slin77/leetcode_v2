package com.linsizhe.amazonOA;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AmazonMusic {
    static int[] getMusic(int rideDuration, int[] soundDuration) {
        int target = rideDuration - 30;
        int lo = 0;
        int hi = soundDuration.length - 1;
        int[] out = new int[2];
        int curMax = 0;

        Arrays.sort(soundDuration);

        while (lo < hi) {
            int cur = soundDuration[lo] + soundDuration[hi];
            if (cur < target) {
                lo++;
            } else if (cur > target) {
                hi--;
            } else {
                if (soundDuration[lo] > curMax || soundDuration[hi] > curMax) {
                    out[0] = lo;
                    out[1] = hi;
                    curMax = Math.max(soundDuration[lo], soundDuration[hi]);
                    lo++;
                    hi++;
                }
            }
        }
        return out;
    }

    public static void main(String[] args) {
        int rideDuration = 90;
        int[] sounds = new int[] {1,10,25,35,60};
        System.out.println(Arrays.toString(getMusic(rideDuration, sounds)));
    }
}
