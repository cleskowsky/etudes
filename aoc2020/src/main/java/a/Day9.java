package a;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Day9 {
    public static void main(String[] args) throws Exception {
        List<String> input = Files.readAllLines(Path.of("inputs/9.txt"));
        long[] ciphertext = new long[input.size()];
        for (int i = 0; i < input.size(); i++) {
            ciphertext[i] = Long.parseLong(input.get(i));
        }

        // Part a
        for (int i = 25; i < ciphertext.length; i++) {
            long next = ciphertext[i];
            if (!isValidNext(ciphertext, i-25, i-1, next)) {
                System.out.println(next);
                break;
            }
        }

        // Part b
        long myNumber = 32321523;
        for (int i = 0; i < ciphertext.length; i++) {
            long sum = 0;
            for (int j = i; j < ciphertext.length; j++) {
                sum += ciphertext[j];
                if (sum > myNumber) {
                    break;
                }
                if (sum == myNumber) {
                    System.out.printf("Found! i: %d, j: %d, ct[i]: %d, ct[j]: %d%n",
                            i, j, ciphertext[i], ciphertext[j]);

                    // smallest: 1145730
                    // biggest: 3649251
                    System.out.println(1145730 + 3649251);

                    System.exit(0);
                }
            }
        }
    }

    public static boolean isValidNext(long[] ciphertext, int preambleStart, int preambleEnd, long l) {
        for (int i = preambleStart; i <= preambleEnd; i++) {
            long x = ciphertext[i];
            for (int j = preambleStart; j <= preambleEnd; j++) {
                if (i == j) {
                    continue;
                }
                long y = ciphertext[j];
                if (x == y) {
                    continue;
                }
                if ((x + y) == l) {
                    return true;
                }
            }
        }
        return false;
    }
}
