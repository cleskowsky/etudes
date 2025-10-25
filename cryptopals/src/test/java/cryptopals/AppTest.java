package cryptopals;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

/**
 * Unit test for simple App.
 */
public class AppTest {

    @Test
    void hexToBinary() {

        var app = new App();

        // base case
        var test = "4b";
        assertEquals("01001011", app.toBinary(test));

        // fails on odd length
        try {
            app.toBinary("4");
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Hex string must have even length", e.getMessage());
        }
    }
}
