import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class Day6Test {

    @Test
    void canParseInput() {
        var s = """
                ..
                .#
                """;
        System.out.println(s);
        Day6.Lab lab = InputParser.parse(s);
        assertEquals(4, lab.floor().keySet().size());
        assertFalse(lab.isBlocked(0, 0));
        assertTrue(lab.isBlocked(1, 1));
    }

    // walks forward
    // turns right to avoid obstacles
    // walks outside mapped area
    // remembers path taken

    class InputParser {
        static Day6.Lab parse(String s) {
            var floor = new HashMap<Day6.Point, Boolean>();

            var rows = s.split("\n");
            for (int i = 0; i < rows.length; i++) {
                var row = rows[i].toCharArray();
                for (int j = 0; j < row.length; j++) {
                    if (row[j] == '#') {
                        floor.put(new Day6.Point(j, i), true);
                    } else {
                        floor.put(new Day6.Point(j, i), false);
                    }
                }
            }

            return new Day6.Lab(floor);
        }
    }
}