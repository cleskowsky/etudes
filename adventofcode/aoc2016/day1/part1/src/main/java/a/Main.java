package a;

// I'm face north initially and then after a series
// of movements end up some distance away from my
// starting position
//
// That distance should be my answer |x| + |y|

// Part 1: 299

public class Main {

    private int[][] headings = {
            {0, 1},  // North
            {1, 0},  // East
            {0, -1}, // South
            {-1, 0}  // West
    };

    private int heading; // Start by facing north
    private int x, y;    // 0, 0

    private void turn(char dir) {
        if (dir == 'L') {
            heading = (heading > 0) ? heading - 1 : 3;
        } else {
            heading = (heading < 3) ? heading + 1 : 0;
        }
    }

    /**
     * Move forward b blocks
     */
    private void step(int b) {
        int[] h = headings[heading];
        for (int i = 1; i <= b; i++) {
            x += h[0];
            y += h[1];
        }
    }

    public static void main(String[] args) {
        String in = "L1, L3, L5, L3, R1, L4, L5, R1, R3, L5, R1, L3, L2, L3, R2, R2, L3, L3, R1, L2, R1, L3, L2, R4, R2, L5, R4, L5, R4, L2, R3, L2, R4, R1, L5, L4, R1, L2, R3, R1, R2, L4, R1, L2, R3, L2, L3, R5, L192, R4, L5, R4, L1, R4, L4, R2, L5, R45, L2, L5, R4, R5, L3, R5, R77, R2, R5, L5, R1, R4, L4, L4, R2, L4, L1, R191, R1, L1, L2, L2, L4, L3, R1, L3, R1, R5, R3, L1, L4, L2, L3, L1, L1, R5, L4, R1, L3, R1, L2, R1, R4, R5, L4, L2, R4, R5, L1, L2, R3, L4, R2, R2, R3, L2, L3, L5, R3, R1, L4, L3, R4, R2, R2, R2, R1, L4, R4, R1, R2, R1, L2, L2, R4, L1, L2, R3, L3, L5, L4, R4, L3, L1, L5, L3, L5, R5, L5, L4, L2, R1, L2, L4, L2, L4, L1, R4, R4, R5, R1, L4, R2, L4, L2, L4, R2, L4, L1, L2, R1, R4, R3, R2, R2, R5, L1, L2";
        String[] instructions = in.split(", ");

        Main m = new Main();
        for (String instr: instructions) {
            m.turn(instr.charAt(0));
            m.step(Integer.parseInt(instr.substring(1)));
        }
        System.out.println(m);
    }

    @Override
    public String toString() {
        return "Main{" +
                "heading=" + heading +
                ", x=" + x +
                ", y=" + y +
                '}';
    }
}
