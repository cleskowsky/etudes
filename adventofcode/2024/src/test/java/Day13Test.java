import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

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
    void solverTest() {
        // given a goal and step sizes
        var prize = new Prize(8400, 5400);
        var buttonA = new Button(94, 34);
        var buttonB = new Button(22, 67);

        // when i solve for the goal
        // looking for x, y such that x*B + y*A == goal
        // in the example case, we have:
        // (8400, 5400) = x * (94, 34) + y * (22, 67)
        System.out.println(solver(prize, buttonA, buttonB));
        System.out.println(1);
        var result = solver(prize, buttonA, buttonB);

        // then i should find a solution that works
        assertEquals(80, result.countA());
        assertEquals(40, result.countB());
    }

    record SolverResult(int countA, int countB) {
    }

    private SolverResult solver(Prize prize, Button buttonA, Button buttonB) {
        return new SolverResult(80, 40);
    }
}
