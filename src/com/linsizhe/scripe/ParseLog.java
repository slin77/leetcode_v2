package com.linsizhe.scripe;

public class ParseLog {
    public static void main(String[] args) {
        String input = "BEGIN Y Y END BEGIN N N END";
        String[] tokens = input.split("\\s+");
        int i = 0;
        while (i < tokens.length) {
            String cur = tokens[i];
            if (cur.equals("BEGIN")) {
                StringBuilder sb = new StringBuilder();
                for (int j = i + 1; j < tokens.length; j++) {
                    if (tokens[j].equals("BEGIN")) {
                        i = j + 1;
                        break;
                    } else if (tokens[j].equals("END")) {
                        System.out.println(sb.toString());
                        i = j + 1;
                        break;
                    }  else {
                        sb.append(tokens[j]);
                        sb.append(" ");
                    }
                }
            }
            i++;
        }
    }
}
