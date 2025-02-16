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

        boolean contains(Point p) {
            return p.x() >= 0 && p.x() < limitX &&
                    p.y() >= 0 && p.y() < limitY;
        }

        Set<Character> keys() {
            return signals().keySet();
        }
    }

    record Pair(Point p1, Point p2) {
    }

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

    static Set<Point> findAntinodes(Point p1, Point p2, SignalMap signals) {
        int dx = p2.x() - p1.x();
        int dy = p2.y() - p1.y();
        Point diff = new Point(dx, dy);

        var result = new HashSet<Point>();
        List.of(p1.add(diff),
                p2.add(diff),
                p1.sub(diff),
                p2.sub(diff)).forEach(x -> {
            if (x.equals(p1) || x.equals(p2)) {
                return;
            }
            if (!signals.contains(x)) {
                return;
            }
            result.add(x);
        });

        return result;
    }

    static Set<Point> findAntinodes(SignalMap signals) {
        var result = new HashSet<Point>();
        signals.keys().forEach(k -> {
            for (Pair p : pairs(signals.get(k))) {
                result.addAll(findAntinodes(p.p1(), p.p2(), signals));
            }
        });
        return result;
    }
}
