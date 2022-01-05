package a;

import a.day18.Day18;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Day18Test {

    private Day18 d = new Day18();

    @Test
    public void eval() {
        assertEquals(2, d.eval("1 + 1"));
        assertEquals(6, d.eval("2 * 3"));
        assertEquals(3, d.eval("1 + (1 + 1)"));
        assertEquals(9, d.eval("1 + (2 * 3) + (1 + 1)"));
        assertEquals(6, d.eval("1 + 2 + 3"));
        assertEquals(10, d.eval("1 + 2 + 3 + 4"));
        assertEquals(7, d.eval("(2 * 3) + 1"));
        assertEquals(8, d.eval("1 + (2 * 3) + 1"));
        assertEquals(8, d.eval("1 + (2 * (2 + 1)) + 1"));
    }

    @Test
    public void puzzleSampleInput() {
        assertEquals(71, d.eval("1 + 2 * 3 + 4 * 5 + 6"));
        assertEquals(26, d.eval("2 * 3 + (4 * 5)"));
        assertEquals(437, d.eval("5 + (8 * 3 + 9 + 3 * 4 * 3)"));
        assertEquals(51, d.eval("1 + (2 * 3) + (4 * (5 + 6))"));
        assertEquals(12240, d.eval("5 * 9 * (7 * 3 * 3 + 9 * 3 + (8 + 6 * 4))"));
        assertEquals(13632, d.eval("((2 + 4 * 9) * (6 + 9 * 8 + 6) + 6) + 2 + 4 * 2"));
    }
}