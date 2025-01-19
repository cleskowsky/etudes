import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class Day5Test {

    @Test
    public void goodOrdering() throws IOException {
        // Given a set of page pageOrderRules and a manual update ordering
        var parserResult = parseSampleInput();

        // When the ordering is validated (*I'll validate the first sample
        // update since that is known good)
        var update = parserResult.updates().get(0);
        for (var rule : parserResult.pageOrderRules()) {
            if (rule.validates(update)) {
                continue;
            }
            // Then it is determined to be good
            fail();
        }
    }

    @Test
    public void badOrdering() throws IOException {
        // Given a set of page pageOrderRules and a manual update ordering
        var parserResult = parseSampleInput();

        // When the ordering is validated (*I'll validate the 4th sample
        // update since that is known bad)
        var update = parserResult.updates().get(3);
        var valid = true;
        for (var rule : parserResult.pageOrderRules()) {
            if (rule.validates(update)) {
            } else {
                valid = false;
            }
        }
        assertFalse(valid);
    }

    private Day5.Parser.ParserResult parseSampleInput() {
        var parser = new Day5.Parser();
        try {
            return parser.parse(Files.readString(Path.of("inputs/day5_sample.txt")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void fixUpdate(Day5.Update bad, Day5.Update fixed) {
        var input = parseSampleInput();
        var d = new Day5();
        assertEquals(fixed, d.fixUpdate(bad, input.pageOrderRules()));
    }

    @Test
    public void shouldFixBadUpdates() {
        var tt = Map.of(
                // table of update repair tests
                // bad update, fixed update
                new Day5.Update(List.of(75, 97, 47, 61, 53)), new Day5.Update(List.of(97, 75, 47, 61, 53)),
                new Day5.Update(List.of(61, 13, 29)), new Day5.Update(List.of(61, 29, 13)),
                new Day5.Update(List.of(97, 13, 75, 29, 47)), new Day5.Update(List.of(97, 75, 47, 29, 13))
        );
        for (var t : tt.entrySet()) {
            fixUpdate(t.getKey(), t.getValue());
        }
    }

    @Test
    public void part2() throws IOException {
        var input = new Day5.Parser().parse(Files.readString(Path.of("inputs/day5.txt")));

        var day5 = new Day5();
        var sum = 0;
        for (Day5.Update u : input.updates()) {
            if (Day5.validUpdate(u, input.pageOrderRules())) {
                continue;
            }
            var fixed = day5.fixUpdate(u, input.pageOrderRules());
            int mid = fixed.pages().size() / 2;
            sum += fixed.pages().get(mid);
        }
        System.out.println(sum);
    }
}
