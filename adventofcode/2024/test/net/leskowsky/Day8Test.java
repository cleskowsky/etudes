package net.leskowsky;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class Day8Test {

    @Test
    void part1() {
        fail();
    }

    @Test
    void parseInput() {
        String s = """
                .a
                a.""";

        assertEquals(1, parseMap(s).signals().size());
        assertEquals(2, parseMap(s).signals().get('a').size());
    }

    record ParseResult(Map<Character, List<Point>> signals) {
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
                if (c == '.') {
                    continue;
                }
                var x = signals.getOrDefault(c, new ArrayList<>());
                x.add(new Point(i, j));
                signals.put(c, x);
            }
        }
        System.out.println(signals);

        return new ParseResult(signals);
    }
}