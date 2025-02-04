package net.leskowsky;

import lombok.Data;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static net.leskowsky.Day6.Direction;
import static net.leskowsky.Day6.Guard;
import static net.leskowsky.Day6.Lab;
import static net.leskowsky.Day6.Point;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

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
    void remembersPath() {
        fail();
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
    }
}