import util.FileUtils;

import java.util.HashMap;

public class Day6 {
    public static void main(String[] args) {
        var s = FileUtils.readAll("in/day6.txt");
        System.out.println("Start of packet: " + marker(s, 4));
        System.out.println("Start of message: " + marker(s, 14));
    }

    /**
     * Return first substring of distinct chars in s of length n
     */
    public static int marker(String s, int n) {
        for (int i = n; i < s.length(); i++) {
            if (n == new Counter(s.substring(i - n, i)).keySet().size()) {
                return i;
            }
        }
        throw new RuntimeException("Couldn't find marker");
    }

    /**
     * Count char occurrences in a string
     * <p>
     * Eg "abbc" would return Counter {
     * a: 1,
     * b: 2,
     * c: 1
     * }
     */
    static class Counter extends HashMap<Character, Integer> {
        public Counter(String s) {
            for (Character c : s.toCharArray()) {
                put(c, getOrDefault(c, 0) + 1);
            }
        }
    }
}
