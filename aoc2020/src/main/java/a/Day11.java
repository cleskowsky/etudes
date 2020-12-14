package a;

import java.nio.file.Files;
import java.nio.file.Path;

public class Day11 {
    public static void main(String[] args) throws Exception {

        // Input
//        String[] lines = Files.readString(Path.of("inputs/11sample.txt")).split("\\n");
        String[] lines = Files.readString(Path.of("inputs/11.txt")).split("\\n");
        int w = lines[0].length();
        int h = lines.length;
        char[][] gen = new char[h][w];
        for (int i = 0; i < h; i++) {
            String l = lines[i];
            for (int j = 0; j < w; j++) {
                gen[i][j] = l.charAt(j);
            }
        }

        // Part a
        int[][] neighbours = new int[][]{
                // y, x
                {-1, -1}, {-1, 0}, {-1, 1},
                {0, -1}, /* myself */ {0, 1},
                {1, -1}, {1, 0}, {1, 1}
        };

        boolean changed = true;
        while (changed) {
            changed = false;

            // We'll have to keep evolving new generations until
            // they stabilized
            char[][] newGen = new char[h][w];
            for (int i = 0; i < h; i++) {
                for (int j = 0; j < w; j++) {
                    int sum = 0;
                    for (int[] n : neighbours) {
                        if (i + n[0] < 0 || i + n[0] >= h) {
                            continue;
                        }
                        if (j + n[1] < 0 || j + n[1] >= w) {
                            continue;
                        }
                        if (gen[i + n[0]][j + n[1]] == '#') {
                            sum++;
                        }
                    }
                    if (gen[i][j] == 'L' && sum == 0) {
                        newGen[i][j] = '#';
                        changed = true;
                    } else if (gen[i][j] == '#' && sum >= 4) {
                        newGen[i][j] = 'L';
                        changed = true;
                    } else {
                        newGen[i][j] = gen[i][j];
                    }
                }
            }
            gen = newGen;

//            for (char[] l : gen) {
//                System.out.println(l);
//            }
//            System.out.println();
        }

        int occupiedSeats = 0;
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                if (gen[i][j] == '#') {
                    occupiedSeats++;
                }
            }
        }
        System.out.println(occupiedSeats);

        // Part b
    }
}
