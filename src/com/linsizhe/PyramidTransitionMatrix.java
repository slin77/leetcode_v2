package com.linsizhe;

import java.util.Arrays;
import java.util.List;

public class PyramidTransitionMatrix {

    int allowedLength;
    List<String> allowed;
    public boolean pyramidTransition(String bottom, List<String> allowed) {
        this.allowedLength = allowed.size();
        this.allowed = allowed;
        char[][] curMap = new char[bottom.length()][bottom.length()];
        for (int i = 0 ; i < bottom.length(); i++) {
            curMap[bottom.length() - 1][i] = bottom.charAt(i);
        }
        return getLayer(bottom.length() - 2, 0, 0, curMap);
    }

    public boolean getLayer(int layer, int idx, int listIdx, char[][] curMap) {
        if (layer == -1) {
            return true;
        }
        char original = curMap[layer][idx];
        if (idx > layer) {
            return getLayer(layer - 1, 0, 0, curMap);
        } else if (listIdx < allowedLength) {
            char a = curMap[layer + 1][idx];
            char b = curMap[layer + 1][idx + 1];
            if (allowed.get(listIdx).startsWith(a + "" + b)) {
                curMap[layer][idx] = allowed.get(listIdx).charAt(2);
                boolean res = getLayer(layer, idx + 1, 0, curMap);
                if (res) {
                    return res;
                } else {
                    curMap[layer][idx] = original;
                }
            }
            return getLayer(layer, idx, listIdx + 1, curMap);
        } else if (listIdx == allowedLength) {
            if (curMap[layer][idx] != '\u0000') {
                return getLayer(layer, idx + 1, 0, curMap);
            }
        }
        return false;
    }

    public static void main (String[] args) {
        String bottom ="CBDDA";
        String[] arras ={"ACC","ACA","AAB","BCA","BCB","BAC","BAA","CAC","BDA","CAA","CCA","CCC","CCB","DAD","CCD","DAB","ACD","DCA","CAD","CBB","ABB","ABC","ABD","BDB","BBC","BBA","DDA","CDD","CBC","CBA","CDA","DBA","ABA"};
        List<String> allowed =  Arrays.asList(arras);
        PyramidTransitionMatrix ptm = new PyramidTransitionMatrix();
        System.out.println(ptm.pyramidTransition(bottom, allowed));
    }
}
