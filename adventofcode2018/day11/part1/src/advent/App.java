package advent;

import java.util.ArrayList;
import java.util.List;

public class App {
    private int[][] grid;
    private int serialNumber;

    public static final int MAX_X = 300;
    public static final int MAX_Y = 300;

    /**
     * Initialize grid with serial number
     *
     * @param serialNumber Grid serial number
     */
    public App(int serialNumber) {
        this.serialNumber = serialNumber;
        this.grid = new int[MAX_Y][MAX_X];
        for (int y = 0; y < this.grid.length; y++) {
            for (int x = 0; x < this.grid[y].length; x++) {
                // 1-based names for cells. joy!
                this.grid[y][x] = powerLevel(x + 1, y + 1);
            }
        }
    }

    private int powerLevel(int gridX, int gridY) {
        int rackId = gridX + 10;
        int powerLevel = rackId * (gridY);
        powerLevel += this.serialNumber;
        powerLevel *= rackId;
        powerLevel /= 100; // dump 2 right most digits
        powerLevel %= 10;
        powerLevel -= 5;
        return powerLevel;
    }

    private int totalPower(int x, int y) {
        // total power is my power level
        // plus power levels of my neighbours
        int sum = grid[y][x];

        List<Integer> levels = new ArrayList<>();
        try {
            levels = neighbours(x, y);
        } catch (ArrayIndexOutOfBoundsException e) {
            // this is fine
            // total power isn't defined for edge rows in the grid
            sum = 0;
        }

        for (int n : levels) {
            sum += n;
        }
        return sum;
    }

    private List<Integer> neighbours(int x, int y) {
        int[][] offsets = {
                {-1, -1}, {-1, 0}, {-1, 1},
                {0, -1}, /* 0,0 */ {0, 1},
                {1, -1}, {1, 0}, {1, 1}
        };
        List<Integer> neighbours = new ArrayList<>();
        for (int[] offset : offsets) {
            int dy = offset[0], dx = offset[1];
            neighbours.add(grid[y + dy][x + dx]);
        }
        return neighbours;
    }

    private void findFuelCellWithLargestTotalPower() {
        int maxP = 0;
        int[] cell = new int[2];
        for (int y = 0; y < grid.length; y++) {
            for (int x = 0; x < grid[y].length; x++) {
                int p = totalPower(x, y);
                if (p > maxP) {
                    maxP = p;
                    cell[0] = x;
                    cell[1] = y;
                }
            }
        }
        System.out.printf("Largest total power: %d, cell: (%d, %d)\n", maxP, cell[0], cell[1]);
    }

    public static void main(String[] args) {
        System.out.println("hello, world");

        // test powerLevel
        System.out.println("A bit of power level computer testing");
        App a = new App(8);
        System.out.println(a.powerLevel(3, 5));
        a = new App(57);
        System.out.println(a.powerLevel(122, 79));

        // test grid serial number: 18 (largest total power 3x3 grid: 33,45)
        System.out.println("A test grid with known largest 3x3 total power matrix");
        a = new App(18);
        a.findFuelCellWithLargestTotalPower();

        // test grid serial number: 42 (largest total power 3x3 grid: 21,61)
        System.out.println("A test grid with known largest 3x3 total power matrix");
        a = new App(42);
        a.findFuelCellWithLargestTotalPower();

        // my grid serial number: 3628
        System.out.println("A grid with my serial number");
        a = new App(3628);
        a.findFuelCellWithLargestTotalPower();
    }
}
