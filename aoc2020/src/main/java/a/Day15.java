package a;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Day15 {
    public static boolean debug = true;

    public static void main(String[] args) {
        var seen = new ArrayList<Integer>();
//        seen.add(0);
//        seen.add(3);
//        seen.add(6);

        // For each spoken number, remember which
        // turns it appeared in
        var lookup = new HashMap<Integer, List<Integer>>();
//        lookup.put(0, new ArrayList<>(List.of(0)));
//        lookup.put(3, new ArrayList<>(List.of(1)));
//        lookup.put(6, new ArrayList<>(List.of(2)));

        // Puzzle input
        seen.add(12);
        seen.add(1);
        seen.add(16);
        seen.add(3);
        seen.add(11);
        seen.add(0);

        lookup.put(12, new ArrayList<>(List.of(0)));
        lookup.put(1, new ArrayList<>(List.of(1)));
        lookup.put(16, new ArrayList<>(List.of(2)));
        lookup.put(3, new ArrayList<>(List.of(3)));
        lookup.put(11, new ArrayList<>(List.of(4)));
        lookup.put(0, new ArrayList<>(List.of(5)));

//        for (int i = 3; i < 2020; i++) {
        for (int i = 6; i < 30000000; i++) {
            if (debug) {
                if (i % 100000 == 0) {
                    System.out.println("turn: " + i);
                }
            }
            int prev = seen.get(i - 1);

            int age = 0;
            if (lookup.containsKey(prev)) {
                var l = lookup.get(prev);
                // Not interested in the case where the only occurence of an
                // age is the previous turn
                if (l.size() > 1) {
                    age = i - 1 - l.get(l.size() - 2);
                }
            }
            seen.add(age);
            var l = lookup.get(age);
            if (l == null) {
                l = new ArrayList<>();
                lookup.put(age, l);
            }
            l.add(i);
        }

        if (debug) {
            for (int i = 0; i < 10; i++) {
                System.out.println(seen.get(i));
            }
            System.out.println("Number of lookup ages: " + lookup.keySet().size());
            System.out.println("Lookup age 3: " + lookup.get(3));
        }
        System.out.println("2020th turn: " + seen.get(seen.size() - 1));
    }
}
