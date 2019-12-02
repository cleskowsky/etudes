import java.util.Arrays;

public class Computer {
    /**
     * Return value in position 0 after computing
     * program instructions
     *
     * @param prg A comma separated list of instructions with their arguments
     * @return program[0]
     */
    public int compute(int[] prg) {
        int pc = 0;
        while (true) {
            int op = prg[pc];
            switch (op) {
                case 1 -> {
                    // add
                    int arg1 = prg[pc + 1];
                    int arg2 = prg[pc + 2];
                    int out = prg[pc + 3];
                    prg[out] = prg[arg1] + prg[arg2];
                }
                case 2 -> {
                    // mult
                    int arg1 = prg[pc + 1];
                    int arg2 = prg[pc + 2];
                    int out = prg[pc + 3];
                    prg[out] = prg[arg1] * prg[arg2];
                }
                case 99 -> {
                    // exit
                    System.out.println("Final prg: " + Arrays.toString(prg));
                    return prg[0];
                }
            }
            pc += 4;
        }
    }

    public static void main(String[] args) {
        // Part 1
        // There are 3 instructions:
        // - add (1)
        // - multiply, and (2)
        // - stop (99)
        Computer c = new Computer();

        // Test input
        int[] prg1 = {1, 0, 0, 0, 99};
        System.out.format("Expect: 2, was: %d%n", c.compute(prg1));
        int[] prg2 = {1, 1, 1, 4, 99, 5, 6, 0, 99};
        System.out.format("Expect: 30, was: %d%n", c.compute(prg2));
        int[] prg3 = {2, 4, 4, 5, 99, 0};
        System.out.format("Expect: 2, was: %d%n", c.compute(prg3));
        int[] prg4 = {2, 3, 0, 3, 99};
        System.out.format("Expect: 2, was: %d%n", c.compute(prg4));

        // Puzzle input
//        int[] prg5 = {1, 0, 0, 3, 1, 1, 2, 3, 1, 3, 4, 3, 1, 5, 0, 3, 2, 1, 6, 19, 1, 9, 19, 23, 1, 6, 23, 27, 1, 10, 27, 31, 1, 5, 31, 35, 2, 6, 35, 39, 1, 5, 39, 43, 1, 5, 43, 47, 2, 47, 6, 51, 1, 51, 5, 55, 1, 13, 55, 59, 2, 9, 59, 63, 1, 5, 63, 67, 2, 67, 9, 71, 1, 5, 71, 75, 2, 10, 75, 79, 1, 6, 79, 83, 1, 13, 83, 87, 1, 10, 87, 91, 1, 91, 5, 95, 2, 95, 10, 99, 2, 9, 99, 103, 1, 103, 6, 107, 1, 107, 10, 111, 2, 111, 10, 115, 1, 115, 6, 119, 2, 119, 9, 123, 1, 123, 6, 127, 2, 127, 10, 131, 1, 131, 6, 135, 2, 6, 135, 139, 1, 139, 5, 143, 1, 9, 143, 147, 1, 13, 147, 151, 1, 2, 151, 155, 1, 10, 155, 0, 99, 2, 14, 0, 0};
        // 2106513 is too low :/ Oh bother I forgot to twiddle the numbers !! :fistshake:

        // With modifications
        int[] prg5 = {1, 12, 2, 3, 1, 1, 2, 3, 1, 3, 4, 3, 1, 5, 0, 3, 2, 1, 6, 19, 1, 9, 19, 23, 1, 6, 23, 27, 1, 10, 27, 31, 1, 5, 31, 35, 2, 6, 35, 39, 1, 5, 39, 43, 1, 5, 43, 47, 2, 47, 6, 51, 1, 51, 5, 55, 1, 13, 55, 59, 2, 9, 59, 63, 1, 5, 63, 67, 2, 67, 9, 71, 1, 5, 71, 75, 2, 10, 75, 79, 1, 6, 79, 83, 1, 13, 83, 87, 1, 10, 87, 91, 1, 91, 5, 95, 2, 95, 10, 99, 2, 9, 99, 103, 1, 103, 6, 107, 1, 107, 10, 111, 2, 111, 10, 115, 1, 115, 6, 119, 2, 119, 9, 123, 1, 123, 6, 127, 2, 127, 10, 131, 1, 131, 6, 135, 2, 6, 135, 139, 1, 139, 5, 143, 1, 9, 143, 147, 1, 13, 147, 151, 1, 2, 151, 155, 1, 10, 155, 0, 99, 2, 14, 0, 0};
        System.out.format("Expect: 6087827, was: %d%n", c.compute(prg5));

    }
}
