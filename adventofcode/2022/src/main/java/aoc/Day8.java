package aoc;

import java.util.HashMap;

public class Day8 {

    public static void main(String[] args) {
        var s = """
                30373
                25512
                65332
                33549
                35390""";

        // Sample
        var sample = parseInput(s);
        System.out.println(
                sample.keySet().stream()
                        .filter(p -> visible(p, sample))
                        .count()
        );

        // Part 1
        var f = parseInput(FileUtils.readAll("in/day8.txt"));
        System.out.println(
                f.keySet().stream()
                        .filter(p -> visible(p, f))
                        .count()
        );

        // Part 2
        System.out.println(scenic(sample));
        System.out.println(scenic(f));
    }

    static int scenic(Forest f) {
        int max = 0;
        for (Point tree : f.keySet()) {
            int score = 1;

            for (HEADING h : HEADING.values()) {
                var seen = 0;
                var curr = tree.plus(h);
                while (f.get(curr) != null) {
                    seen++;
                    if (f.get(curr) >= f.get(tree)) {
                        break;
                    }
                    curr = curr.plus(h);
                }
                score = score * seen;
            }

            if (score > max) {
                max = score;
            }
        }
        return max;
    }

    static enum HEADING {
        NORTH(new Point(0, -1)),
        EAST(new Point(1, 0)),
        SOUTH(new Point(0, 1)),
        WEST(new Point(-1, 0));

        Point p;

        HEADING(Point p) {
            this.p = p;
        }
    }

    static boolean visible(Point tree, Forest f) {
        for (HEADING h : HEADING.values()) {
            if (visibleFromDir(tree, f, h)) {
                return true;
            }
        }
        return false;
    }

    static public boolean visibleFromDir(Point tree, Forest f, HEADING h) {
        int min = f.get(tree);

        var nextTree = tree.plus(h);
        if (nextTree == null) {
            // Edge trees are trivially visible
            return true;
        }

        while (f.get(nextTree) != null) {
            if (f.get(nextTree) >= min) {
                return false;
            }
            nextTree = nextTree.plus(h);
        }

        return true;
    }

    static class Forest extends HashMap<Point, Integer> {
    }

    static Forest parseInput(String s) {
        var f = new Forest();
        var split = s.split("\n");
        for (int i = 0; i < split.length; i++) {
            var row = split[i];
            for (int j = 0; j < row.toCharArray().length; j++) {
                f.put(new Point(j, i),
                        Character.getNumericValue(row.charAt(j)));
            }
        }
        return f;
    }
}
