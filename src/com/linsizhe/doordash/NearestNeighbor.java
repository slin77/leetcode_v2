package com.linsizhe.doordash;

import java.util.*;

//Nearest Neighbour City
//
//        A number of cities are arranged on a graph that has been divided up like an ordinary Cartesian plane.
//        Each city is located at an integral (x, y) coordinate intersection. City names and locations are given in the
//        form of three arrays: c, x, and y, which are aligned by the index to provide the city name (c[i]), and its
//        coordinates, (x[i], y[i]). Determine the name of the nearest city that shares either an x or a y coordinate
//        with the queried city. If no other cities share an x or y coordinate, return 'NONE'. If two cities have the
//        same distance to the queried city, q[i], consider the one with an alphabetically shorter name
//        (i.e. 'ab' < 'aba' < 'abb') as the closest choice. The distance is the Manhattan distance, the absolute
//        difference in x plus the absolute difference in y.
public class NearestNeighbor {

    private static class Place {
        String name;
        int x;
        int y;
    }

    public static List<String> closestStraightCity(List<String> c, List<Integer> x, List<Integer> y,
                                                   List<String> queries) {
        HashMap<Integer, List<Place>> xMap = new HashMap<>();
        HashMap<Integer, List<Place>> yMap = new HashMap<>();
        HashMap<String, Place> nameMap = new HashMap<>();

        for (int i = 0; i < c.size(); i++) {
            Place p = new Place();
            p.name = c.get(i);
            p.x = x.get(i);
            p.y = y.get(i);

            xMap.putIfAbsent(p.x, new ArrayList<>());
            yMap.putIfAbsent(p.y, new ArrayList<>());

            xMap.get(p.x).add(p);
            yMap.get(p.y).add(p);
            nameMap.put(p.name, p);
        }

        xMap.values().forEach(item ->
                item.sort(Comparator.<Place>comparingInt(city -> city.y).thenComparing(city -> city.name)));
        yMap.values().forEach(item ->
                item.sort(Comparator.<Place>comparingInt(city -> city.x).thenComparing(city -> city.name)));

        ArrayList<String> out = new ArrayList<>();
        for (int i = 0; i < queries.size(); i++) {
            Place query = nameMap.get(queries.get(i));

            Place clostX = getClosetCity(xMap.getOrDefault(query.x, new ArrayList<>()), query, Comparator.<Place>comparingInt(p -> p.y));
            Place closty = getClosetCity(yMap.getOrDefault(query.y, new ArrayList<>()), query, Comparator.<Place>comparingInt(p -> p.x));

            if (clostX == null && closty == null) {
                out.add("NONE");
            } else if (clostX == null) {
                out.add(closty.name);
            } else if (closty == null) {
                out.add(clostX.name);
            } else {
                if (getDistance(clostX, query) < getDistance(closty, query)) {
                    out.add(clostX.name);
                } else if (getDistance(clostX, query) > getDistance(closty, query)) {
                    out.add(closty.name);
                } else {
                    out.add(getBestMatchCity(clostX, closty, query).name);
                }
            }
        }

        return out;

    }

    // xs is all with same x
    private static Place getClosetCity(List<Place> xs, Place query, Comparator<Place> comparator) {
        if (xs.size() <= 1) return null;
        int idx = Collections.binarySearch(xs, query, comparator);
        if (idx == xs.size() - 1) {
            return xs.get(idx - 1);
        } else if (idx == 0) {
            return xs.get(1);
        } else {
            Place left = xs.get(idx - 1);
            Place right = xs.get(idx + 1);
            return getBestMatchCity(left, right, query);
        }
    }

    private static Place getBestMatchCity(Place left, Place right, Place query) {
        if (getDistance(left, query) < getDistance(right, query)) {
            return left;
        } else if (getDistance(left, query) > getDistance(right, query)) {
            return right;
        } else {
            return left.name.compareTo(right.name) < 0 ? left : right;
        }
    }

    private static int getDistance(Place x, Place y) {
        return Math.abs(x.x - y.x) + Math.abs(x.y - y.y);
    }
}
