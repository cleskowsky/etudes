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

    record ParseResult(Day8.SignalMap signals, int limitX, int limitY) {
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
    void pairs() {
        var s1 = """
                ..........
                ...#......
                #.........
                ....a...a.
                ..........
                ....a...a.
                ..#.......
                ......A...
                ..........
                ..........""";
        assertEquals(6, Day8.pairs(parse(s1).signals().get('a')).size());
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

        var signalMap = parse(s);

        assertEquals(2, signalMap.count());
        assertEquals(new Point(4, 3), signalMap.get('a').get(0));
        assertEquals(new Point(8, 4), signalMap.get('a').get(1));
        assertEquals(new Point(5, 5), signalMap.get('a').get(2));
        assertEquals(new Point(6, 7), signalMap.get('A').get(0));

        assertEquals(3, Day8.pairs(signalMap.get('a')).size());

        Day8 d8 = new Day8();
        signalMap.signals().forEach((sig, locs) -> {
            for (Day8.Pair p : Day8.pairs(locs)) {
                d8.findAntinodesFor(p);
            }
        });
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