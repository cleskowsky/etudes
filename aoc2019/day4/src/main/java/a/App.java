package a;

import java.util.stream.IntStream;

/**
 * Hello world!
 */
public class App {
    public boolean hasSameAdjacentDigits(char[] digits) {
        for (int i = 0; i < digits.length - 1; i++) {
            if (digits[i] == digits[i + 1]) {
                return true;
            }
        }
        return false;
    }

    public boolean hasIncreasingDigits(char[] digits) {
        for (int i = 0; i < digits.length - 1; i++) {
            if (digits[i] > digits[i + 1]) {
                return false;
            }
        }
        return true;
    }

    public boolean isCandidatePassword(int n) {
        char[] digits = Integer.valueOf(n).toString().toCharArray();
        return hasSameAdjacentDigits(digits) && hasIncreasingDigits(digits);
    }

    public static void main(String[] args) {
        System.out.println("Hello World!");

        // My range 168630-718098
        App a = new App();
        long n = IntStream.rangeClosed(168630, 718098)
                .filter(a::isCandidatePassword)
                .count();
        System.out.println(n);
    }
}
