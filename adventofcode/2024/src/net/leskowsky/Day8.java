package net.leskowsky;

import java.util.ArrayList;
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
            return p.x() < limitX && p.y() < limitY;
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

    void addAntinodes(Point p1, Point p2, SignalMap signalMap) {
        var result = new ArrayList<Point>();

        int dx = p2.x() - p1.x();
        int dy = p2.y() - p1.y();
        Point diff = new Point(dx, dy);

        // antinodes for p1
        result.add(p1.add(diff));
        result.add(p1.sub(diff));

        // antinodes for p2
        result.add(p2.add(diff));
        result.add(p2.sub(diff));

        // add if inside signal map
        result.forEach(x -> {
            if (signalMap.contains(x) &&
                    !x.equals(p1) &&
                    !x.equals(p2)) {
                antinodes.add(x);
            }
        });
    }

    void findAntinodes(SignalMap signalMap) {
        for (Day8.Pair p : pairs(signalMap.get('a'))) {
            addAntinodes(p.p1(), p.p2(), signalMap);
        }
    }
}
