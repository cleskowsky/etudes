import util.Point;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Day10 {
    public static void main(String[] args) {
        var d = new Day10();
        d.example();
        d.example2();
        try {
            d.part1();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    void example() {
        System.out.println("part 1, first example");

        var s = """
                0123
                1234
                8765
                9876""";

        var hm = parseInput(s);

        // all trails that go from a '0' position to any '9' position
        List<Trail> found = findAllTrails(hm);

        // unique trails by (start, end) pairs
        Set<Pair> hikingTrails = new HashSet<>();
        for (Trail t : found) {
            hikingTrails.add(new Pair(t.getFirst(), t.getLast()));
        }
        System.out.println("hikingTrails: " + hikingTrails);
        assert hikingTrails.size() == 1;
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

    /**
     * Returns coords in heightMap with height 0
     *
     * @param heightMap
     */
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

    /**
     * Returns all paths from a start coord to on with height 9
     * Note: There may be several ways to get to the 9 coord from 0
     *
     * @param hm
     */
    List<Trail> findAllTrails(HeightMap hm) {
        var found = new ArrayList<Trail>();

        List<Trail> seen = findTrailHeads(hm);
        if (debug) {
            System.out.println(seen);
        }

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

    static final boolean debug = false;

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

    /**
     * Given a coord and a heightMap, find neighbour coords that are
     * 1 degree higher
     *
     * @param fromPos
     * @param hm
     */
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

    record Pair(Point a, Point b) {
    }

    void example2() {
        var s = """
                89010123
                78121874
                87430965
                96549874
                45678903
                32019012
                01329801
                10456732""";

        var hm = parseInput(s);

        // all trails that go from a '0' position to any '9' position
        List<Trail> found = findAllTrails(hm);

        // unique trails by (start, end) pairs
        Set<Pair> hikingTrails = new HashSet<>();
        for (Trail t : found) {
            hikingTrails.add(new Pair(t.getFirst(), t.getLast()));
        }
        System.out.println("hikingTrails: " + hikingTrails);
        assert hikingTrails.size() == 36;
    }

    void part1() throws IOException {
        var s = Files.readString(Path.of("inputs/day10.txt"));

        var hm = parseInput(s);

        // all trails that go from a '0' position to any '9' position
        List<Trail> found = findAllTrails(hm);

        // unique trails by (start, end) pairs
        Set<Pair> hikingTrails = new HashSet<>();
        for (Trail t : found) {
            hikingTrails.add(new Pair(t.getFirst(), t.getLast()));
        }
        System.out.println("hikingTrails: " + hikingTrails);
        assert hikingTrails.size() == 459;
    }
}
