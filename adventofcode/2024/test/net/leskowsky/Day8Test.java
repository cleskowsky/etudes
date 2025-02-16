package net.leskowsky;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class Day8Test {

    @Test
    void parseInput() {
        String s = """
                .a
                a.""";
        assertEquals(1, parseMap(s).signals().count());
        assertEquals(2, parseMap(s).signals().get('a').size());
        assertEquals(2, parseMap(s).maxX());
        assertEquals(2, parseMap(s).maxY());
    }

    record ParseResult(Day8.SignalMap signals, int maxX, int maxY) {
    }

    private ParseResult parseMap(String s) {
        // find signals
        // find maxX, maxY
        var signals = new HashMap<Character, List<Point>>();

        var lines = s.split("\n");
        for (int j = 0; j < lines.length; j++) {
            var line = lines[j];
            for (int i = 0; i < line.length(); i++) {
                char c = line.charAt(i);
                if (c == '.' || c == '#') {
                    continue;
                }
                var x = signals.getOrDefault(c, new ArrayList<>());
                x.add(new Point(i, j));
                signals.put(c, x);
            }
        }

        return new ParseResult(new Day8.SignalMap(signals), lines[0].length(), lines.length);
    }

    @Test
    void sample() {
        var s = """
                ..........
                ...#......
                ..........
                ....a.....
                ..........
                .....a....
                ..........
                ......#...
                ..........
                ..........""";

        var x = parseMap(s);
        assertEquals(new Point(4,3), x.signals().get('a').get(0));
        assertEquals(new Point(5,5), x.signals().get('a').get(1));

        var antinodes = new ArrayList<Point>();
    }

    @Test
    void part1() {
        // find signals
        // foreach signal
        //   find pairs
        //   foreach pair
        //     find antinodes
        // count antinodes
        fail();
    }
}