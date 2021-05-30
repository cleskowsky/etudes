package a;

import org.junit.jupiter.api.Test;

import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
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
        assertTrue(Day19.checkMessage(zero.getChildren(), "aa", rs));
        assertFalse(Day19.checkMessage(zero.getChildren(), "ab", rs));
        assertFalse(Day19.checkMessage(zero.getChildren(), "a", rs));
        assertFalse(Day19.checkMessage(zero.getChildren(), "aaa", rs));

    }

    @Test
    void messageWithChoice() {
        RuleSet rs = new RuleSet();
        Pattern zero = new Pattern(1, 2);
        rs.put(0, zero);
        rs.put(1, new Terminal('a'));
        rs.put(2, new Choice(new Pattern(1, 3), new Pattern(3, 1)));
        rs.put(3, new Terminal('b'));
        assertFalse(Day19.checkMessage(zero.getChildren(), "a", rs));
        assertTrue(Day19.checkMessage(zero.getChildren(), "aab", rs));
        assertTrue(Day19.checkMessage(zero.getChildren(), "aba", rs));
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
        assertTrue(Day19.checkMessage(zero.getChildren(), "aaaabb", rs));
        assertTrue(Day19.checkMessage(zero.getChildren(), "aaabab", rs));
        assertTrue(Day19.checkMessage(zero.getChildren(), "abbabb", rs));
        assertTrue(Day19.checkMessage(zero.getChildren(), "abbbab", rs));
        assertTrue(Day19.checkMessage(zero.getChildren(), "aabbbb", rs));
        assertTrue(Day19.checkMessage(zero.getChildren(), "abaaab", rs));
        assertTrue(Day19.checkMessage(zero.getChildren(), "ababbb", rs));
    }

    @Test
    void part1() {
        RuleSet rs = InputRules("inputs/19rules.txt");
        System.out.println(rs);
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
                    List<Pattern> x = new ArrayList<>();
                    for (String pattern : split) {
                        String[] numList = pattern.split(" ");
                        List<Integer> nums = new ArrayList<>();
                        for (String n : numList) {
                            nums.add(Integer.parseInt(n.strip()));
                        }
                        x.add(new Pattern(nums));
                    }
                    if (x.size() == 1) {
                        // A pattern
                        rs.put(ruleID, x.get(0));
                    } else {
                        // A choice
                        rs.put(ruleID, new Choice(x));
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