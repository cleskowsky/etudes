import java.util.ArrayList;
import java.util.List;

public class Day11 {
    public static void main(String[] args) {
        System.out.println("day 11");

        var d = new Day11();
        d.example();
    }

    void example() {
        System.out.println("example");
        var stones = List.of(125l, 17l);
        System.out.println(blink(stones));
    }

    /**
     * Returns the stones after a blink
     * 
     * @param s
     */
    List<Long> blink(List<Long> stones) {
        var result = new ArrayList<Long>();

        for (var s : stones) {
            // 0 becomes 1
            if (s == 0) {
                result.add(1l);
                continue;
            }
        }

        // even number of digits - splits into 2
        // default - x2024
        return result;
    }
}
