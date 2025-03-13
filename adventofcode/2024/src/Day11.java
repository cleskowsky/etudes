import java.util.ArrayList;
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

    /**
     * Returns the stones after a blink
     * 
     * @param s
     */
    int blink(List<Long> stones, int rounds) {

        var val = new ArrayList<Long>(stones);

        for (int i = 0; i < rounds; i++) {

            // a scratch list to store stones after each round
            var result = new ArrayList<Long>();

            for (var s : val) {
                // 0 becomes 1
                if (s == 0) {
                    result.add(1l);
                    continue;
                }

                // even number of digits - splits into 2
                if (s.toString().length() % 2 == 0) {
                    var str = s.toString();
                    var half = str.length() / 2;
                    var first = Long.parseLong(str.substring(0, half));
                    var second = Long.parseLong(str.substring(half));
                    result.add(first);
                    result.add(second);
                    continue;
                }

                // default - *2024
                result.add(s * 2024);
            }

            if (debug) {
                System.out.println(result);
            }

            val = result;
        }

        return val.size();
    }

    static boolean debug = false;

    void part1() {
        System.out.println("part 1");

        var stones = List.of(5l, 89749l, 6061l, 43l, 867l, 1965860l, 0l, 206250l);
        System.out.println(blink(stones, 25));
    }
}
