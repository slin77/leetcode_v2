package com.linsizhe.scripe;

import java.util.*;

public class ParseHttpHeader {
    public List<String> getSupportedTags(String input, Set<String> supported) {
        String[] tags = input.split(",\\s+");
        ArrayList<String> out = new ArrayList<>();
        for (String tag : tags) {
            if (supported.contains(tag)) {
                out.add(tag);
            }
        }
        return out;
    }

    public List<String> getSupportedTags2(String input, Set<String> supported) {
        Map<String, Set<String>> supportedByPrefix = new HashMap<>();
        List<String> res = new ArrayList<>();
        for (String lang : supported) {
            String[] parts = lang.split("-");
            supportedByPrefix.putIfAbsent(parts[0], new HashSet<>());
            supportedByPrefix.get(parts[0]).add(lang);
        }
        return res;
    }
}
