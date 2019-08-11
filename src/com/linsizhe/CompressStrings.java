package com.linsizhe;

import java.util.ArrayList;
import java.util.List;

// For Affirm
public class CompressStrings {
    public String compress(String input) {
        char[] chars = input.toCharArray();
        char lastChar = ' ';
        int count = 1;
        StringBuilder sb = new StringBuilder();
        for (char c : chars) {
            if (c == lastChar) {
                count++;
                lastChar = c;
            } else {
                if (lastChar != ' ') {
                   sb.append(lastChar);
                }
                if (count > 1) {
                    sb.append(count);
                }
                lastChar = c;
                count = 1;
            }
        }
        sb.append(lastChar);
        if (count > 1) {
            sb.append(count);
        }
        return sb.toString();
    }

    public String deCompress(String inputs) {
        char lastChar = ' ';
        String length = "";
        char[] chars = inputs.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            if ('0' <= c && c <= '9') {
                length += c;

            } else {
                if (lastChar != ' ') {
                    if (length.equals("")) {
                        sb.append(lastChar);
                    } else {
                        for (int j = 0; j < Integer.valueOf(length); j++) {
                            sb.append(lastChar);
                        }
                    }
                }
                length = "";
                lastChar = c;
            }
        }
        if (lastChar != ' ') {
            if (length.equals("")) {
                sb.append(lastChar);
            } else {
                for (int j = 0; j < Integer.valueOf(length); j++) {
                    sb.append(lastChar);
                }
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        CompressStrings cs = new CompressStrings();
        String s = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaabbbbbbuuuuuyyyyyesssksjsjksjs";
        System.out.println(cs.deCompress(cs.compress(s)));
        System.out.println(s.equals(cs.deCompress(cs.compress(s))));
    }
}
