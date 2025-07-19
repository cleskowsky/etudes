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
        assertEquals(new Robot(2, 2), parseResult.wh().r);
        assertEquals(15, parseResult.moves().size());
    }

    static class Warehouse {
        Robot r;
        Floor f;

        public Warehouse(Robot r, Floor f) {
            this.r = r;
            this.f = f;
        }
    }

    ParseResult parseInput(String s) {
        try {
            var split = Files.readString(Path.of(s)).split("\n\n");
            var wh = parseWarehouse(split[0]);
            var moves = parseMoves(split[1]);
            return new ParseResult(wh, moves);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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
                switch (c) {
                    case '#' -> f.put(new Tile(x, y), new Wall());

                    case 'O' -> f.put(new Tile(x, y), new Box());

                    case '@' -> {
                        if (wh != null) {
                            throw new RuntimeException("More than 1 robot found");
                        }
                        wh = new Warehouse(new Robot(x, y), f);
                    }

                    case '.' -> {
                    }

                    default -> throw new RuntimeException("Bad tile: " + c);
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

    record Robot(Tile t) {
        public Robot(int x, int y) {
            this(new Tile(x, y));
        }
    }

    record Box() {
    }

    record Wall() {
    }

    static class Floor extends HashMap<Tile, Object> {

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
        move(wh.r, d, wh);
    }

    void move(Robot r, Dir d, Warehouse wh) {

        var adj = new Tile(r.t().x() + d.x, r.t().y() + d.y);

        switch (wh.f.get(adj)) {
            case Box b -> {
                // try moving box
            }

            case Wall w -> {
                // do nothing
                // can't move in this case
            }

            default -> {
                // empty tile
                // move the robot
                wh.r = new Robot(adj);
            }
        }
    }
}
