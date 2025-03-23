import java.util.ArrayList;
import java.util.List;

public class Day11 {
    public static void main(String[] args) {
        System.out.println("day 11");

        var d = new Day11();
        d.example();
//        d.part1();
    }

    void example() {
        System.out.println("example");

        var stones = List.of(125L, 17L);
        System.out.println(blink(stones, 6));
    }

    // Return stones after n blinks
    int blink(List<Long> stones, int rounds) {

        if (debug) {
            System.out.println("Blinking " + rounds + " times");
            System.out.println("stones=" + stones);
        }

        var val = 0;

        if (rounds == 0) {
            // return number of stones in final round
            val += stones.size();
        } else {
            // blink first
            var x = blink(stones.getFirst());
            val += blink(x, rounds - 1);

            // blink rest
            if (stones.size() > 1) {
                val += blink(stones.subList(1, stones.size()), rounds);
            }
        }

        return val;
    }

    private static List<Long> blink(Long s) {
        var result = new ArrayList<Long>();

        if (s == 0) {
            // 0 becomes 1
            if (debug) {
                System.out.println("0 becomes 1");
            }
            result.add(1L);
        } else if (s.toString().length() % 2 == 0) {
            // even number of digits - splits into 2
            if (debug) {
                System.out.println("even number of digits - splits into 2");
            }
            var str = s.toString();
            var half = str.length() / 2;
            var first = Long.parseLong(str.substring(0, half));
            var second = Long.parseLong(str.substring(half));
            result.add(first);
            result.add(second);
        } else {
            // default - *2024
            if (debug) {
                System.out.println("default - *2024");
            }
            result.add(s * 2024);
        }

        return result;
    }

    static boolean debug = false;

//    void part1() {
//        System.out.println("part 1");
//
//        var stones = List.of(5l, 89749l, 6061l, 43l, 867l, 1965860l, 0l, 206250l);
//        System.out.println(blink(stones, 25));
//    }
}
