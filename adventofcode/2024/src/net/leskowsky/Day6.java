package net.leskowsky;

import java.util.Map;

public class Day6 {
    public static void main(String[] args) {
        System.out.println("hello, world");
    }

    // Lab
    static record Point(int x, int y) {
    }

    static record Lab(Map<Point, Boolean> floor) {
        public boolean isBlocked(int x, int y) {
            return floor.get(new Point(x, y));
        }
    }

    // Guard
    static class Guard {
        Lab lab;
        Point pos;
        Point dir;

        public Guard(Lab lab) {
            this.lab = lab;
            this.pos = new Point(0, 1);
            this.dir = new Point(0, -1);
        }

        public Point getPos() {
            return pos;
        }

        public Point getDir() {
            return dir;
        }

        public void step() {
            pos = new Point(pos.x() + dir.x(), pos.y() + dir.y());
        }
    }
    // Tracker
}
