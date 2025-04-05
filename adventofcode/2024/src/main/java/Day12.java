import java.util.HashMap;
import java.util.List;

public class Day12 {
    public static void main(String[] args) {
        System.out.println(1);

        var d = new Day12();
        d.example();
    }

    public void example() {
        System.out.println("example");

        var s = """
                AAAA
                BBCD
                BBCC
                EEEC""";
        System.out.println(s);

        Farm farm = parseInput(s);

        var total = farm.getRegions().stream()
            .map(Day12::fencePrice)
            .reduce(0, Integer::sum);
    }

    static class Farm extends HashMap<Point, Character> {
        public List<Region> getRegions() {
            return null;
        }
    }

    record Region(List<Character> plots) {
    }

    record Point(int x, int y) {
    }

    static int fencePrice(Region region) {
        return 0;
    }

    Farm parseInput(String s) {
        return null;
    }
}
