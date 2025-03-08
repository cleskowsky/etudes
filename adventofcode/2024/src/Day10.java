import net.leskowsky.Point;

import java.util.HashMap;
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

        var heightMap = parseInput(s);
        System.out.println(heightMap);
    }

    Map<Point, Integer> parseInput(String s) {
        Map<Point, Integer> result = new HashMap<>();

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
}
