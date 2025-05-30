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
}
