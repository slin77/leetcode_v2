package com.linsizhe.facebook;

// https://leetcode.com/problems/angle-between-hands-of-a-clock/
public class AngleBetweenHandsOfAClock {
    public double angleClock(int hour, int minutes) {
        double h = 30 * (double) hour + 30 * ((double) minutes / 60);
        double m = 360 * (double) minutes / 60;
        double diff = Math.abs(m - h);
        return Math.min(diff, 360 - diff);
    }
}
