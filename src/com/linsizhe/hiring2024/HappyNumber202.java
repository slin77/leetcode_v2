package com.linsizhe.hiring2024;

import java.util.HashSet;

public class HappyNumber202 {
    public boolean isHappy(int n) {
        HashSet<Integer> visited = new HashSet();
        while (n != 1) {
            if (visited.contains(n)) return false;
            visited.add(n);
            String str = String.valueOf(n);
            n = 0;
            for (char c : str.toCharArray()) {
                n += (c - '0') * (c - '0');
            }
        }
        return true;
    }
}
