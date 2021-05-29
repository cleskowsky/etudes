package a;

import lombok.Data;

import java.util.*;

public class Day19 {

    /**
     * Check message matches rules in pattern using RuleSet rs
     * @param p Pattern : A list of rules we'll need to compare against individual
     *          message chars
     * @param s The satellite message to match
     * @param rs ResultSet : A set of rules
     * @return True if match
     */
    public static boolean checkMessage(List<Integer> p, String s, RuleSet rs) {
        for (int i = 0; i < p.size(); i++) {
            if (i > s.length() - 1) {
                return false;
            }
            int ruleID = p.get(i);
            if (rs.get(ruleID) instanceof Terminal) {
                Terminal t = (Terminal) rs.get(ruleID);
                if (s.charAt(i) == t.getC()) {
                    // Message string matches pattern up to i
                    continue;
                }
                return false;
            }
        }

        return true;
    }
}

interface Rule {
}

@Data
class Pattern implements Rule {
    List<Integer> children;

    public Pattern(Integer... n) {
        children = Arrays.asList(n);
    }
}

@Data
class Choice implements Rule {
    List<Pattern> children;

    public Choice(Pattern... n) {
        this.children = Arrays.asList(n);
    }
}

@Data
class Terminal implements Rule {
    char c;

    public Terminal(char c) {
        this.c = c;
    }
}

@Data
class RuleSet {
    Map<Integer, Rule> m = new HashMap<>();

    public Rule get(int n) {
        return m.get(n);
    }

    public void put(int n, Rule r) {
        m.put(n, r);
    }
}