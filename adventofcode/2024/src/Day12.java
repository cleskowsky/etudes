import java.util.HashMap;
import java.util.Map;

public class Day12 {
    public static void main(String[] args) {
        System.out.println(1);
    }

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
}
