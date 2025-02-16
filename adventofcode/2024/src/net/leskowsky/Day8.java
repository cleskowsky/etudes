package net.leskowsky;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Day8 {
    record SignalMap(Map<Character, List<Point>> signals, int limitX, int limitY) {
        int count() {
            return signals.size();
        }

        List<Point> get(char c) {
            return signals.get(c);
        }
    }

    record Pair(Point p1, Point p2) {
    }

    Set<Point> antinodes = new HashSet<>();

    static Set<Pair> pairs(List<Point> locs) {
        var result = new HashSet<Pair>();
        for (int i = 0; i < locs.size(); i++) {
            for (int j = i + 1; j < locs.size(); j++) {
                if (i == j) {
                    continue;
                }
                result.add(new Pair(locs.get(i), locs.get(j)));
            }
        }
        return result;
    }

    void findAntinodesFor(Pair p) {

    }
}
