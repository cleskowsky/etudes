package main;

import java.util.HashMap;
import java.util.List;

class Day12 {
    static class Farm extends HashMap<Point, Character> {
        List<Character> getPlots() {
            return values().stream().toList();
        }

        Character getPlot(int x, int y) {
            return get(new Point(x, y));
        }
    }

    record Point(int x, int y) {
    }

    static Farm parseInput(String s) {
        var f = new Farm();

        var rows = s.split("\n");
        for (int i = 0; i < rows.length; i++) {
            for (int j = 0; j < rows[0].length(); j++) {
                var p = new Point(j, i);
                f.put(p, rows[i].charAt(j));
            }
        }

        return f;
    }
}
