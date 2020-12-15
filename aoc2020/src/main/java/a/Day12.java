package a;

import java.nio.file.Files;
import java.nio.file.Path;

public class Day12 {
    public static void main(String[] args) throws Exception {
        // Input
//        String s = Files.readString(Path.of("inputs/12sample.txt"));
        String s = Files.readString(Path.of("inputs/12.txt"));

        // Parta
        int x = 0;
        int y = 0;

        int[][] headings = new int[][]{
                {0, 1},  // North
                {1, 0},  // East
                {0, -1}, // South
                {-1, 0}  // West
        };
        int facing = 1;  // East

        for (String instr : s.split("\\n")) {
            String move = instr.substring(0, 1);
            int by = Integer.parseInt(instr.substring(1));

            // Are we changing our facing direction?
            if (move.equals("L") || move.equals("R")) {
                facing = turn(facing, move.equals("L"), by);
                continue;
            }

            // Move in the facing direction or ...
            int[] h;
            if (move.equals("F")) {
                h = headings[facing];
            } else {
                switch (move) {
                    case "N":
                        h = headings[0];
                        break;
                    case "E":
                        h = headings[1];
                        break;
                    case "S":
                        h = headings[2];
                        break;
                    case "W":
                        h = headings[3];
                        break;
                    default:
                        throw new RuntimeException("Invalid move: " + move);
                }
            }
            x += h[0] * by;
            y += h[1] * by;
        }
        System.out.println(Math.abs(x) + Math.abs(y));

        // Partb
    }

    public static int turn(int facing, boolean left, int by) {
        if (left) {
            facing -= by / 90;
            if (facing < 0) {
                facing = 4 + facing;
            }
        } else {
            facing = (facing + by / 90) % 4;
        }
        return facing;
    }
}
