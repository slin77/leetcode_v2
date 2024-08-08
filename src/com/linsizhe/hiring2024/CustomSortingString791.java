package com.linsizhe.hiring2024;

//  https://leetcode.com/problems/custom-sort-string/
public class CustomSortingString791 {
    // public String customSortString(String order, String s) {
    //     Map<Character, Integer> map = new HashMap();
    //     for (int i = 0; i < order.length(); i++) {
    //         map.put(order.charAt(i), i);
    //     }
    //     Character[] temp = new Character[s.length()];
    //     for (int i = 0; i < s.length(); i++) {
    //         temp[i] = s.charAt(i);
    //     }
    //     // Comparator<Character> cmp = (ch1, ch2)-> (map.getOrDefault(ch1, -1)  - map.getOrDefault(ch2, -1));
    //     Arrays.sort(temp, (ch1, ch2)-> (map.getOrDefault(ch1, -1)  - map.getOrDefault(ch2, -1)));
    //     StringBuilder sb = new StringBuilder();
    //     for (char c : temp) {
    //         sb.append(c);
    //     }
    //     return sb.toString();
    // }

    // frequency table and counting
    public String customSortString(String order, String s) {
        int[] freq = new int[255];
        for (char c: s.toCharArray()) {
            freq[c]++;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < order.length(); i++) {
            int time = freq[order.charAt(i)];
            if (time > 0) {
                for (int j = 0; j <  time; j++) {
                    sb.append(order.charAt(i));
                }
            }
            freq[order.charAt(i)] = 0;
        }

        // append what is left not in order at end of string
        for (int i = 0; i < 255; i++) {
            if (freq[i] != 0) {
                for (int j = 0; j < freq[i]; j++) {
                    sb.append((char)i);
                }
            }
        }

        return sb.toString();
    }
}
