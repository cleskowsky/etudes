package a;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Day19Test {
    @Test
    void checkMessage() {
        RuleSet rs = new RuleSet();
        Pattern zero = new Pattern(1, 1);
        rs.put(0, zero);
        rs.put(1, new Terminal('a'));
        assertTrue(Day19.checkMessage(zero.getChildren(), "aa", rs));
        assertFalse(Day19.checkMessage(zero.getChildren(), "ab", rs));
        assertFalse(Day19.checkMessage(zero.getChildren(), "a", rs));
        assertFalse(Day19.checkMessage(zero.getChildren(), "aaa", rs));
    }

    private RuleSet getSimpleRuleSet() {
        RuleSet rs = new RuleSet();
        rs.put(0, new Pattern(1, 2));
        rs.put(1, new Terminal('a'));
        rs.put(2, new Choice(new Pattern(1, 3), new Pattern(3, 1)));
        rs.put(3, new Terminal('b'));
        return rs;
    }
}