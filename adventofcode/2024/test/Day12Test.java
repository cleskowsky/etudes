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
    // This is just a count of plots in a region and we have region already

    // Region perimeter
    @Test
    void regionPerimeter() {
        var s = """
                AAAA
                BBCD
                BBCC
                EEEC""";
        var d = new Day12();
        var fenceNeeded = d.regions(Day12.parseInput(s)).stream()
                .map(d::perimeter)
                .reduce(0, Integer::sum);
        assertEquals(40, fenceNeeded);
    }

    // Region fence price
    @Test
    void regionFencePrice() {
        var s = """
                AAAA
                BBCD
                BBCC
                EEEC""";
        var d = new Day12();
        var totalCost = d.regions(Day12.parseInput(s)).stream()
                .map(d::fencePrice)
                .reduce(0, Integer::sum);
        assertEquals(140, totalCost);
    }

    // Region fence price 2
    @Test
    void regionFencePrice2() {
        var s = """
                RRRRIICCFF
                RRRRIICCCF
                VVRRRCCFFF
                VVRCCCJFFF
                VVVVCJJCFE
                VVIVCCJJEE
                VVIIICJJEE
                MIIIIIJJEE
                MIIISIJEEE
                MMMISSJEEE""";
        var d = new Day12();

        var farm = Day12.parseInput(s);
        d.regions(farm).forEach(r -> {
            System.out.println(r);
            System.out.println(r.plots().size());
        });
        assertEquals(11, d.regions(farm).size());

        var totalCost = d.regions(Day12.parseInput(s)).stream()
                .map(d::fencePrice)
                .reduce(0, Integer::sum);
        assertEquals(1930, totalCost);
    }
}
