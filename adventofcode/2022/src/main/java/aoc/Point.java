package aoc;

import aoc.Day8.HEADING;

public record Point(int x, int y) {
    public Point plus(HEADING h) {
        return new Point(x + h.p.x, y + h.p.y);
    }
}
