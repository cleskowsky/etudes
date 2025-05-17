import org.junit.jupiter.api.Test;

import java.util.Map;

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

    // Regions
    @Test
    void findRegions() {
        var testTable = Map.of(
                "A", 1,
                """
                        AB
                        AB""", 2,
                """
                        AAAA
                        BBCD
                        BBCC
                        EEEC""", 5,
                """
                        OOOOO
                        OXOXO
                        OOOOO
                        OXOXO
                        OOOOO""", 5
        );
        for (var t : testTable.entrySet()) {
            var d = new Day12();
            var farm = Day12.parseInput(t.getKey());
            assertEquals(t.getValue(), d.regions(farm).size());
        }
    }

    // Region area
    // Region perimeter
    // Region fence price
}
