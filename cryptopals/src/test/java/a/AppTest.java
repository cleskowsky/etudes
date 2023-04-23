package a;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.fail;

class AppTest {

    @Test
    void set1Challenge3() {
        var bytes = App.hexToBytes(
                "1b37373331363f78151b7f2b783431333d78397828372d363c78373e783a393b3736");

        for (int i = 0; i < 255; i++) {
            var x = bytes.clone();
            for (int j = 0; j < bytes.length; j++) {
                x[j] = (byte) (x[j] ^ i);
            }
            var s = new String(x);
            if (App.maybeEnglish(s)) {
                System.out.println(s);
            }
        }

        // todo: Add assertions here for the challenge and number of successful matches
        fail();
    }
}