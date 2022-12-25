package aoc;

public record Point(int x, int y) {

    public Point add(int x, int y) {
        return new Point(this.x + x, this.y + y);
    }

    public Point sub(int x, int y) {
        return new Point(this.x - x, this.y - y);
    }
}
