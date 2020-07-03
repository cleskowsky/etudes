public class Main {

    /**
     * Counts the number of 1 bits in n's
     * binary representation
     * @param n A number, non-negative
     * @return
     */
    public int countBits(int n) {
        int result = 0;
        while (n > 0) {
            result += n % 2;
            n = n / 2;
        }
        return result;
    }

    public static void main(String[] args) {
        Main m = new Main();

        System.out.println(m.countBits(1));
        System.out.println(m.countBits(2));
        System.out.println(m.countBits(3));
        System.out.println(m.countBits(4));
        System.out.println(m.countBits(1234));
    }
}
