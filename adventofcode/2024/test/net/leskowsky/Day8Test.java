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
        assertEquals(1, parse(s).count());
        assertEquals(2, parse(s).signals().get('a').size());
        assertEquals(2, parse(s).limitX());
        assertEquals(2, parse(s).limitY());
    }

    private Day8.SignalMap parse(String s) {
        // find signals
        // find limitX, limitY
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

        return new Day8.SignalMap(signals, lines[0].length(), lines.length);
    }

    @Test
    void findAntinodes() {
        var s = """
                ..........
                ..........
                ..........
                ....a.....
                ..........
                .....a....
                ..........
                ..........
                ..........
                ..........""";
        var signals = parse(s);
        assertEquals(2, Day8.findAntinodes(signals).size());

        s = """
                ..........
                ..........
                ....a.....
                ......a...
                ....a.....
                ..........
                ..........
                ..........""";
        signals = parse(s);
        assertEquals(6, Day8.findAntinodes(signals).size());
    }

    @Test
    void sample() {
        var s = """
                ..........
                ...#......
                #.........
                ....a.....
                ........a.
                .....a....
                ..#.......
                ......A...
                ..........
                ..........""";

        var signals = parse(s);

        assertEquals(2, signals.count());
        assertEquals(new Point(4, 3), signals.get('a').get(0));
        assertEquals(new Point(8, 4), signals.get('a').get(1));
        assertEquals(new Point(5, 5), signals.get('a').get(2));
        assertEquals(new Point(6, 7), signals.get('A').get(0));
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