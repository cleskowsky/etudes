import util.FileUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

public class Day5 {

    public static boolean PART_A = false;

    public static void main(String[] args) {
//        var s = util.FileUtils.readAll("in/day5_sample.txt");
        var s = FileUtils.readAll("in/day5.txt");

        var stacks = stacks(s);
        var moves = moves(s);

        for (Move m : moves) {
            if (PART_A) {
                for (int i = 0; i < m.n(); i++) {
                    stacks.get(m.to()).push(
                            stacks.get(m.from()).pop());
                }
            } else {
                var x = new ArrayList<Character>();
                for (int i = 0; i < m.n(); i++) {
                    x.add(0, stacks.get(m.from()).pop());
                }
                stacks.get(m.to()).addAll(x);
            }
        }

        var x = "";
        for (int i = 1; i < stacks.size() + 1; i++) {
            x += stacks.get(i).peek();
        }
        System.out.println("Answer: " + x);
    }

    record Move(int n, int from, int to) {
    }

    /**
     * Parse moves from input
     */
    public static List<Move> moves(String s) {
        var ret = new ArrayList<Move>();

        for (String line : s.split("\n")) {
            if (line.startsWith("move")) {
                var split = line.split(" ");
                ret.add(new Move(
                        Integer.parseInt(split[1]),
                        Integer.parseInt(split[3]),
                        Integer.parseInt(split[5])
                ));
            }
        }

        return ret;
    }

    /**
     * Parse stacks from input
     */
    private static HashMap<Integer, Stack<Character>> stacks(String s) {
        var m = new HashMap<Integer, Stack<Character>>();

        for (String line : s.split("\n")) {
            if (line.trim().isEmpty()) {
                return m;
            }
            for (int i = 0; i < line.length(); i++) {
                char c = line.charAt(i);
                if (Character.isAlphabetic(c)) {
                    var stack = m.getOrDefault(i, new Stack<>());
                    stack.add(0, c);
                    m.put(i, stack);
                } else if (Character.isDigit(c)) {
                    m.put(Character.getNumericValue(c), m.remove(i));
                }
            }
        }

        throw new RuntimeException("Couldn't parse stacks");
    }
}
