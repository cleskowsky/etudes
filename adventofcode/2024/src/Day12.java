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
    }

    /**
     * Returns a list of regions for farm
     */
    List<Region> regions(Farm farm) {
        return List.of(
                new Region("A", List.of(new Point(0, 0)))
        );
    }

    record Region(String name, List<Point> points) {
    }
}
