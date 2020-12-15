package a;

import java.nio.file.Files;
import java.nio.file.Path;

public class Day12 {
    enum Heading {
        NORTH(0, 1),
        EAST(1, 0),
        SOUTH(0, -1),
        WEST(-1, 0);

        private int dx, dy;

        Heading(int dx, int dy) {
            this.dx = dx;
            this.dy = dy;
        }
    }

    public static void main(String[] args) throws Exception {
        // Input
//        String s = Files.readString(Path.of("inputs/12sample.txt"));
        String s = Files.readString(Path.of("inputs/12.txt"));

        // Parta
        int x = 0;
        int y = 0;

        Heading[] headings = new Heading[]{
                Heading.NORTH,
                Heading.EAST,
                Heading.SOUTH,
                Heading.WEST
        };
        int facing = 1;

        for (String instr : s.split("\\n")) {
            String move = instr.substring(0, 1);
            int by = Integer.parseInt(instr.substring(1));

            // Are we changing our facing direction?
            if (move.equals("R") || move.equals("L")) {
                facing = turn(facing, move, by);
                continue;
            }

            // Move in the facing direction or ...
            Heading h;
            if (move.equals("F")) {
                h = headings[facing];
            } else {
                h = getHeading(move);
            }
            x += h.dx * by;
            y += h.dy * by;
        }
        System.out.println(Math.abs(x) + Math.abs(y));

        // Partb
    }

    public static Heading getHeading(String s) {
        switch (s) {
            case "N":
                return Heading.NORTH;
            case "E":
                return Heading.EAST;
            case "S":
                return Heading.SOUTH;
            case "W":
                return Heading.WEST;
            default:
                throw new IllegalArgumentException("Bad heading: " + s);
        }
    }

    public static int turn(int facing, String dir, int by) {
        if (dir.equals("R")) {
            facing = (facing + by / 90) % 4;
        } else {
            facing -= by / 90;
            if (facing < 0) {
                facing = 4 + facing;
            }
        }
        return facing;
    }
}
