package main;

import org.junit.jupiter.api.Test;

import static main.Day12.Farm;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day12Test {

    @Test
    void parseInput() {
        var s = """
                AAAA
                BBCD
                BBCC
                EEEC""";
        Farm f = Day12.parseInput(s);
        assertEquals(16, f.getPlots().size());
        assertEquals('E', f.getPlot(2, 3));
        assertEquals('C', f.getPlot(3, 3));
    }
}
