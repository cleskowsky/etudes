import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

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

    @Test
    void canParseInput() {
        var s = """
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
        System.out.println(parseInput(s));

        var robots = parseInput(s);
        System.out.println(robots.size());
    }

    // eg 1 robot per line
    // p=0,4 v=3,-3
    // p=6,3 v=-1,-3
    // p=10,3 v=-1,2
    // ...
    List<Robot> parseInput(String s) {
        var val = Arrays.stream(s.split("\n"))
                .map(this::parseRobot)
                .toList();

        return val;
    }

    // eg p=2,4 v=2,-3
    Robot parseRobot(String s) {
        var split = s.split()
        return new Robot(new Pos(0, 0), new Mov(0, 0));
    }
}
