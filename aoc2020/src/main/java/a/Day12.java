package a;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Day12 {
    enum Heading {
        NORTH(0, 1),
        EAST(1, 0),
        SOUTH(0, -1),
        WEST(-1, 0);

        int x, y;

        Heading(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public static Heading getHeading(int n) {
            String[] headings = new String[]{"N", "E", "S", "W"};
            return getHeading(headings[n]);
        }

        public static Heading getHeading(String s) {
            switch (s) {
                case "N":
                    return NORTH;
                case "E":
                    return EAST;
                case "S":
                    return SOUTH;
                case "W":
                    return WEST;
                default:
                    throw new IllegalArgumentException("Invalid heading: " + s);
            }
        }
    }

    static class Move {
        String way;
        int by;

        public Move(String way, int by) {
            this.way = way;
            this.by = by;
        }
    }

    public static void main(String[] args) throws Exception {
        var moves = Input("inputs/12.txt");

        // Parta
        int x = 0;
        int y = 0;
        int facing = 1;

        for (Move m : moves) {
            switch (m.way) {
                case "N":
                case "E":
                case "S":
                case "W":
                    Heading h = Heading.getHeading(m.way);
                    x += m.by * h.x;
                    y += m.by * h.y;
                    break;
                case "F":
                    h = Heading.getHeading(facing);
                    x += m.by * h.x;
                    y += m.by * h.y;
                    break;
                case "L":
                    facing -= m.by / 90;
                    if (facing < 0) {
                        facing = 4 + facing;
                    }
                    break;
                case "R":
                    facing = (facing + m.by / 90) % 4;
                    break;
                default:
                    throw new IllegalArgumentException("Invalid heading: " + m.way);
            }
        }
        System.out.println(Math.abs(x) + Math.abs(y));

        // Partb
        int[] waypoint = new int[]{10, 1};
        int[] ship = new int[]{0, 0};
        for (Move m : moves) {
            move(ship, waypoint, m);
        }
        System.out.println(Math.abs(ship[0]) + Math.abs(ship[1]));
    }

    public static void move(int[] ship, int[] wayPoint, Move move) {
        switch (move.way) {
            case "N":
            case "E":
            case "S":
            case "W":
                Heading h = Heading.getHeading(move.way);
                wayPoint[0] += move.by * h.x;
                wayPoint[1] += move.by * h.y;
                break;
            case "L":
                int n = move.by / 90;
                for (int i = 0; i < n; i++) {
                    int x = -1 * wayPoint[1];
                    int y = wayPoint[0];
                    wayPoint[0] = x;
                    wayPoint[1] = y;
                }
                break;
            case "R":
                n = move.by / 90;
                for (int i = 0; i < n; i++) {
                    int x = wayPoint[1];
                    int y = -1 * wayPoint[0];
                    wayPoint[0] = x;
                    wayPoint[1] = y;
                }
                break;
            case "F":
                for (int i = 0; i < move.by; i++) {
                    ship[0] += wayPoint[0];
                    ship[1] += wayPoint[1];
                }
                break;
        }
    }

    public static List<Move> Input(String fname) {
        String s = "";
        try {
            s = Files.readString(Path.of(fname));
        } catch (IOException e) {
            e.printStackTrace();
        }

        var l = new ArrayList<Move>();
        for (String instr : s.split("\\n")) {
            l.add(new Move(
                    instr.substring(0, 1),
                    Integer.parseInt(instr.substring(1)))
            );
        }

        return l;
    }
}
