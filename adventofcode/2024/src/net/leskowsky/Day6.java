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
        Direction dir;

        enum Direction {
            UP(0, -1),
            RIGHT(1, 0),
            DOWN(0, 1),
            LEFT(-1, 0);

            final int x;
            final int y;

            Direction(int x, int y) {
                this.x = x;
                this.y = y;
            }
        }

        public Guard(Lab lab) {
            this.lab = lab;
            this.pos = new Point(0, 1);
            this.dir = Direction.UP;
        }

        public Point getPos() {
            return pos;
        }

        public Direction getDir() {
            return dir;
        }

        public void step() {
            var next = new Point(pos.x() + dir.x, pos.y() + dir.y);
            if (lab.isBlocked(next.x(), next.y())) {
                turn();
                next = new Point(pos.x() + dir.x, pos.y() + dir.y);
            }
            pos = next;
        }

        private void turn() {
            switch (dir) {
                case UP:
                    dir = Direction.RIGHT;
                    break;
                case RIGHT:
                    dir = Direction.DOWN;
                    break;
                case DOWN:
                    dir = Direction.LEFT;
                    break;
                case LEFT:
                    dir = Direction.UP;
                    break;
                default:
                    throw new RuntimeException("Bad direction");
            }
        }
    }

    // Tracker
}
