package a;

import lombok.Data;

import java.util.*;

public class Day19 {

    /**
     * Check message matches rules in pattern using RuleSet rs
     *
     * @param p  Pattern : A list of rules we'll need to compare against individual
     *           message chars
     * @param s  The satellite message to match
     * @param rs ResultSet : A set of rules
     * @return True if match
     */
    public static boolean checkMessage(List<Integer> p, String s, RuleSet rs) {
        if (p == null) {
            // First call to checkMessage
            p = ((Pattern) rs.get(0)).getChildren();
        }

        if ((s.isEmpty() && !p.isEmpty()) ||
                (p.isEmpty() && !s.isEmpty())) {
            return false;
        } else if (p.isEmpty() && s.isEmpty()) {
            return true;
        }
        int ruleID = p.get(0);
        if (rs.get(ruleID) instanceof Terminal) {
            Terminal t = (Terminal) rs.get(ruleID);
            if (s.charAt(0) == t.getC()) {
                return checkMessage(p.subList(1, p.size()), s.substring(1), rs);
            }
        } else if (rs.get(ruleID) instanceof Choice) {
            Choice c = (Choice) rs.get(ruleID);
            for (Pattern x : c.getChildren()) {
                List<Integer> candidatePattern = new ArrayList<>();
                candidatePattern.addAll(x.getChildren());
                candidatePattern.addAll(p.subList(1, p.size()));
                if (checkMessage(candidatePattern, s, rs)) {
                    return true;
                }
            }
        } else if (rs.get(ruleID) instanceof Pattern) {
            Pattern patt = (Pattern) rs.get(ruleID);
            List<Integer> candidatePattern = new ArrayList<>();
            candidatePattern.addAll(patt.getChildren());
            candidatePattern.addAll(p.subList(1, p.size()));
            return checkMessage(candidatePattern, s, rs);
        }
        return false;
    }
}

interface Rule {
}

@Data
class Pattern implements Rule {
    List<Integer> children = new ArrayList<>();

    public Pattern(Integer... n) {
        Collections.addAll(children, n);
    }

    public void addRule(Integer n) {
        children.add(n);
    }
}

@Data
class Choice implements Rule {
    List<Pattern> children = new ArrayList<>();

    public Choice(Pattern... p) {
        Collections.addAll(children, p);
    }

    public void addPattern(Pattern p) {
        children.add(p);
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