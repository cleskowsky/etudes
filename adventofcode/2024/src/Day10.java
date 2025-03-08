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
        assert found.size() == 1;
        System.out.println(found);
    }

    record Trail(List<Point> path) {
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
            var path = new ArrayList<Point>();
            path.add(entryPoint);
            seen.add(new Trail(path));
        }
        return seen;
    }

    List<Trail> findLongTrails(HeightMap hm) {
        var found = new ArrayList<Trail>();

        List<Trail> seen = findTrailHeads(hm);
        assert seen.size() == 1;
        System.out.println(seen);

        while (!seen.isEmpty()) {
            var trail = seen.removeFirst();
            Point curr = trail.path.getLast();
            List<Point> next = moveDirections(curr);
        }

        return found;
    }

    class HeightMap extends HashMap<Point, Integer> {
    }
}
