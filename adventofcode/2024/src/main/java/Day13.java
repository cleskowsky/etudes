
public class Day13 {

    // Returns the cheapest presses of buttons a, b to reach the prize
    // Pressing button a costs 3 credits
    // Pressing button b costs 1 credits
    public static SolverResult solver(Prize p, Button a, Button b) {
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
}
