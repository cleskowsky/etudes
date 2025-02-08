package net.leskowsky;

public record Point(int x, int y) {
    public Point(Point p) {
        this(p.x, p.y);
    }
}
