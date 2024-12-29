import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

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
                continue;
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
}
