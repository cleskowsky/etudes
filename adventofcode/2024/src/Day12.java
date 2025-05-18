import java.util.*;

public class Day12 {
    static Farm parseInput(String s) {
        var plots = new HashMap<Point, Character>();

        var rows = s.split("\n");
        for (int i = 0; i < rows.length; i++) {
            for (int j = 0; j < rows[i].length(); j++) {
                plots.put(new Point(j, i), rows[i].charAt(j));
            }
        }

        return new Farm(plots);
    }

    record Farm(Map<Point, Character> plots) {
    }

    record Point(int x, int y) {
        Point add(Point p) {
            return new Point(x + p.x, y + p.y);
        }
    }

    /**
     * Returns a list of farm regions
     */
    List<Region> regions(Farm farm) {
        var regions = new ArrayList<Region>();

        // input is always a square
        var gridX = Math.sqrt(farm.plots().size());

        // remember plots seen
        var seen = new ArrayList<Point>();

        // visit every plot row by row
        for (int i = 0; i < gridX; i++) {
            for (int j = 0; j < gridX; j++) {

                // if a plot hasn't been seen before, find the containing region
                if (seen.contains(new Point(j, i))) {
                    continue;
                }
                regions.add(findRegion(new Point(j, i), farm, seen));
            }
        }

        return regions;
    }

    /**
     * Returns a list of plots in a region
     */
    Region findRegion(Point p, Farm farm, List<Point> seen) {
        var found = new ArrayList<Point>();
        found.add(p);

        Region r = new Region(farm.plots().get(p).toString(), new ArrayList<>(), farm);

        while (!found.isEmpty()) {
            var x = found.removeFirst();

            if (seen.contains(x)) {
                continue;
            }

            for (var d : directions) {
                var neighbour = x.add(d);
                var adjacentPlant = farm.plots().get(neighbour);
                if (adjacentPlant == null || adjacentPlant != r.name().charAt(0)) {
                    // non-existent or non-region
                    continue;
                }
                if (seen.contains(neighbour)) {
                    // visited plot
                    continue;
                }
                found.add(neighbour);
            }

            r.plots().add(x);
            seen.add(x);
        }

        return r;
    }

    record Region(String name, List<Point> plots, Farm farm) {
    }

    List<Point> directions = List.of(
            new Point(0, -1),
            new Point(-1, 0),
            new Point(0, 1),
            new Point(1, 0)
    );

    int perimeter(Region r) {
        var sides = 0;
        for (var p : r.plots()) {
            var plotPlant = r.farm().plots().get(p);
            for (var d : directions) {
                var neighbourPlotPlant = r.farm().plots().get(p.add(d));
                if (plotPlant.equals(neighbourPlotPlant)) {
                    continue;
                }
                sides++;
            }
        }
        return sides;
    }

    int fencePrice(Region r) {
        return perimeter(r) * r.plots().size();
    }
}
