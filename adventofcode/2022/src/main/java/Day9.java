import util.FileUtils;
import util.Point;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static java.lang.Math.*;

public class Day9 {

    public static void main(String[] args) {

        // Part 1 sample
        System.out.println(moveRope(List.of("R 4", "U 4", "L 3", "D 1", "R 4", "D 1", "L 5", "R 2"), 2));
        System.out.println(moveRope(List.of("R 4", "U 4", "L 3", "D 1", "R 4", "D 1", "L 5", "R 2"), 10));

        // Part 2 sample
        System.out.println(moveRope(List.of("R 5", "U 8", "L 8", "D 3", "R 17", "D 10", "L 25", "U 20"), 10));

        // Puzzle input
        System.out.println(moveRope(FileUtils.readLines("9"), 2));
        System.out.println(moveRope(FileUtils.readLines("9"), 10));
    }

    static int moveRope(List<String> moves, int knots) {
        List<Point> rope = new ArrayList<>();
        for (int i = 0; i < knots; i++) {
            rope.add(new Point(0, 0));
        }

        var seen = new HashSet<Point>();
        seen.add(new Point(0, 0));

        for (String s : moves) {
            var split = s.split(" ");
            for (int i = 0; i < Integer.parseInt(split[1]); i++) {
                var x = switch (split[0]) {
                    case "U" -> rope.get(0).add(0, 1);
                    case "D" -> rope.get(0).add(0, -1);
                    case "L" -> rope.get(0).add(-1, 0);
                    case "R" -> rope.get(0).add(1, 0);
                    default -> throw new RuntimeException("Couldn't move head");
                };
                rope.set(0, x);

                for (int j = 1; j < rope.size(); j++) {
                    rope.set(j, moveTail(rope.get(j - 1), rope.get(j)));
                }
                seen.add(rope.get(rope.size() - 1));
            }
        }

        return seen.size();
    }

    /**
     * Move tail towards head
     */
    static Point moveTail(Point head, Point tail) {
        var newTail = tail;

        var x = head.sub(tail.x(), tail.y());
        if (max(abs(x.x()), abs(x.y())) > 1) {
            if (x.x() != 0) {
                newTail = newTail.add((int) signum(x.x()), 0);
            }
            if (x.y() != 0) {
                newTail = newTail.add(0, (int) signum(x.y()));
            }
        }

        return newTail;
    }
}
