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

    // Regions
    @Test
    void findRegions() {
        var s = """
                AB
                AB""";
        regionFinderTester(s, 2);
    }

    @Test
    void findRegions2() {
        var s2 = """
                AAAA
                BBCD
                BBCC
                EEEC""";
        regionFinderTester(s2, 5);
    }

    @Test
    void findRegions3() {
        var s = "A";
        regionFinderTester(s, 1);
    }

    @Test
    void findRegions4() {
        var s = """
                OOOOO
                OXOXO
                OOOOO
                OXOXO
                OOOOO""";
        regionFinderTester(s, 5);
    }

    private void regionFinderTester(String s, int expected) {
        var d = new Day12();
        var farm = Day12.parseInput(s);
        assertEquals(expected, d.regions(farm).size());
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
        assertEquals(11, d.regions(farm).size());

        var totalCost = d.regions(Day12.parseInput(s)).stream()
                .map(d::fencePrice)
                .reduce(0, Integer::sum);
        assertEquals(1930, totalCost);
    }
}
