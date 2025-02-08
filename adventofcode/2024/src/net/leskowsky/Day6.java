package net.leskowsky;

import lombok.Data;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Day6 {
    public static void main(String[] args) {
    }

    // Lab
    static record Lab(Map<Point, Boolean> floor) {
        public boolean isBlocked(int x, int y) {
            return floor.get(new Point(x, y));
        }

        public boolean contains(Point point) {
            return floor.get(point) != null;
        }
    }

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

    static record SeenFacing(Point pos, Direction dir) {
    }

    // Guard
    @Data
    static class Guard {
        Point pos;
        Direction dir;

        Set<Point> seen;
        boolean leftLab;

        Set<SeenFacing> seenFacings;
        boolean looped;

        public Guard(Point pos, Direction dir) {
            this.pos = pos;
            this.dir = dir;

            seen = new HashSet<>();
            seen.add(pos);
            leftLab = false;

            seenFacings = new HashSet<>();
            looped = false;
        }

        public void step(Lab lab) {
            var next = new Point(pos.x() + dir.x, pos.y() + dir.y);

            if (lab.contains(next)) {
                if (lab.isBlocked(next.x(), next.y())) {
                    turn();
                    next = new Point(pos.x() + dir.x, pos.y() + dir.y);
                }

                var seenFacing = new SeenFacing(next, dir);
                if (seenFacings.contains(seenFacing)) {
                    looped = true;
                } else {
                    seenFacings.add(seenFacing);
                }

                pos = next;
                seen.add(next);
            } else {
                leftLab = true;
            }
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
            }
        }
    }
}
