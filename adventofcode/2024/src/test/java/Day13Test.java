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

    public record SolverResult(int countA, int countB) {
    }

    @Test
    void solver() {

        // given a goal and step sizes
        var testTable = List.of(
                // Goal, ButtonA, ButtonB, Expected result
                List.of(prize(8400, 5400), button(94, 34), button(22, 67), result(80, 40)),
                List.of(prize(12748, 12176), button(26, 66), button(67, 21), result(0, 0)),
                List.of(prize(7870, 6450), button(17, 86), button(84, 37), result(38, 86))
        );

        // when i solve for the goal
        for (var t : testTable) {
            var result = Day13.solver(t.get(0), t.get(1), t.get(2));

            // then i should find the min solution that works
            assertEquals(t.get(3), result);
        }
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
