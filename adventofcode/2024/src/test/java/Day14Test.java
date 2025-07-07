import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.assertEquals;

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

    record Robot(Pos pos, Mov mov) {
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
    // pos=0,4 v=3,-3
    // pos=6,3 v=-1,-3
    // pos=10,3 v=-1,2
    // ...
    List<Robot> parseInput(String s) {
        return Arrays.stream(s.split("\n"))
                .map(this::parseRobot)
                .toList();
    }

    // eg Pos and mov for a single robot separated by a space
    // pos=2,4 v=2,-3
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

    @Test
    void sample() {
        var robots = parseInput(sampleInput());

        robots = parseInput(sampleInput())
                .stream()
                .map(r -> moveRobot(r, 100, 11, 7))
                .toList();

        System.out.println(safetyFactor(robots, 11, 7));
    }

    Robot moveRobot(Robot r, int steps, int maxX, int maxY) {
        var newX = (r.pos.x + r.mov.u * steps) % maxX;
        if (newX < 0) {
            newX = maxX + newX;
        }

        var newY = (r.pos.y + r.mov.v * steps) % maxY;
        if (newY < 0) {
            newY = maxY + newY;
        }

        return new Robot(new Pos(newX, newY), r.mov);
    }

    /*
     * let's see if we can predict what tile a robot will land in
     * after wrapping around from one side of the floor map to the
     * other (multiple times)
     */

    @Test
    void mapWrapping() {
        // x, moving right

        // .x. -> ..x
        // given a 1-d map 3 tiles wide, and 1 tile deep, and a robot at 1, 0
        var r = new Robot(new Pos(1, 0), new Mov(1, 0));
        // when robot moves 1 tile right
        r = moveRobot(r, 1, 3, 1);
        // then it will be in tile 2, 0
        assertEquals(new Pos(2, 0), r.pos);

        // .x. -> ..x -> x..
        // given a 1-d map 3 tiles wide, and 1 tile deep, and a robot at 1, 0
        r = new Robot(new Pos(1, 0), new Mov(2, 0));
        // when robot moves 2 tiles right
        r = moveRobot(r, 1, 3, 1);
        // then it will be in tile 0, 0
        assertEquals(new Pos(0, 0), r.pos);

        // x, moving left

        // .x. -> x..
        // given a 1-d map 3 tiles wide, and 1 tile deep, and a robot at 1, 0
        r = new Robot(new Pos(1, 0), new Mov(-1, 0));
        // when robot moves 1 tile left
        r = moveRobot(r, 1, 3, 1);
        // then it will be in tile 0, 0
        assertEquals(new Pos(0, 0), r.pos);

        // .x. -> x.. -> ..x
        // given a 1-d map 3 tiles wide, and 1 tile deep, and a robot at 1, 0
        r = new Robot(new Pos(1, 0), new Mov(-2, 0));
        // when robot moves 2 tiles left
        r = moveRobot(r, 1, 3, 1);
        // then it will be in tile 2, 0
        assertEquals(new Pos(2, 0), r.pos);
    }

    // Returns the safety factor for the given map and robots
    int safetyFactor(List<Robot> robots, int maxX, int maxY) {

        // the grid length and width are both odd
        assert maxX % 2 == 1;
        assert maxY % 2 == 1;

        /*
         * i have to sort the robots into quadrants and then the
         * safety factor is the number of robots in each quadrant
         * multiplied together
         */

        int midX = maxX / 2;
        int midY = maxY / 2;

        int[] quadrants = new int[4];
        robots.forEach(r -> {
            if (r.pos.x < midX) {
                if (r.pos.y < midY) {
                    quadrants[0]++;
                } else if (r.pos.y > midY) {
                    quadrants[1]++;
                }
            }

            if (r.pos.x > midX) {
                if (r.pos.y < midY) {
                    quadrants[2]++;
                } else if (r.pos.y > midY) {
                    quadrants[3]++;
                }
            }
        });

        System.out.println(Arrays.toString(quadrants));

        return Arrays.stream(quadrants).reduce(1, (a, b) -> a * b);
    }
}
