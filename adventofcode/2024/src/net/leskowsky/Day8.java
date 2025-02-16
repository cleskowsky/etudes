package net.leskowsky;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Day8 {
    record SignalMap(Map<Character, List<Point>> signals) {
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

    static List<Pair> pairs(List<Point> locs) {
        return List.of();
    }

    static List<Point> findAntinodesFor(Pair p) {
        return List.of();
    }
}
