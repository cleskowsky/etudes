package net.leskowsky;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static net.leskowsky.Day8.pairs;
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
    void getPairs() {
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
        assertEquals(6, pairs(parse(s1).signals().get('a')).size());
    }

    @Test
    void addAntinodes() {
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

        var d8 = new Day8();
        d8.findAntinodes(signals);
        System.out.println(d8.antinodes);

        assertEquals(2, d8.antinodes.size());

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

        d8 = new Day8();
        d8.findAntinodes(signals);
        System.out.println(d8.antinodes);

        assertEquals(6, d8.antinodes.size());
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

        assertEquals(3, pairs(signalMap.get('a')).size());

        Day8 d8 = new Day8();
        signalMap.signals().forEach((sig, locs) -> {
            for (Day8.Pair p : pairs(locs)) {
                d8.addAntinodes(p.p1(), p.p2(), signalMap);
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