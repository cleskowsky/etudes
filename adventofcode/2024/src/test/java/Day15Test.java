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
                    throw new RuntimeException("Invalid character" + c);
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

        var res = parseInput("inputs/day15.txt");
        var wh = res.wh();
        var moves = res.moves();

        var d = moves.getFirst();
        move(wh.robot, d, wh);
    }

    void move(Tile t, Dir d, Warehouse wh) {

        var adj = new Tile(t.x() + d.x, t.y() + d.y);
        var c = wh.floor.get(adj);

        if (c == 'O') {
            move(adj, d, wh);
        }

        /*
         * if we're looking at the robot tile,
         * move it if the adj square is empty
         * (it may have moved above)
         */

        if (t.equals(wh.robot)) {
            c = wh.floor.get(adj);
            if (c == '.') {
                wh.floor.put(t, '.');
                wh.floor.put(adj, '@');
                wh.robot = adj;
            }
        }
    }

    @Test
    void move() {
        String s = """
                @.
                
                >""";
        System.out.println(s);
        var res = parseInput(s);
        System.out.println(res);
        var wh = res.wh();
        System.out.println(wh);
        System.out.println(wh.robot);
        System.out.println(wh.floor);
    }
}
