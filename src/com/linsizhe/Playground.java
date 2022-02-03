package com.linsizhe;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
public class Playground {

    private static class ContentCompare implements Comparator<String> {

        @Override
        public int compare(String o1, String o2) {
            return o1.substring(o1.indexOf(' ', o1.length())).compareTo(o2.substring(o2.indexOf(' ')));
        }

        @Override
        public Comparator<String> thenComparing(Comparator<? super String> other) {
            return new IdentifyCompare();
        }
    }

    private static class IdentifyCompare implements Comparator<String> {
        @Override
        public int compare(String o1, String o2) {
            return o1.substring(0, o1.indexOf(' ') + 1).compareTo(o2.substring(0, o2.indexOf(' ') + 1));
        }
    }

    public static void main(String[] args) {
//        char a = '1';
//        char b = '2';
//        ArrayList<Integer> arr = new ArrayList<>();
//        arr.toArray()
////        String s = String.valueOf({a, b});
//        HashMap<String, List<String>> map = new HashMap();
//        map.values().stream().sorted(Comparator.comparingInt(a -> a.get(0).charAt(0))).collect(Collectors.toList());
//        int i = 10;
//        System.out.println(i);
//        TreeSet ts = new TreeSet();
//
//        HashMap<Integer, int[]> hs = new HashMap();
//        int[] ok = {3, 4};
//        hs.put(1, new int[] {2, 4});
//        System.out.println(hs.get(-2));
//        Comparator.comparingInt().re .then
//        System.out.println('z' - 'a');
//        System.out.println(25 % 26);
//        HashMap<Integer, Integer> freq = new HashMap<>();
//         PriorityQueue pq = new PriorityQueue();
////        LinkedList ll;
//        HashSet<Integer> set = new HashSet<>();
//        //String s = "1234";
//        String[] out = {};
//        String s = "dig1 8 1 5 1";
//
//        System.out.println(s.indexOf(' '));
//        PriorityQueue<String> pq= new PriorityQueue<>(String::compareTo);
//        StringBuilder sb = new StringBuilder();
//        LinkedList<String> ls = new LinkedList<>();
//        String.join(ls.get(0), ls.get(1), ls.get(2));
//
//        String s1 = "abc";
//        String s2 = "abde";

    }
}



