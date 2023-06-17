package util;

public enum Heading {
    N(new Point(0, -1)),
    E(new Point(1, 0)),
    S(new Point(0, 1)),
    W(new Point(-1, 0));

    public Point p;

    Heading(Point p) {
        this.p = p;
    }
}
