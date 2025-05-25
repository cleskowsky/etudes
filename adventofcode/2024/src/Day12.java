import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day12 {

    public static final boolean DEBUG = false;

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

        Point add(Heading h) {
            return new Point(x + h.x, y + h.y);
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
                // seen this plot already
                continue;
            }

            for (var h : Heading.values()) {
                var neighbour = x.add(h);
                var adjacentPlant = farm.plots().get(neighbour);
                if (adjacentPlant == null || adjacentPlant != r.name().charAt(0)) {
                    // non-existent or non-region
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

    enum Heading {
        TOP(0, -1),
        RIGHT(1, 0),
        BOTTOM(0, -1),
        LEFT(-1, 0);

        int x;
        int y;

        Heading(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    int perimeter(Region r) {
        var sides = 0;

        // Split fencing into vertical, horizontal and then
        // merge adjacent fences
        var north = new ArrayList<Point>();
        var south = new ArrayList<Point>();
        var east = new ArrayList<Point>();
        var west = new ArrayList<Point>();

        for (var p : r.plots()) {
            var plotPlant = r.farm().plots().get(p);
            for (var h : Heading.values()) {
                var neighbour = p.add(h);

                var adjPlant = r.farm().plots().get(neighbour);
                if (plotPlant.equals(adjPlant)) {
                    continue;
                }

                sides++;

                // add plot to index

                if (h.equals(Heading.TOP)) {
                    // top fence
                    north.add(p);
                } else if (h.equals(Heading.BOTTOM)) {
                    // bottom fence
                    south.add(p);
                } else if (h.equals(Heading.LEFT)) {
                    // left fence
                    west.add(p);
                } else if (h.equals(Heading.RIGHT)) {
                    // right fence
                    east.add(p);
                }
            }
        }

        System.out.println("Calculating perimeter for: " + r);

        return sides;
    }

    int fencePrice(Region r) {
        return perimeter(r) * r.plots().size();
    }
}
