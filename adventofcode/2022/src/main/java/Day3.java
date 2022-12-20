import aoc.FileUtils;

import java.util.HashMap;
import java.util.List;

public class Day3 {
    public static void main(String[] args) {
        for (String in : List.of("in/day3_sample.txt", "in/day3.txt")) {
            System.out.println("Input: " + in);

            System.out.println("Part 1: " + FileUtils.readLines(in)
                    .stream()
                    .map(x -> priority(badItem(x)))
                    .reduce(0, Integer::sum));

            var lines = FileUtils.readLines(in);
            var y = 0;
            for (int i = 0; i < lines.size(); i += 3) {
                y += priority(badge(
                        lines.get(i), lines.get(i + 1), lines.get(i + 2)));
            }
            System.out.println("Part 2: " + y);
        }
    }

    /**
     * Return team badge
     */
    private static char badge(String... s) {
        for (char c : s[0].toCharArray()) {
            if (s[1].indexOf(c) >= 0 && s[2].indexOf(c) >= 0) {
                return c;
            }
        }
        throw new RuntimeException("Couldn't find badge");
    }

    /**
     * Return item priority
     */
    private static int priority(char badItem) {
        return 1 + (Character.isLowerCase(badItem) ?
                badItem - 'a' :
                badItem - 'A' + 26);
    }

    /**
     * Return bad item type
     */
    private static char badItem(String s) {
        var items = new HashMap<Character, Boolean>();

        for (int i = 0; i < s.length(); i++) {
            if (i < s.length() / 2) {
                // first compartment
                items.put(s.charAt(i), true);
            } else {
                // second compartment
                if (items.containsKey(s.charAt(i))) {
                    return s.charAt(i);
                }
            }
        }

        throw new RuntimeException("Can't find bad item");
    }
}
