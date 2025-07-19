import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;

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
        var g = parseInput("inputs/day15.txt");
    }

    record Warehouse(Robot r, Floor f) {
    }

    static final ParseResult ParseFailed = new ParseResult(null, null);

    ParseResult parseInput(String s) {
        try {
            var split = Files.readString(Path.of(s)).split("\n\n");
            Floor f = parseFloor(split[0]);
            List<Move> moves = parseMoves(split[1]);
            return new ParseResult(new Warehouse(new Robot(0, 0), f), moves);
        } catch (IOException e) {
            return ParseFailed;
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
    Floor parseFloor(String s) {
        return null;
    }

    /*
     * Expect:
     *
     * <^^>>>vv<v>>v<<
     */
    List<Move> parseMoves(String s) {
        return null;
    }

    record ParseResult(Warehouse wh, List<Move> moves) {
    }

    record Move(Dir d) {
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

    record Robot(int x, int y) {
    }

    record Box() {
    }

    record Wall() {
    }

    class Floor extends HashMap<Coord, Object> {
    }

    record Coord(int x, int y) {
    }

//    void move(Robot r, Dir d, Warehouse wh) {
//        // if wall, return
//        // if empty, move
//        // if box, move box then move robot
//    }
}
