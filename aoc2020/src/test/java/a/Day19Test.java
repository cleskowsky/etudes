package a;

import org.junit.jupiter.api.Test;

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
    void withSampleRuleSet() {
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
}