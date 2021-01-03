package a;

import java.util.ArrayList;

public class Day15 {
    public static boolean debug = true;

    public static void main(String[] args) {
        var seen = new ArrayList<Integer>();
        seen.add(0);
        seen.add(3);
        seen.add(6);

        // Puzzle input
//        seen.add(12);
//        seen.add(1);
//        seen.add(16);
//        seen.add(3);
//        seen.add(11);
//        seen.add(0);

        for (int i = 3; i < 2020; i++) {
            if (debug) {
                if (i % 1000 == 0) {
                    System.out.println("turn: " + i);
                }
            }
            int prev = seen.get(i - 1);

            boolean found = false;
            int x = 0;
            for (int j = i - 2; j >= 0; j--) {
                if (seen.get(j) == prev) {
                    found = true;
                    x = j;
                    break;
                }
            }

            if (found) {
                seen.add(i - 1 - x);
            } else {
                seen.add(0);
            }
        }

        if (debug) {
            for (int i = 0; i < 10; i++) {
                System.out.println(seen.get(i));
            }
        }
        System.out.println("2020th turn: " + seen.get(seen.size() - 1));
    }
}
