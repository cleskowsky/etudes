import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day11 {
    public static void main(String[] args) {
        System.out.println("day 11");

        var d = new Day11();

//        d.example();
//        d.part1();
        var start = System.currentTimeMillis();
        d.part2();
        System.out.println("example took " + (System.currentTimeMillis() - start) + "ms");
    }

    void example() {
        System.out.println("example");
        var stones = List.of(125L, 17L);
        System.out.println(blink(stones, 25));
        assert blink(stones, 25) == 55312;
    }

    // Return stones after n blinks
    long blink(List<Long> stones, int rounds) {
        if (debug) {
            System.out.println("Blinking " + rounds + " times");
            System.out.println("stones=" + stones);
        }

        var val = 0L;

        if (rounds == 0) {
            // return number of stones in final round
            val += stones.size();
        } else {
            // blink first
            // use cached stone value for rounds if available
//            Long stoneVal = null;
            var stoneVal = blinkCache.get(new BlinkCacheKey(stones.getFirst(), rounds - 1));
            if (stoneVal == null) {
                var x = blink(stones.getFirst());
                stoneVal = blink(x, rounds - 1);
            }
            val += stoneVal;

            // cache value for stone after n rounds
            blinkCache.put(
                    new BlinkCacheKey(stones.getFirst(), rounds - 1),
                    stoneVal
            );

            // blink rest
            if (stones.size() > 1) {
                val += blink(stones.subList(1, stones.size()), rounds);
            }
        }

        return val;
    }

    Map<BlinkCacheKey, Long> blinkCache = new HashMap<>();

    record BlinkCacheKey(long stone, int round) {
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

    void part1() {
        System.out.println("part 1");
        var stones = List.of(5L, 89749L, 6061L, 43L, 867L, 1965860L, 0L, 206250L);
        assert blink(stones, 25) == 203609;
    }

    void part2() {
        System.out.println("part 2");

        var stones = List.of(5L, 89749L, 6061L, 43L, 867L, 1965860L, 0L, 206250L);
        long val = 0;

        var n = 75;
        for (var s : stones) {
            System.out.printf("Blinking %d %d times\n", s, n);
            val += blink(List.of(s), n);
        }

        System.out.println(val);
//        assert blink(stones, 25) == 203609;
    }
}
