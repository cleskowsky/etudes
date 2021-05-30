package a;

import org.junit.jupiter.api.Test;

import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class Day19Test {
    @Test
    void simpleMessage() {
        RuleSet rs = new RuleSet();
        Pattern zero = new Pattern(1, 1);
        rs.put(0, zero);
        rs.put(1, new Terminal('a'));
        assertTrue(Day19.checkMessage(null, "aa", rs));
        assertFalse(Day19.checkMessage(null, "ab", rs));
        assertFalse(Day19.checkMessage(null, "a", rs));
        assertFalse(Day19.checkMessage(null, "aaa", rs));

    }

    @Test
    void messageWithChoice() {
        RuleSet rs = new RuleSet();
        Pattern zero = new Pattern(1, 2);
        rs.put(0, zero);
        rs.put(1, new Terminal('a'));
        rs.put(2, new Choice(new Pattern(1, 3), new Pattern(3, 1)));
        rs.put(3, new Terminal('b'));
        assertFalse(Day19.checkMessage(null, "a", rs));
        assertTrue(Day19.checkMessage(null, "aab", rs));
        assertTrue(Day19.checkMessage(null, "aba", rs));
    }

    @Test
    void usingSampleRuleSetFromAdventOfCode() {
        RuleSet rs = new RuleSet();
        Pattern zero = new Pattern(4, 1, 5);
        rs.put(0, zero);
        rs.put(1, new Choice(new Pattern(2, 3), new Pattern(3, 2)));
        rs.put(2, new Choice(new Pattern(4, 4), new Pattern(5, 5)));
        rs.put(3, new Choice(new Pattern(4, 5), new Pattern(5, 4)));
        rs.put(4, new Terminal('a'));
        rs.put(5, new Terminal('b'));
        assertTrue(Day19.checkMessage(null, "aaaabb", rs));
        assertTrue(Day19.checkMessage(null, "aaabab", rs));
        assertTrue(Day19.checkMessage(null, "abbabb", rs));
        assertTrue(Day19.checkMessage(null, "abbbab", rs));
        assertTrue(Day19.checkMessage(null, "aabbbb", rs));
        assertTrue(Day19.checkMessage(null, "abaaab", rs));
        assertTrue(Day19.checkMessage(null, "ababbb", rs));
    }

    @Test
    void part1() {
        RuleSet rs = InputRules("inputs/19rules.txt");
        List<String> messages = InputMessages("inputs/19messages.txt");
        int cnt = 0;
        for (String s : messages) {
            if (Day19.checkMessage(null, s, rs)) {
                cnt++;
            }
        }
        System.out.println(cnt);
    }

    @Test
    void part2() {
        RuleSet rs = InputRules("inputs/19rules.txt");
        rs.put(8, new Choice(new Pattern(42), new Pattern(42, 8)));
        rs.put(11, new Choice(new Pattern(42, 31), new Pattern(42, 11, 31)));
        List<String> messages = InputMessages("inputs/19messages.txt");
        int cnt = 0;
        for (String s : messages) {
            if (Day19.checkMessage(null, s, rs)) {
                cnt++;
            }
        }
        System.out.println(cnt);
    }

    private RuleSet InputRules(String fname) {
        RuleSet rs = new RuleSet();
        try {
            List<String> lines = Files.readAllLines(Path.of(new URI(fname).toString()));
            for (String s : lines) {
                String[] split = s.split(": ");
                int ruleID = Integer.parseInt(split[0]);
                if (split[1].contains("a")) {
                    // Terminal
                    rs.put(ruleID, new Terminal('a'));
                } else if (split[1].contains("b")) {
                    // Terminal
                    rs.put(ruleID, new Terminal('b'));
                } else {
                    // Pattern, choice
                    split = split[1].split(" \\| ");
                    if (split.length == 1) {
                        // A pattern
                        Pattern p = new Pattern();
                        for (String x : split[0].split(" ")) {
                            p.addRule(Integer.parseInt(x.strip()));
                        }
                        rs.put(ruleID, p);
                    } else {
                        // A choice
                        Choice c = new Choice();
                        for (String x : split) {
                            Pattern p = new Pattern();
                            for (String y : x.split(" ")) {
                                p.addRule(Integer.parseInt(y.strip()));
                            }
                            c.addPattern(p);
                        }
                        rs.put(ruleID, c);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    private List<String> InputMessages(String fname) {
        List<String> msgs = null;
        try {
            msgs = Files.readAllLines(Path.of(new URI(fname).toString()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return msgs;
    }
}