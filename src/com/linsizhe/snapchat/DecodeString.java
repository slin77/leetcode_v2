package com.linsizhe.snapchat;

public class DecodeString {
    public String decodeString(String s) {
        int time = 0;
        StringBuilder buf = new StringBuilder();
        int i = 0;
        while (i < s.length()) {
            if (Character.isDigit(s.charAt(i))) {
                time = time * 10 + s.charAt(i) - '0';
                i++;
            } else if (s.charAt(i) == '[') {
                int count = 1;
                int idx = i;
                for (int j = i + 1; j < s.length(); j++) {
                    if (s.charAt(j) == '[') {
                        count++;
                    } else if (s.charAt(j) == ']') {
                        count--;
                        if (count == 0) {
                            idx = j;
                            break;
                        }
                    }
                }
                for (int r = 0; r < time; r++) {
                    buf.append(decodeString(s.substring(i + 1, idx)));
                }
                i = idx + 1;
                time = 0;
            } else {
                time = 0;
                buf.append(s.charAt(i));
                i++;
            }
        }
        return buf.toString();
    }
}
