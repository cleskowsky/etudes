package a;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * Lol!
 * <p>
 * Shockingly my solution for part 1 isn't going to work
 */
public class Main {
    private String[][] grid = {
            {".", ".", ".", ".", ".", ".", "."},
            {".", ".", ".", "1", ".", ".", "."},
            {".", ".", "2", "3", "4", ".", "."},
            {".", "5", "6", "7", "8", "9", "."},
            {".", ".", "A", "B", "C", ".", "."},
            {".", ".", ".", "D", ".", ".", "."},
            {".", ".", ".", ".", ".", ".", "."},
    };

    static class Cell {
        private int x, y;

        Cell(int x, int y) {
            this.x = x;
            this.y = y;
        }

        int getX() {
            return x;
        }

        int getY() {
            return y;
        }

        @Override
        public String toString() {
            return "Cell{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }
    }

    /**
     * Returns the next button in my code
     */
    Cell getNextCodeCell(Cell startingFrom, String moves) {
        Cell c = startingFrom;
        System.out.println(c + " " + moves);

        Cell nextKey = new Cell(0, 0);

        for (Character m : moves.toCharArray()) {
            switch (m) {
                case 'U':
                    nextKey = new Cell(c.getX(), c.getY() - 1);
                    break;

                case 'R':
                    nextKey = new Cell(c.getX() + 1, c.getY());
                    break;

                case 'D':
                    nextKey = new Cell(c.getX(), c.getY() + 1);
                    break;

                case 'L':
                    nextKey = new Cell(c.getX() - 1, c.getY());
                    break;
            }
            System.out.println(c + " " + m + " " + nextKey);

            if (grid[nextKey.getY()][nextKey.getX()].equals(".")) {
                continue;
            }

            c = nextKey;
        }

        return c;
    }

    /**
     * Returns my bathroom code
     */
    private String getBathroomCode(List<String> input) {
        // Start at "5"
        Cell c = new Cell(1, 3);

        // The password I'm constructing as I go
        String code = "";

        for (String moves : input) {
            c = getNextCodeCell(c, moves);
            code += grid[c.getY()][c.getX()];
        }

        return code;
    }

    public static void main(String[] args) {
        // This is the puzzle test input
        List<String> input = List.of(
                "ULL",
                "RRDDD",
                "LURDL",
                "UUUUD"
        );

        try {
            input = Files.readAllLines(Paths.get("input.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Main a = new Main();
        System.out.println("Bathroom code: " + a.getBathroomCode(input));
    }
}
