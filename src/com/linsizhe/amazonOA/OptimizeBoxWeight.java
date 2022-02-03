package com.linsizhe.amazonOA;

import java.util.Arrays;

public class OptimizeBoxWeight{
    public static int[] optimize(int[] input) {
        Arrays.sort(input);
        int[] cum = new int[input.length];
        cum[0] = input[0];
        for (int i = 1; i < cum.length; i++) {
            cum[i] = input[i] + cum[i - 1];
        }
        int idx = -1;
        //System.out.println(Arrays.toString(cum));
        for (int i = cum.length - 1; i >= 0; i--) {
            if (cum[cum.length - 1] - cum[i] > cum[i]) {
                idx = i + 1;
                break;
            }
        }
        return Arrays.copyOfRange(input, idx, input.length);
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(optimize(new  int[]{10, 5, 3, 1, 20})));
        System.out.println(Arrays.toString(optimize(new  int[]{1, 2, 3, 5, 8})));
    }
}
