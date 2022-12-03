import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class Day2Test {

    @Test
    void score() {
        Map<String, Integer> testTable = Map.of(
                "A X", 4,
                "A Y", 8,
                "A Z", 3,
                "B X", 1,
                "B Y", 5,
                "B Z", 9,
                "C X", 7,
                "C Y", 2,
                "C Z", 6
        );
        var d = new Day2();
        testTable.forEach((k, v) -> {
            assertEquals(v, d.score(new Day2.Round(k)));
        });
    }
}