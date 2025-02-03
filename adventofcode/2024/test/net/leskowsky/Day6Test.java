package net.leskowsky;

import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static net.leskowsky.Day6.Guard;
import static net.leskowsky.Day6.Point;
import static net.leskowsky.Day6.Lab;
import static org.junit.jupiter.api.Assertions.*;

class Day6Test {

    // parse lab floor
    @Test
    void canParseInput() {
        var s = """
                ..
                .#
                """;
        var lab = InputParser.parse(s);
        assertEquals(4, lab.floor().keySet().size());
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
        var lab = InputParser.parse(s);
        var g = new Guard(lab);
        assertEquals(new Point(0, 1), g.getPos());
        assertEquals(new Point(0, -1), g.getDir());

        // When the guard takes a step
        g.step();

        // His has moved up and his heading hasn't changed
        assertEquals(new Point(0, 0), g.getPos());
        assertEquals(new Point(0, -1), g.getDir());
    }

    // turns right to avoid obstacles
    @Test
    void guardTurnsRightToAvoidObstacle() {
        // Given an obstacle in front of the guard
        var s = """
                #.
                ^.""";
        var lab = InputParser.parse(s);
        var g = new Guard(lab);

        // When the guard steps
        g.step();

        // He turns right then moves
        assertEquals(new Point(1, 1), g.getPos());
        assertEquals(new Point(1, 0), g.getDir());
    }

    // walks outside mapped area
    // remembers path taken

    class InputParser {
        static Lab parse(String s) {
            var floor = new HashMap<Point, Boolean>();

            var rows = s.split("\n");
            for (int i = 0; i < rows.length; i++) {
                var row = rows[i].toCharArray();
                for (int j = 0; j < row.length; j++) {
                    if (row[j] == '#') {
                        floor.put(new Point(j, i), true);
                    } else {
                        floor.put(new Point(j, i), false);
                    }
                }
            }

            return new Lab(floor);
        }
    }
}