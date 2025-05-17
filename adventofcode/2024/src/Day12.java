import java.util.ArrayList;
import java.util.HashMap;
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
    List<Region> regions(Farm f) {
        var result = new ArrayList<Region>();

        Map<Point, Region> plotsToRegionsMap = new HashMap<>();

        var gridX = Math.sqrt(f.plots().size());
        for (int i = 0; i < gridX; i++) {
            for (int j = 0; j < gridX; j++) {
                var p = new Point(j, i);

                // look up for region
                var myPlant = f.plots().get(p);
                var adjPlant = f.plots().get(p.up());
                if (adjPlant == myPlant) {
                    var region = plotsToRegionsMap.get(p.up());
                    region.plots().add(p);
                    plotsToRegionsMap.put(p, region);
                    continue;
                }

                // look left for region
                adjPlant = f.plots().get(p.left());
                if (adjPlant == myPlant) {
                    var region = plotsToRegionsMap.get(p.left());
                    region.plots().add(p);
                    plotsToRegionsMap.put(p, region);
                    continue;
                }

                // couldn't find region, create one
                var plots = new ArrayList<Point>();
                plots.add(p);
                var region = new Region(myPlant.toString(), plots, f);
                plotsToRegionsMap.put(p, region);
                result.add(region);
            }
        }

        return result;
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
            for ( var d : directions) {
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
