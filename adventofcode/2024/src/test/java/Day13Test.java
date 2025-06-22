import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.*;

public class Day13Test {

    // it looks like we're given a goal and 2 different steps
    // sizes and we're supposed to use some combination of those
    // steps to reach the goal. it's probably the case that
    // different variations of steps will work. after i get
    // a basic solver working we can optimize ...
    //
    // Note: Pressing button a costs 3 credits, pressing button b
    // costs 1 credits

    public record Prize(long x, long y) {
    }

    public record Button(int x, int y) {
    }

    public record SolverResult(long countA, long countB) {
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
                new Claw(prize(18641, 10279), button(69, 23), button(27, 71), result(0, 0)));

        // when i solve for the goal
        var cnta = 0L;
        var cntb = 0L;
        for (var t : testTable) {
            System.out.println(t);

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

        double i1 = a.x * b.y - a.y * b.x;
        var i = (b.y * p.x - p.y * b.x) / i1;
        var j = (p.y * a.x - a.y * p.x) / i1;

        if ((i % 1 == 0) && (j % 1 == 0)) {
            return new SolverResult((long) i, (long) j);
        }

        return new SolverResult(0, 0);
    }

    Prize prize(int x, int y) {
        return new Prize(x, y);
    }

    Button button(int x, int y) {
        return new Button(x, y);
    }

    SolverResult result(long a, long b) {
        return new SolverResult(a, b);
    }

    @Test
    void part1() {
        var claws = parseInput("inputs/day13.txt");
        assertEquals(320, claws.size());

        var cnta = 0L;
        var cntb = 0L;
        for (var c : claws) {
            var result = solver(c.p, c.a, c.b);
            cnta += result.countA;
            cntb += result.countB;
        }

        System.out.println(3 * cnta * 10000000000000L);
        assertEquals(40369, 3 * cnta + cntb);
    }

    // Example input:
    // Button A: X+40, Y+38
    // Button B: X+21, Y+84
    // Prize: X=4245, Y=5634
    //
    // Button A: X+19, Y+11
    // Button B: X+37, Y+98
    // Prize: X=3246, Y=6474
    // ...
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
                                new SolverResult(0, 0)));
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

    @Test
    void testSolver2() {
        // given goal x, delta d1, delta d2
        var goal = 8400;
        var d1 = 94;
        var d2 = 22;

        // when i subtract d1 from it i times
        // then d2 should divide it as well if there is an answer
        // want to find i, j such that i * d1 + j * d2 = goal
        var pairs = new ArrayList<Pair>();

        var i = 0;
        while (goal >= 0) {
            if (goal % d2 == 0) {
                pairs.add(new Pair(i, goal / d2));
            }
            goal -= d1;
            i++;
        }

        // i'll have to track how many times I've subtracted d1
        var min = pairs.getFirst();
        var cost = 3 * min.i + min.j;

        for (var p : pairs) {
            var x = 3 * p.i + p.j;
            if (x < cost) {
                min = p;
                cost = x;
            }
        }

        assertEquals(new Pair(80, 40), min);
    }

    record Pair(int i, int j) {
    }

    @Test
    void solve() {
        // given 2 equations with 2 unknowns
        // new Claw(prize(8400, 5400), button(94, 34), button(22, 67), result(80, 40)),

        var claw = new Claw(prize(8400, 5400), button(94, 34), button(22, 67), result(80, 40));

        // px = i * ax + j * bx
        // py = i * ay + j * by

        // 8400 = i * 94 + j * 22
        // 5400 = i * 34 + j * 67

        // solve for j
        // i = (8400 - j * 22) / 94
        // 34 * (8400 - j * 22) / 94 + 67 * j = 5400
        // 34 * (8400 - j * 22) + 94 * 67 * j = 5400 * 94
        // this is it ... i can get j using values from the input
        // 34 * 8400 - 34 * 22 * j + 94 * 67 * j = 5400 * 94

        // and again for i
        // j = (5400 - i * 34) / 67
        // 8400 = i * 94 + (5400 - i * 34) / 67 * 22
        // 67 * 8400 = i * 94 * 67 + (5400 - i * 34) * 22
        // 67 * 8400 = i * 94 * 67 + 5400 * 22 - i * 34 * 22
        // 67 * 8400 - 5400 * 22 = i * 94 * 67 - i * 34 * 22
        // i = (67 * 8400 - 5400 * 22) / (94 * 67 - 34 * 22)

        double d = claw.a.x * claw.b.y - claw.a.y * claw.b.x;
        var i = (claw.b.y * claw.p.x - claw.p.y * claw.b.x) / d;
        var j = (claw.p.y * claw.a.x - claw.a.y * claw.p.x) / d;

        assertEquals(claw.r, result((long) i, (long) j));

        // when given to solver to solve
        // then solver either finds a solution or indicates no solution exists
    }

    @Test
    void cantSolve() {
        var claw = new Claw(prize(12748, 12176), button(26, 66), button(67, 21), result(0, 0));

        double d = claw.a.x * claw.b.y - claw.a.y * claw.b.x;
        var i = (claw.b.y * claw.p.x - claw.p.y * claw.b.x) / d;
        var j = (claw.p.y * claw.a.x - claw.a.y * claw.p.x) / d;

        assertNotEquals(Math.floor(i), i, 0.0);
        assertNotEquals(Math.floor(j), j, 0.0);
    }

}
