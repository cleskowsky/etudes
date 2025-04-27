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
        var farm = Day12.InputParser.parse(s);

        // Then a farm is returned with 5 plots
        assertEquals(5, farm.plots());
    }

    // another test
    // ...
}
