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

    public record Prize(int x, int y) {
    }

    public record Button(int x, int y) {
    }

    @Test
    void testSolver() {
        // given a goal and step sizes
        var prize = new Prize(8400, 5400);
        var buttonA = new Button(94, 34);
        var buttonB = new Button(22, 67);

        // when i solve for the goal
        // looking for x, y such that x*B + y*A == goal
        // in the example case, we have:
        // (8400, 5400) = x * (94, 34) + y * (22, 67)
        var result = solver(prize, buttonA, buttonB);

        // then i should find the min solution that works
        assertEquals(new SolverResult(80, 40), result);
    }

    record SolverResult(int countA, int countB) {
    }

    // Returns the cheapest presses of buttons a, b to reach the prize
    // Pressing button a costs 3 credits
    // Pressing button b costs 1 credits
    private SolverResult solver(Prize p, Button a, Button b) {
        var result = new SolverResult(0, 0);

        for (int i = 1; i < 101; i++) {
            for (int j = 1; j < 101; j++) {
                int x = i * a.x + j * b.x;
                int y = i * a.y + j * b.y;
                if (p.x == x && p.y == y) {
                    result = new SolverResult(i, j);
                }
            }
        }

        return result;
    }

    @Test
    void noSolution1() {
        var prize = new Prize(12748, 12176);
        var buttonA = new Button(26, 66);
        var buttonB = new Button(67, 21);

        assertEquals(new SolverResult(0, 0), solver(prize, buttonA, buttonB));
    }

    @Test
    void solverTest2() {
        var prize = new Prize(7870, 6450);
        var buttonA = new Button(17, 86);
        var buttonB = new Button(84, 37);

        assertEquals(new SolverResult(38, 86), solver(prize, buttonA, buttonB));
    }

    @Test
    void part1() {
        var machines = parseInput("inputs/day13.txt");
        System.out.println(machines);
        System.out.println(machines.size());
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
    private List<Machine> parseInput(String s) {
        var result = new ArrayList<Machine>();

        try {
            var lines = Files.readString(Path.of(s)).split("\n");
            for (int i = 0; i < lines.length; i += 4) {
                var b1 = parseInts(lines[i]);
                var b2 = parseInts(lines[i + 1]);
                var p = parseInts(lines[i + 2]);
                result.add(
                        new Machine(
                                new Prize(p.getFirst(), p.getLast()),
                                new Button(b1.getFirst(), b1.getLast()),
                                new Button(b2.getFirst(), b2.getLast())
                        )
                );
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return result;
    }

    record Machine(Prize p, Button a, Button b) {
    }

    @Test
    void testParseInts() {
        var x = parseInts("Button A: X+40, Y+38");
        assertEquals(new Button(40, 38), new Button(x.get(0), x.get(1)));
    }

    private List<Integer> parseInts(String s) {
        var m = Pattern.compile("\\d{2}");
        return m.matcher(s).results()
                .map(MatchResult::group)
                .map(Integer::parseInt)
                .toList();
    }
}
