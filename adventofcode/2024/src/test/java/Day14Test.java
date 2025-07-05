import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class Day14Test {

    /*
     * I can maintain a list of robots and during an event loop
     * advance their position by their rate of movement since they're
     * only taking 100 steps in the first part but also this strategy
     * will be made unworkable due to an order of magnitude change in
     * input in the next part I bet ...
     *
     * I'll start with the straightforward implementation since
     * that will help get a basic data model sorted at least for part
     * a
     *
     */

    record Pos(int x, int y) {
    }

    record Mov(int u, int v) {
    }

    record Robot(Pos p, Mov m) {
    }

    String sampleInput() {
        return """
                p=0,4 v=3,-3
                p=6,3 v=-1,-3
                p=10,3 v=-1,2
                p=2,0 v=2,-1
                p=0,0 v=1,3
                p=3,0 v=-2,-2
                p=7,6 v=-1,-3
                p=3,0 v=-1,-2
                p=9,3 v=2,3
                p=7,3 v=-1,2
                p=2,4 v=2,-3
                p=9,5 v=-3,-3""";
    }

    @Test
    void canParseInput() {
        var robots = parseInput(sampleInput());
        System.out.println(robots);
        System.out.println(robots.size());
    }

    // eg 1 robot per line
    // p=0,4 v=3,-3
    // p=6,3 v=-1,-3
    // p=10,3 v=-1,2
    // ...
    List<Robot> parseInput(String s) {
        return Arrays.stream(s.split("\n"))
                .map(this::parseRobot)
                .toList();
    }

    // eg Pos and mov for a single robot separated by a space
    // p=2,4 v=2,-3
    Robot parseRobot(String s) {
        var pat = Pattern.compile("p=(-?\\d+),(-?\\d+)\\sv=(-?\\d+),(-?\\d+)");

        // parse pos, mov
        var m = pat.matcher(s);
        if (m.matches()) {
            return new Robot(
                    new Pos(Integer.parseInt(m.group(1)), Integer.parseInt(m.group(2))),
                    new Mov(Integer.parseInt(m.group(3)), Integer.parseInt(m.group(4)))
            );
        } else {
            throw new RuntimeException("Bad robot: " + s);
        }
    }
}
