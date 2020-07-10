package a;

// I'm face north initially and then after a series
// of movements end up some distance away from my
// starting position
//
// That distance should be my answer |x| + |y|

// Part 2: 181

import java.util.ArrayList;
import java.util.List;

public class Main {

    private int[][] headings = {
            {0, 1},  // North
            {1, 0},  // East
            {0, -1}, // South
            {-1, 0}  // West
    };

    private int heading; // Start by facing north
    private int x, y;    // 0, 0

    // Remember visited locations for part2
    // Which location do I visit twice?
    private List<Block> visited;

    public Main() {
        this.visited = new ArrayList<Block>();
        visited.add(new Block(0, 0));
    }

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

            // I *think* every block is considered visited
            // for the purposes of part 2
            x += h[0];
            y += h[1];

            // Remember visited locations
            Block loc = new Block(x, y);
            if (visited.contains(loc)) {
                System.out.println("x: " + x + " y: " + y);
                System.exit(0);
            }
            visited.add(loc);
        }

    }

    // I haven't written a program in Java in awhile :)
    // For visited location tracking, I need to be able to
    // compare coordinates to determine equality
    //
    // I think a pair of numbers means I need a new tiny type
    //
    // Default equality from Object is an object's "id" only
    // (which is possibly a heap address) ... not good enough
    // for my purpose
    //
    // I wonder if this is necessary ...
    private static class Block {
        private int x, y;

        public Block(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Block block = (Block) o;

            if (x != block.x) return false;
            return y == block.y;
        }

        @Override
        public int hashCode() {
            int result = x;
            result = 31 * result + y;
            return result;
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
