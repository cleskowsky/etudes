package aoc;

import aoc.Day8.Forest;
import org.junit.jupiter.api.Test;

import static aoc.Day8.visibleFromDir;
import static org.junit.jupiter.api.Assertions.assertTrue;

class Day8Test {

    @Test
    void treeShouldBeVisible() {
        // Given a tree t, a forest and a heading
        var t = new Point(1, 1);

        var f = new Forest();
        f.put(t, 5);
        f.put(new Point(2, 1), 3);
        f.put(new Point(3, 1), 2);

        // When all other trees in a dir are shorter than t
        // Then t is visible
        assertTrue(visibleFromDir(t, f, Heading.E));
    }
}
