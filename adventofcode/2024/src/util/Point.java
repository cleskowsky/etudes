package util;

public record Point(int x, int y) {
    public Point(Point p) {
        this(p.x, p.y);
    }

    public Point add(Point p) {
        return new Point(x + p.x, y + p.y);
    }

    public Point sub(Point p) {
        return new Point(x - p.x, y - p.y);
    }
}
