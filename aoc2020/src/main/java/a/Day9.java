package a;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

public class Day9 {
    public static void main(String[] args) throws Exception {
        System.out.println("hello");

        List<String> input = Files.readAllLines(Path.of("inputs/9.txt"));
        long[] ciphertext = new long[input.size()];
        for (int i = 0; i < input.size(); i++) {
            ciphertext[i] = Long.parseLong(input.get(i));
        }
        System.out.println(ciphertext);

        // Part a
        for (int i = 5; i < ciphertext.length; i++) {
            long next = ciphertext[i];
            int preambleStart = i - 5;
            int preambleEnd = i;
            for (int j = preambleStart; j < preambleEnd; j++) {
                long x = ciphertext[j];
                for (int k = preambleStart; k < preambleEnd; k++) {
                    long y = ciphertext[k];
                    if (x == y) {
                        continue;
                    }

                }
            }
        }
    }

    public static boolean isValidNext(long[] preamble, long l) {
        for (int j = 1; j <= 5; j++) {
            long x = preamble[i - j];
            for (int k = 1; k <= 5; k++) {
                if (j == k) {
                    continue;
                }
                long y = preamble[k];
                if (x == y) {
                    continue;
                }
                if ((x + y) == l) {
                    return true;
                }
            }
        }
    }
}
