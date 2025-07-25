import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day15Test {

    /*
     * when the robot moves into a space with a box
     * the box moves 1 space in the direction the robot
     * just moved in. if there's a box in that square,
     * *that* box moves too similarly. no movement happens
     * when a wall is in the way.
     *
     * i'll remember the boxes that should move if i'm able
     * to find an open floor tile. if i hit a wall, throw away
     * that box list and go to the next move.
     */

    @Test
    void parseInput() {
        var parseResult = parseInput("inputs/day15.txt");
        assertEquals(new Tile(2, 2), parseResult.wh().robot);
        assertEquals(15, parseResult.moves().size());
    }

    static class Warehouse {
        Tile robot;
        Floor floor;

        public Warehouse(Tile robot, Floor floor) {
            this.robot = robot;
            this.floor = floor;
            floor.put(robot, '@');
        }

        @Override
        public String toString() {
            return "Warehouse{" +
                    "robot=" + robot +
                    ", floor=\n" + floor +
                    '}';
        }
    }

    ParseResult parseInput(Path p) {
        try {
            return parseInput(Files.readString(p));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    ParseResult parseInput(String s) {
        var split = s.split("\n\n");
        var wh = parseWarehouse(split[0]);
        var moves = parseMoves(split[1]);
        return new ParseResult(wh, moves);
    }

    /*
     * Expect:
     *
     * ########
     * #..O.O.#
     * ##@.O..#
     * #...O..#
     * #.#.O..#
     * #...O..#
     * #......#
     * ########
     */
    Warehouse parseWarehouse(String s) {

        var rows = s.split("\n");
        int maxX = rows[0].length(); // string
        int maxY = rows.length; // array

        Warehouse wh = null;
        var f = new Floor(maxX, maxY);

        for (int y = 0; y < rows.length; y++) {
            var row = rows[y];
            for (int x = 0; x < row.length(); x++) {
                var c = row.charAt(x);

                if ("#O@.".indexOf(c) == -1) {
                    throw new RuntimeException("Invalid character: " + c);
                }

                f.put(new Tile(x, y), c);
                if (c == '@') {
                    wh = new Warehouse(new Tile(x, y), f);
                }
            }
        }

        if (wh == null) {
            throw new RuntimeException("Couldn't parse warehouse");
        }

        return wh;
    }

    /*
     * Expect:
     *
     * <^^>>>vv<v>>v<<
     */
    List<Dir> parseMoves(String s) {

        var val = new ArrayList<Dir>();

        for (char c : s.toCharArray()) {
            switch (c) {
                case '^' -> val.add(Dir.UP);
                case '>' -> val.add(Dir.RIGHT);
                case 'v' -> val.add(Dir.DOWN);
                case '<' -> val.add(Dir.LEFT);
                case '\n' -> {
                }
                default -> throw new RuntimeException("Bad direction: " + c);
            }
        }
        return val;
    }

    record ParseResult(Warehouse wh, List<Dir> moves) {
    }

    enum Dir {
        UP(0, -1),
        RIGHT(1, 0),
        DOWN(0, 1),
        LEFT(-1, 0);

        public final int x;
        public final int y;

        Dir(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static class Floor extends HashMap<Tile, Character> {

        int maxX;
        int maxY;

        public Floor(int maxX, int maxY) {
            super();
            this.maxX = maxX;
            this.maxY = maxY;
        }

        boolean isEmpty(Tile t) {
            return get(t) == '.';
        }

        @Override
        public String toString() {
            var val = new StringBuilder();
            for (int y = 0; y < maxY; y++) {
                for (int x = 0; x < maxX; x++) {
                    val.append(get(new Tile(x, y)));
                }
                val.append("\n");
            }
            return val.toString();
        }
    }

    record Tile(int x, int y) {
    }

    /*
     * move robot:
     *  if tile is empty, move
     *  if tile is wall, skip
     *  res = if tile is box, move box
     *  if res, move robot
     */

    @Test
    void sample() {

        // var res = parseInput(Path.of("inputs/day15.txt"));
        // var res = parseInput(Path.of("inputs/day15_2.txt"));

        // part a
        var res = parseInput(Path.of("inputs/day15_3.txt"));

        var wh = res.wh();
        var moves = res.moves();

        // warehouse before any moves
        // System.out.println(wh);

        moves.stream().forEach(m -> {
            // System.out.println("\nmoving: " + m);
            move(wh.robot, m, wh);
            // System.out.println(wh);
        });

        // assertEquals(2028, sumGpsCoords(wh.floor));
        assertEquals(1568399, sumGpsCoords(wh.floor));
    }

    // Moves the thing at tile t (only ever called for boxes, and the robot)
    void move(Tile from, Dir dir, Warehouse wh) {

        var floor = wh.floor;

        var to = new Tile(from.x() + dir.x, from.y() + dir.y);
        var c = floor.get(to);

        // move adjacent tile
        if (c == '.') {
            /*
             * empty
             * move whatever was passed in
             * we will only ever see a robot
             * or box here
             */
            move(from, to, wh);
        } else if (c == 'O') {
            // try moving box
            move(to, dir, wh);
        }

        // move myself
        if (floor.isEmpty(to)) {
            move(from, to, wh);
        }
    }

    void move(Tile from, Tile to, Warehouse wh) {

        var floor = wh.floor;
        if (!floor.isEmpty(to)) {
            throw new RuntimeException("Bad move: from=" + from + ", to=" + to);
        }

        floor.put(to, floor.get(from));
        floor.put(from, '.');

        if (from == wh.robot) {
            wh.robot = to;
        }
    }

    @Test
    void move() {
        String s = """
                @.#
                O.#
                ..#
                ###
                
                v""";

        var res = parseInput(s);
        var wh = res.wh();
        var moves = res.moves();
        var floor = wh.floor;

        move(wh.robot, moves.getFirst(), wh);
        // robot should move down 1 tile
        assertEquals(new Tile(0, 1), wh.robot);
        // and so should the box
        assertEquals('O', floor.get(new Tile(0, 2)));

        // i can't move the robot / box again because
        // i've hit a wall ...
        move(wh.robot, moves.getFirst(), wh);
        assertEquals(new Tile(0, 1), wh.robot);
        assertEquals('O', floor.get(new Tile(0, 2)));
    }

    long sumGpsCoords(Floor f) {
        long val = 0;
        for (int y = 0; y < f.maxY; y++) {
            for (int x = 0; x < f.maxX; x++) {
                var c = f.get(new Tile(x, y));
                if (c == 'O') {
                    val += 100L * y + x;
                }
            }
        }
        return val;
    }
}
