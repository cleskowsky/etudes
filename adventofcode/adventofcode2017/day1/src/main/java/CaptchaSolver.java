public class CaptchaSolver {
    private int addIfSame(int sum, int x, int y) {
        if (x == y) {
            sum += x;
        }
        return sum;
    }

    /**
     * Solver 1 is a sum of certain digits in a string
     * that satisfy my captcha criteria.
     * <p>
     * Walk s. If the digit I'm at is equal to the next,
     * add it to my tally. (The last digit is compared
     * with the first.)
     *
     * @param s A string of digits
     * @return Sum of digits that satisfy captcha criteria
     */
    public int sameNextDigit(String s) {
        int sum = 0;
        for (int i = 0; i < s.length(); i++) {
            int j = (i + 1) % s.length();
            sum = addIfSame(
                    sum,
                    Character.getNumericValue(s.charAt(i)),
                    Character.getNumericValue(s.charAt(j))
            );
        }
        return sum;
    }

    /**
     * Solver 2 is similar to 1 but this time we compare
     * our current digit with one that is halfway around
     * s.
     * <p>
     * As in 1, s is a string that wraps around.
     */
    public int sameHalfwayDigit(String s) {
        int sum = 0;
        for (int i = 0; i < s.length(); i++) {
            int j = (i + s.length() / 2) % s.length();
            sum = addIfSame(
                    sum,
                    Character.getNumericValue(s.charAt(i)),
                    Character.getNumericValue(s.charAt(j))
            );
        }
        return sum;
    }
}
