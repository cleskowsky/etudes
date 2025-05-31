import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day13Test {

    // it looks like we're given a goal and 2 different steps
    // sizes and we're supposed to use some combination of those
    // steps to reach the goal. it's probably the case that
    // different variations of steps will work. after i get
    // a basic solver working we can optimize ...
    //
    // Note: Pressing button a costs 3 credits, pressing button b
    // costs 1 credits

    public record Prize(int x, int y) {
    }

    public record Button(int x, int y) {
    }

    public record SolverResult(int countA, int countB) {
    }

    record Claw(Prize p, Button a, Button b, SolverResult r) {
    }

    @Test
    void testSolver() {

        // given a goal and step sizes
        var testTable = List.of(
                new Claw(prize(8400, 5400), button(94, 34), button(22, 67), result(80, 40)),
                new Claw(prize(12748, 12176), button(26, 66), button(67, 21), result(0, 0)),
                new Claw(prize(7870, 6450), button(17, 86), button(84, 37), result(38, 86)),
                new Claw(prize(18641, 10279), button(69, 23), button(27, 71), result(0, 0))
        );

        // when i solve for the goal
        var cnta = 0;
        var cntb = 0;
        for (var t : testTable) {
            var result = solver(t.p, t.a, t.b);
            cnta += result.countA;
            cntb += result.countB;

            // then i should find the min solution that works
            assertEquals(t.r, result);
        }

        System.out.println(3 * cnta + cntb);
    }

    // Returns the cheapest way to reach the prize by pushing buttons a, b
    public SolverResult solver(Prize p, Button a, Button b) {
        var result = new SolverResult(0, 0);

        for (int i = 1; i < 101; i++) {
            for (int j = 1; j < 101; j++) {
                if (p.x == i * a.x + j * b.x && p.y == i * a.y + j * b.y) {
                    result = new SolverResult(i, j);
                }
            }
        }

        return result;
    }

    Prize prize(int x, int y) {
        return new Prize(x, y);
    }

    Button button(int x, int y) {
        return new Button(x, y);
    }

    SolverResult result(int a, int b) {
        return new SolverResult(a, b);
    }

    @Test
    void part1() {
        var claws = parseInput("inputs/day13.txt");
        assertEquals(320, claws.size());

        var cnta = 0;
        var cntb = 0;
        for (var c : claws) {
            var result = solver(c.p, c.a, c.b);
            cnta += result.countA;
            cntb += result.countB;
        }

        System.out.println(3 * cnta + cntb);
    }

    // Example input:
    //    Button A: X+40, Y+38
    //    Button B: X+21, Y+84
    //    Prize: X=4245, Y=5634
    //
    //    Button A: X+19, Y+11
    //    Button B: X+37, Y+98
    //    Prize: X=3246, Y=6474
    //    ...
    private List<Claw> parseInput(String s) {
        var result = new ArrayList<Claw>();

        try {
            var lines = Files.readString(Path.of(s)).split("\n");
            for (int i = 0; i < lines.length; i += 4) {
                var b1 = parseInts(lines[i]);
                var b2 = parseInts(lines[i + 1]);
                var p = parseInts(lines[i + 2]);
                result.add(
                        new Claw(
                                new Prize(p.getFirst(), p.getLast()),
                                new Button(b1.getFirst(), b1.getLast()),
                                new Button(b2.getFirst(), b2.getLast()),
                                // placeholder
                                new SolverResult(0, 0)
                        )
                );
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return result;
    }

    private List<Integer> parseInts(String s) {
        var m = Pattern.compile("\\d+");
        return m.matcher(s).results()
                .map(MatchResult::group)
                .map(Integer::parseInt)
                .toList();
    }
}
