import java.util.List;

public class Day11 {
    public static void main(String[] args) {
        System.out.println("day 11");

        var d = new Day11();
        d.example();
        d.part1();
    }

    void example() {
        System.out.println("example");

        var stones = List.of(125l, 17l);
        System.out.println(blink(stones, 6));
    }

    // Return stones after n blinks
    int blink(List<Long> stones, int rounds) {
        return 0;
    }

    static boolean debug = false;

    void part1() {
        System.out.println("part 1");

        var stones = List.of(5l, 89749l, 6061l, 43l, 867l, 1965860l, 0l, 206250l);
        System.out.println(blink(stones, 25));
    }
}
