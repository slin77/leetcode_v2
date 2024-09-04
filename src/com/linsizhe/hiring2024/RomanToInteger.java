package com.linsizhe.hiring2024;

import java.util.HashMap;

//  https://leetcode.com/problems/roman-to-integer/
public class RomanToInteger {
    public int romanToInt(String s) {
        HashMap<Character, Integer> map = new HashMap();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);
        int res = 0;
        for (int i = 0; i < s.length(); i++) {
            char c =  s.charAt(i);
            if (i != s.length() -1 &&  c == 'I' && (s.charAt(i + 1) == 'V' || s.charAt(i + 1) == 'X'))  {
                res  -= 1;
            } else if (i != s.length()-1 &&  c == 'X' && (s.charAt(i + 1) == 'L' || s.charAt(i + 1) == 'C')) {
                res -= 10;
            }  else if (i != s.length()-1 &&  c == 'C' && (s.charAt(i + 1) == 'D' || s.charAt(i + 1) == 'M'))  {
                res -= 100;
            } else {
                res += map.get(c);
            }
        }
        return  res;
    }
}
