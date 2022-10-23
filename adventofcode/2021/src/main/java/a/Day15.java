package a;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class Day15 {

    public static void main(String[] args) throws Exception {
        var x = System.currentTimeMillis();
        System.out.println(calcMinRisk(
                new Point(0, 0),
                new Point(99, 99),
                input(Files.readString(Path.of("in/15.txt"))),
                new HashMap<Point, Integer>())
        );
        System.out.println(System.currentTimeMillis() - x);

        // visit adjacent squares (don't revisit any)
        // stop when I reach the end square (bottom right)
        // calculate the total risk of path
        // return min risk of paths from current (plus current risk)
    }

    public static int calcMinRisk(Point start, Point finish, Map<Point, Integer> riskTable, Map<Point, Integer> minRiskFromPointCache) {

        // Ensure start is in the grid
        if (start.x() > finish.x() || start.y() > finish.y()) {
            return Integer.MAX_VALUE;
        }

        if (start.equals(finish)) {
            return riskTable.get(start);
        } else {
            // find min total risk of possible paths from start

            // Get minRisk for neighbours right and down
            var p1 = new Point(start.x() + 1, start.y());
            var p2 = new Point(start.x(), start.y() + 1);

            var min1 = minRiskFromPointCache.get(p1);
            if (min1 == null) {
                min1 = calcMinRisk(p1, finish, riskTable, minRiskFromPointCache);
            }

            var min2 = minRiskFromPointCache.get(p2);
            if (min2 == null) {
                min2 = calcMinRisk(p2, finish, riskTable, minRiskFromPointCache);
            }

            // add my own risk to the result, cache it and return
            var x = riskTable.get(start);
            if (start.x() == 0 && start.y() == 0) {
                x = 0;
            }
            minRiskFromPointCache.put(start, Math.min(min1, min2) + x);

            return minRiskFromPointCache.get(start);
        }
    }

    /**
     * Return a dict of coords to risk levels
     */
    static Map<Point, Integer> input(String s) {
        var grid = new HashMap<Point, Integer>();

        var split = s.split("\n");
        for (int i = 0; i < split.length; i++) {
            var row = split[i];
            for (int j = 0; j < row.length(); j++) {
                var risk = Character.getNumericValue(row.charAt(j));
                grid.put(new Point(j, i), risk);
            }
        }

        return grid;
    }

    /**
     * Returns a grid by tiles larger in both x, y directions
     */
    static Map<Point, Integer> expandGrid(Map<Point, Integer> g, int by) {
        var x = new HashMap<Point, Integer>();

        // g is always a square grid
        var side = (int) Math.sqrt(g.size());

        g.keySet().forEach((k -> {
            for (int i = 0; i < by; i++) {
                for (int j = 0; j < by; j++) {
                    // Add corresponding point p for k in each tile of expanded grid
                    var p = new Point(k.x() + j * side, k.y() + i * side);

                    if (g.get(p) == null) {
                        // Initial v for new tile point
                        var v = x.get(new Point(p.x() - side, p.y()));
                        if (v == null) {
                            // A left tile doesn't exist when j is 0
                            v = x.get(new Point(p.x(), p.y() - side));
                        }
                        x.put(p, v == 9 ? 1 : v + 1);
                    } else {
                        x.put(p, g.get(p));
                    }
                }
            }

        }));

        return x;
    }
}
