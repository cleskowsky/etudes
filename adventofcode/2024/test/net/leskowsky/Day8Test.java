package net.leskowsky;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static net.leskowsky.Day8.findAntinodes;
import static net.leskowsky.Day8.findAntinodes2;
import static org.junit.jupiter.api.Assertions.assertEquals;

class Day8Test {
    private Day8.SignalMap parse(String s) {
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
    void testFindAntinodes() {
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
        assertEquals(4, findAntinodes(signals).size());

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
        System.out.println(findAntinodes(signals));
        assertEquals(14, findAntinodes(signals).size());
    }

    @Test
    void part1() throws IOException {
        // find signals
        // foreach signal
        //   find pairs
        //   foreach pair
        //     find antinodes
        // count antinodes
        var signals = parse(Files.readString(Path.of("inputs/day8.txt")));
        assertEquals(376, findAntinodes(signals).size());
    }

    @Test
    void part2Example() {
        var s = """
                T....#....
                ...T......
                .T....#...
                .........#
                ..#.......
                ..........
                ...#......
                ..........
                ....#.....
                ..........""";
        var signals = parse(s);
        System.out.println(findAntinodes2(signals).size());
    }

    @Test
    void part2() throws IOException {
        var signals = parse(Files.readString(Path.of("inputs/day8.txt")));
        assertEquals(1352, findAntinodes2(signals).size());
    }
}
