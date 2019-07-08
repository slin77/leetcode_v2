package com.linsizhe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;


//follow up:
//1.Imagine you are given a real file system, how will you search files? DFS or BFS ? depends on depth
//If the file content is very large (GB level), how will you modify your solution? use metadata. Filesize + hash, checksum
//If you can only read the file by 1kb each time, how will you modify your solution?
//What is the time complexity of your modified solution? What is the most time consuming part and memory consuming part of it? How to optimize?
//How to make sure the duplicated files you find are not false positive?
class DuplicateInSystem {
    public List<List<String>> findDuplicate(String[] paths) {
        HashMap<String, List<String>> contentToPath = new HashMap();
        for (String path : paths) {
            String[] parts = path.split(" ");
            String dir = parts[0];
            if (!dir.endsWith("/")) {
                dir = dir + "/";
            }

            for (int i = 1; i < parts.length; i++) {
                String nameAndContent = parts[i];
                int idx = nameAndContent.indexOf(".");
                String content = nameAndContent.substring(idx + 5, nameAndContent.length());
                String name = nameAndContent.substring(0, idx + 4);
                contentToPath.putIfAbsent(content, new ArrayList<String>());
                contentToPath.get(content).add(dir + name);
            }
        }
        return contentToPath.values().stream()
                .filter(x -> x.size() > 1)
                .collect(Collectors.toList());
    }
}
