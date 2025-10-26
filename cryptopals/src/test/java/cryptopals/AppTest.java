package cryptopals;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.ByteArrayOutputStream;

import org.junit.jupiter.api.Test;

public class AppTest {

    @Test
    void hexToBinary() {

        var app = new App();

        // convert single byte from hex to binary
        var retval = Hex.toBinary("4b");

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

    // Today I learned ... :/
    @Test
    void digitMapsHexToDecimal() {

        var x = "0123456789abcdefABCDEF".toCharArray();

        for (char c : x) {
            var v = Character.digit(c, 16);
            if (c >= '0' && c <= '9') {
                assertEquals(c - '0', v);
            } else if (c >= 'a' && c <= 'f') {
                assertEquals(c - 'a' + 10, v);
            } else if (c >= 'A' && c <= 'F') {
                assertEquals(c - 'A' + 10, v);
            } else {
                fail("unexpected char: " + c);
            }
        }
    }

    @Test
    void toBase64() {
        // Given a byte array
        var src = Hex.toBinary("49276d206b696c6c696e6720796f757220627261696e206c696b65206120706f69736f6e6f7573206d757368726f6f6d");

        // When run through a base64 encoder,
        var retval = Base64.toBase64(src);

        // Then a string of base64-encoded bytes is produced
        assertEquals("SSdtIGtpbGxpbmcgeW91ciBicmFpbiBsaWtlIGEgcG9pc29ub3VzIG11c2hyb29t", retval);
    }
}
