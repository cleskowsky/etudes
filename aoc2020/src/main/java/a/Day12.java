package a;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Day12 {
    static class Pair {
        int x;
        int y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    enum Heading {
        NORTH(new Pair(0, 1)),
        EAST(new Pair(1, 0)),
        SOUTH(new Pair(0, -1)),
        WEST(new Pair(-1, 0));

        Pair p;

        Heading(Pair p) {
            this.p = p;
        }

        public int getX() {
            return p.x;
        }

        public int getY() {
            return p.y;
        }

        public static Heading getHeading(int n) {
            String[] headings = new String[] { "N", "E", "S", "W" };
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

        int facing = 1;  // East

        for (Move m : moves) {
            switch (m.way) {
                case "N":
                case "E":
                case "S":
                case "W":
                    Heading h = Heading.getHeading(m.way);
                    x += m.by * h.getX();
                    y += m.by * h.getY();
                    break;
                case "F":
                    h = Heading.getHeading(facing);
                    x += m.by * h.getX();
                    y += m.by * h.getY();
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
        Pair waypoint = new Pair(10, 1);
        Pair ship = new Pair(0, 0);
        for (Move a : moves) {
            move(ship, waypoint, a);
        }
        System.out.println(Math.abs(ship.x) + Math.abs(ship.y));
    }

    public static void move(Pair ship, Pair wayPoint, Move move) {
        switch (move.way) {
            case "N":
            case "E":
            case "S":
            case "W":
                Heading h = Heading.getHeading(move.way);
                wayPoint.x += move.by * h.getX();
                wayPoint.y += move.by * h.getY();
                break;
            case "L":
                int n = move.by / 90;
                for (int i = 0; i < n; i++) {
                    int x = -1 * wayPoint.y;
                    int y = wayPoint.x;
                    wayPoint.x = x;
                    wayPoint.y = y;
                }
                break;
            case "R":
                n = move.by / 90;
                for (int i = 0; i < n; i++) {
                    int x = wayPoint.y;
                    int y = -1 * wayPoint.x;
                    wayPoint.x = x;
                    wayPoint.y = y;
                }
                break;
            case "F":
                for (int i = 0; i < move.by; i++) {
                    ship.x += wayPoint.x;
                    ship.y += wayPoint.y;
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
