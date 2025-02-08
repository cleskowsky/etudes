package net.leskowsky;

import lombok.Data;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;

import static net.leskowsky.Day6.Direction;
import static net.leskowsky.Day6.Guard;
import static net.leskowsky.Day6.Lab;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class Day6Test {

    // parse lab floor
    @Test
    void canParseInput() {
        var s = """
                ..
                .#
                """;
        var result = InputParser.parse(s);
        var lab = result.getLab();
        assertEquals(4, lab.floor().size());
        assertFalse(lab.isBlocked(0, 0));
        assertTrue(lab.isBlocked(1, 1));
    }

    // walks forward
    @Test
    void guardStep() {
        // Given a lab and a guard
        var s = """
                .
                ^
                """;
        var result = InputParser.parse(s);
        var g = result.getGuard();
        assertEquals(new Point(0, 1), g.getPos());
        assertEquals(Direction.UP, g.getDir());

        // When the guard takes a step
        g.step(result.getLab());

        // His has moved up and his heading hasn't changed
        assertEquals(new Point(0, 0), g.getPos());
        assertEquals(Direction.UP, g.getDir());
    }

    // turns right to avoid obstacles
    @Test
    void guardTurnsToAvoidObstacle() {
        // Given an obstacle in front of the guard
        var s = """
                #.
                ^.""";
        var result = InputParser.parse(s);
        var g = result.getGuard();

        // When the guard steps
        g.step(result.getLab());

        // He turns right then moves
        assertEquals(new Point(1, 1), g.getPos());
        assertEquals(Direction.RIGHT, g.getDir());
    }

    // walks outside mapped area
    @Test
    void guardLeavesFloor() {
        // Given a guard at the floor's edge
        var s = """
                ^.
                ..""";
        var result = InputParser.parse(s);
        var g = result.getGuard();

        // When the guard takes a step outside the floor
        g.step(result.getLab());

        // He doesn't move or turn (Simulator is finished)
        assertEquals(new Point(0, 0), g.getPos());
        assertEquals(Direction.UP, g.getDir());
    }

    // remembers path taken
    @Test
    void stepLog() {
        // Given a guard and a floor
        String s = """
                .#
                ^.""";
        var result = InputParser.parse(s);
        var g = result.getGuard();

        // When the guard takes a step
        while (true) {
            g.step(result.getLab());
            if (g.isLeftLab()) {
                continue;
            }
            break;
        }

        // Then a record of squares occupied is kept
        assertEquals(2, g.getSeen().size());
    }

    @Test
    void sample() throws IOException {
        var result = InputParser.parseFile("inputs/day6_sample.txt");

        var g = result.getGuard();
        while (true) {
            g.step(result.getLab());
            if (g.isLeftLab()) {
                continue;
            }
            break;
        }

        System.out.println(g.getSeen().size());
    }

    @Test
    void part1() throws IOException {
        var result = InputParser.parseFile("inputs/day6.txt");

        var g = result.getGuard();
        while (true) {
            g.step(result.getLab());
            if (g.isLeftLab()) {
                break;
            }
        }

        assertEquals(5404, g.getSeen().size());
    }

    @Test
    void part2() throws IOException {
        var result = InputParser.parseFile("inputs/day6.txt");

        // for each open square
        var lab = result.getLab();
        for (Point p : lab.floor().keySet()) {
            if (lab.isBlocked(p.x(), p.y())) {
                // skip already obstructed square
                continue;
            }

            // add obstruction
            lab.floor().put(p, true);

            // simulate guard movement
            var g = result.getGuard();
            while (true) {
                g.step(result.getLab());

                // if we exit map, stop
                // if we get a seenFacing we've seen before we have a loop
                if (g.isLeftLab()) {
                    continue;
//                } else if (g.) {

                }
                break;
            }
        }



//        System.out.println(g.getSeen().size());
    }

    static class InputParser {
        @Data
        static class ParseResult {
            Lab lab;
            Guard guard;
        }

        static ParseResult parse(String s) {
            var result = new ParseResult();

            var floor = new HashMap<Point, Boolean>();
            var lab = new Lab(floor);
            result.setLab(lab);

            var rows = s.split("\n");
            for (int i = 0; i < rows.length; i++) {
                var row = rows[i].toCharArray();
                for (int j = 0; j < row.length; j++) {
                    if (row[j] == '#') {
                        floor.put(new Point(j, i), true);
                    } else if (row[j] == '^') {
                        result.setGuard(new Guard(new Point(j, i), Direction.UP));
                        // guard start square is not blocked
                        floor.put(new Point(j, i), false);
                    } else {
                        floor.put(new Point(j, i), false);
                    }
                }
            }

            return result;
        }

        static ParseResult parseFile(String s) throws IOException {
            return parse(
                    Files.readString(Path.of(s))
            );
        }
    }
}