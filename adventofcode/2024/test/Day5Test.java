import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.fail;

class Day5Test {

    @Test
    public void goodOrdering() {
        // Given a set of page pageOrderRules and a manual update ordering
        var parser = new Day5.Parser();
        Day5.Parser.ParserResult parserResult = parser.parse("");
        var pageOrderRules = parserResult.pageOrderRules();
        var manualUpdates = parserResult.updates();

        // When the ordering is validated
        // Then it is determined to be good
        fail();
    }
}
