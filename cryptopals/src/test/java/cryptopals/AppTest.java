package cryptopals;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.ByteArrayOutputStream;

import org.junit.jupiter.api.Test;

/**
 * Unit test for simple App.
 */
public class AppTest {

    @Test
    void hexToBinary() {

        var app = new App();

        // base case
        var t1 = "4b";

        var buf = new ByteArrayOutputStream();
        for (char c : t1.toCharArray()) {
            buf.write(Character.digit(c, 16));
        }

        var retval = app.toBinary(buf.toByteArray());
        // with 0-padding expect 01001011
        // which is the same as 1001011
        assertEquals("1001011", Integer.toString(retval[0], 2));

        // fails on odd length
        try {
            app.toBinary(new byte[] { 4 });
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("byte[] src must have even length", e.getMessage());
        }
    }
}
