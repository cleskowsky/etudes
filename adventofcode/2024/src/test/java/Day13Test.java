import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day13Test {

    // it looks like we're given a goal and 2 different steps
    // sizes and we're supposed to use some combination of those
    // steps to reach the goal. it's probably the case that
    // different variations of steps will work. after i get
    // a basic solver working we can optimize ...

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


}
