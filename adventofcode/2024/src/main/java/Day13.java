import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;

public class Day13 {

    public record Prize(int x, int y) {
    }

    public record Button(int x, int y) {
    }

    public record SolverResult(int countA, int countB) {
    }

    record Machine(Prize p, Button a, Button b) {
    }

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

    private List<Integer> parseInts(String s) {
        var m = Pattern.compile("\\d{2}");
        return m.matcher(s).results()
                .map(MatchResult::group)
                .map(Integer::parseInt)
                .toList();
    }
}
