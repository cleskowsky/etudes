package a;

import java.nio.file.Files;
import java.nio.file.Path;

public class Day11 {
    public static void main(String[] args) {

//        String inputFile = "inputs/11sample.txt";
        String inputFile = "inputs/11.txt";

        char[][] gen = Input(inputFile);
        int h = gen.length;
        int w = gen[0].length;

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
            for (int j = 0; j < h; j++) {
                for (int i = 0; i < w; i++) {
                    int sum = neighboursParta(gen, neighbours, j, i);
                    if (gen[j][i] == 'L' && sum == 0) {
                        newGen[j][i] = '#';
                        changed = true;
                    } else if (gen[j][i] == '#' && sum >= 4) {
                        newGen[j][i] = 'L';
                        changed = true;
                    } else {
                        newGen[j][i] = gen[j][i];
                    }
                }
            }
            gen = newGen;
        }
        System.out.println(countOccupiedSeats(gen));

        // Part b

        gen = Input(inputFile);

        changed = true;
        while (changed) {
            changed = false;

            // We'll have to keep evolving new generations until
            // they stabilized
            char[][] newGen = new char[h][w];
            for (int j = 0; j < h; j++) {
                for (int i = 0; i < w; i++) {
                    int sum = neighboursPartb(gen, neighbours, j, i);
                    if (gen[j][i] == 'L' && sum == 0) {
                        newGen[j][i] = '#';
                        changed = true;
                    } else if (gen[j][i] == '#' && sum >= 5) {
                        newGen[j][i] = 'L';
                        changed = true;
                    } else {
                        newGen[j][i] = gen[j][i];
                    }
                }
            }
            gen = newGen;
        }
        System.out.println(countOccupiedSeats(gen));
    }

    private static int neighboursPartb(char[][] gen, int[][] neighbours, int j, int i) {
        int sum = 0;
        for (int[] n : neighbours) {
            // We'll proceed in the neighbour direction until we find
            // a seat (occupied or not) or fall off the map
            int k = 1;
            boolean seatFound = false;
            while (!seatFound) {
                int y = j + n[0] * k;
                int x = i + n[1] * k;
                if ((y < 0 || y >= gen.length) || (x < 0 || x >= gen[0].length)) {
                    break;
                }
                switch (gen[y][x]) {
                    case 'L':
                        seatFound = true;
                        break;
                    case '#':
                        seatFound = true;
                        sum++;
                        break;
                    default:
                        k++;
                }
            }
        }
        return sum;
    }

    private static int neighboursParta(char[][] gen, int[][] neighbours, int j, int i) {
        int sum = 0;
        for (int[] n : neighbours) {
            int y = j + n[0];
            int x = i + n[1];
            if ((y < 0 || y >= gen.length) || (x < 0 || x >= gen[0].length)) {
                continue;
            }
            if (gen[j + n[0]][i + n[1]] == '#') {
                sum++;
            }
        }
        return sum;
    }

    private static int countOccupiedSeats(char[][] gen) {
        int sum = 0;
        for (int j = 0; j < gen.length; j++) {
            for (int i = 0; i < gen[0].length; i++) {
                if (gen[j][i] == '#') {
                    sum++;
                }
            }
        }
        return sum;
    }

    private static void printGen(char[][] gen) {
        for (char[] l : gen) {
            System.out.println(l);
        }
        System.out.println();
    }

    private static char[][] Input(String fileName) {
        String[] lines = new String[]{""};
        try {
            lines = Files.readString(Path.of(fileName)).split("\\n");
        } catch (Exception e) {
            e.printStackTrace();
        }
        char[][] gen = new char[lines.length][];
        for (int i = 0; i < lines.length; i++) {
            gen[i] = lines[i].toCharArray();
        }
        return gen;
    }
}
