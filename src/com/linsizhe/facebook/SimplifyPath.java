package com.linsizhe.facebook;

import java.util.ArrayList;

public class SimplifyPath {
    public String simplifyPath(String path) {
        String[] paths = path.split("/");
        // better if we use a stack here.
        ArrayList<String> out = new ArrayList();
        for (int i = 0 ; i < paths.length; i++) {
            if (paths[i].equals(".")) {
                continue;
            } else if (paths[i].equals("..")) {
                if (!out.isEmpty()) {
                    out.remove(out.size() - 1);
                }
            } else if (!paths[i].equals("")) {
                out.add(paths[i]);
            }
        }
        StringBuilder sb = new StringBuilder();
        for (String s : out) {
            sb.append("/");
            sb.append(s);
        }
        if (sb.length() == 0) {
            sb.append("/");
        }
        return sb.toString();
    }
}
