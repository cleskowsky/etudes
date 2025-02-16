package net.leskowsky;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class Day8Test {
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

        s = """
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
        signals = parse(s);
        assertEquals(4, Day8.findAntinodes(signals).size());

        s = """
                ......#....#
                ...#....0...
                ....#0....#.
                ..#....0....
                ....0....#..
                .#....A.....
                ...#........
                #......#....
                ........A...
                .........A..
                ..........#.
                ..........#.""";
        signals = parse(s);
        System.out.println(Day8.findAntinodes(signals));
        assertEquals(14, Day8.findAntinodes(signals).size());
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