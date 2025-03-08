import util.Point;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day10 {
    public static void main(String[] args) {
        var d = new Day10();
        d.example();
    }

    void example() {
        System.out.println("part 1, first example");

        var s = """
                0123
                1234
                8765
                9876""";
        System.out.println(s);

        var hm = parseInput(s);
        System.out.println(hm);

        List<Trail> found = findLongTrails(hm);
        System.out.println("found: " + found);
        assert found.size() == 1;
    }

    HeightMap parseInput(String s) {
        HeightMap result = new HeightMap();

        var lines = s.split("\n");
        for (int i = 0; i < lines.length; i++) {
            var line = lines[i];
            for (int j = 0; j < line.length(); j++) {
                var p = line.charAt(j);
                result.put(new Point(j, i), Character.getNumericValue(p));
            }
        }

        return result;
    }

    List<Trail> findTrailHeads(Map<Point, Integer> heightMap) {
        var trailHeads = heightMap.entrySet().stream()
                .filter(e -> e.getValue() == 0)
                .map(Map.Entry::getKey)
                .toList();
        System.out.println(trailHeads);

        List<Trail> seen = new ArrayList<>();
        for (var entryPoint : trailHeads) {
            var t = new Trail();
            t.add(entryPoint);
            seen.add(t);
        }
        return seen;
    }

    List<Trail> findLongTrails(HeightMap hm) {
        var found = new ArrayList<Trail>();

        List<Trail> seen = findTrailHeads(hm);
        System.out.println(seen);
        assert seen.size() == 1;

        while (!seen.isEmpty()) {
            if (debug) {
                System.out.println("seen: " + seen);
            }
            var t = seen.removeFirst();

            Point curr = t.getLast();
            if (hm.get(curr) == 9) {
                // found a complete trail
                found.add(t);
                continue;
            }

            for (var x : nextMoves(curr, hm)) {
                var newTrail = new Trail(t);
                newTrail.add(x);
                seen.add(newTrail);
            }
        }

        return found;
    }

    static final boolean debug = true;

    static class HeightMap extends HashMap<Point, Integer> {
        Integer get(Point point) {
            return getOrDefault(point, 0);
        }
    }

    enum Heading {
        UP(new Point(0, -1)),
        RIGHT(new Point(1, 0)),
        DOWN(new Point(0, 1)),
        LEFT(new Point(-1, 0));

        final Point facing;

        Heading(Point p) {
            this.facing = p;
        }
    }

    List<Point> nextMoves(Point fromPos, HeightMap hm) {
        var result = new ArrayList<Point>();

        var currHeight = hm.get(fromPos);

        for (Heading h : Heading.values()) {
            var nextPos = new Point(
                    fromPos.x() + h.facing.x(),
                    fromPos.y() + h.facing.y()
            );
            if (hm.get(nextPos) == currHeight + 1) {
                result.add(nextPos);
            }
        }

        return result;
    }

    static class Trail extends ArrayList<Point> {
        public Trail() {
            super();
        }

        public Trail(Trail t) {
            super(t);
        }
    }
}
