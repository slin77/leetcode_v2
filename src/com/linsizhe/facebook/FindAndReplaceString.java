package com.linsizhe.facebook;

import java.util.HashMap;

//https://leetcode.com/problems/find-and-replace-in-string/

// piece table", which is used in editors. We record all the valid operations first and put them into a piece table,
// then iterate the string index to "apply" these operations.
public class FindAndReplaceString {
    public String findReplaceString(String s, int[] indices, String[] sources, String[] targets) {
        HashMap<Integer, Integer> replacement = new HashMap();
        for (int i = 0; i < indices.length; i++) {
            if (s.startsWith(sources[i], indices[i])) {
                // think i as a pointer
                // we use it to find corresponding entry in sources, indices and targets
                // so we store it in map
                replacement.put(indices[i], i);
            }
        }
        StringBuilder sb = new StringBuilder();
        int i = 0;
        while (i < s.length()) {
            if (replacement.containsKey(i)) {
                sb.append(targets[replacement.get(i)]);
                i += sources[replacement.get(i)].length();
            } else {
                sb.append(s.charAt(i));
                i++;
            }
        }
        return sb.toString();
    }
}
