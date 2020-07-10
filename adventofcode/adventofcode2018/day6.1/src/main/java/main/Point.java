package main;

public class Point {
    private int x;
    private int y;
    private char name;

    private static int n;

    public static final Point DOT_POINT = new Point(0,0, '.');

    private Point(int x, int y, char name) {
        this.x = x;
        this.y = y;
        this.name = name;
    }

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
        this.name = (char)('A' + n);
        n++;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public char getName() {
        return name;
    }
}
