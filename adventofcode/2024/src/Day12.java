import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

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
        Point up() {
            return new Point(x, y - 1);
        }

        Point left() {
            return new Point(x - 1, y);
        }

        Point add(Point p) {
            return new Point(x + p.x, y + p.y);
        }
    }

    /**
     * Returns farm regions as list
     */
    List<Region> regions(Farm farm) {
        var regions = new ArrayList<Region>();

        Map<Point, Region> plotCache = new HashMap<>();

        var gridX = Math.sqrt(farm.plots().size());
        for (int i = 0; i < gridX; i++) {
            for (int j = 0; j < gridX; j++) {
                var p = new Point(j, i);
                var plant = farm.plots().get(p);

                // find existing region for this plot
                Region r = null;
                for (var d : directions) {
                    var neighbour = p.add(d);
                    if (plant == farm.plots().get(neighbour)) {
                        r = plotCache.get(p.add(d));
                    }
                }

                // create region if none found
                if (r == null) {
                    var plots = new ArrayList<Point>();
                    plots.add(p);
                    r = new Region(plant.toString(), plots, farm);
                    regions.add(r);
                }

                // add p to plotCache
                plotCache.put(p, r);

                // add neighbours to my region
                for (var d : directions) {
                    var neighbour = p.add(d);
                    if (plant == farm.plots().get(neighbour)) {
                        r.plots().add(neighbour);
                        plotCache.put(neighbour, r);
                    }
                }
            }
        }

        return regions;
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
