package com.linsizhe.scripe;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GetMutalWishList {
    Map<Character, List<Character>> map;

    public boolean hasMutualFirstList(char userId) {
        return hasMutualAtIdx(userId, 0);
    }

    public boolean hasMutualAtIdx(char userId, int idx) {
        List<Character> userList = map.get(userId);
        if (userList == null) return false;
        if (userList.size() <= idx) return false;
        List<Character> otherList = map.get(userList.get(idx));
        if (otherList == null) return false;
        if (otherList.size() <= idx) return false;
        return otherList.get(idx) == userList.get(idx);
    }

    public List<Character> getChanged(char userId, int bumped) {
        List<Character> res = new ArrayList<>();
        List<Character> userList = map.get(userId);
        if (userList == null || userList.size() <= bumped || bumped == 0) {
            return res;
        }
        if (hasMutualAtIdx(userId, bumped)) {
            res.add(map.get(userId).get(bumped));
        }
        if (hasMutualAtIdx(userId, bumped - 1)) {
            res.add(map.get(userId).get(bumped - 1));
        }

        //swap
        char temp = userList.get(bumped - 1);
        userList.set(bumped - 1, userList.get(bumped));
        userList.set(bumped, temp);

        if (hasMutualAtIdx(userId, bumped)) {
            res.add(userList.get(bumped));
        }

        if (hasMutualAtIdx(userId, bumped - 1)) {
            res.add(userList.get(bumped - 1));
        }
        return res;
    }
}
