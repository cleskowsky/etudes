import java.util.Map;

public class Day6 {
    public static void main(String[] args) {
        System.out.println("hello, world");
    }

    // Lab
    record Point(int x, int y) {
    }

    record Lab(Map<Point, Boolean> floor) {
        public boolean isBlocked(int x, int y) {
            return floor.get(new Point(x, y));
        }
    }

    // Obstacle
    // Guard
    // Tracker
}
