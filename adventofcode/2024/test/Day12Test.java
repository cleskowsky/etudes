import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day12Test {

    @Test
    void parseInput() {
        // Given a string representation of garden plots
        var s = """
                AAAA
                BBCD
                BBCC
                EEEC""";

        // When parsed
        var farm = Day12.parseInput(s);

        // Then a farm is returned with 16 plots
        assertEquals(16, farm.plots().size());
    }

    // Region fence price
    // Region area
    // Region perimeter
}
