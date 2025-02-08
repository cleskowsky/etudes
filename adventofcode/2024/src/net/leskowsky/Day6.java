package net.leskowsky;

import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Day6 {
    public static void main(String[] args) {
    }

    // Lab
    static record Lab(Map<Point, Boolean> floor) {
        public boolean isBlocked(int x, int y) {
            return isBlocked(new Point(x, y));
        }

        public boolean isBlocked(Point p) {
            return floor.get(p);
        }

        public boolean contains(Point point) {
            return floor.get(point) != null;
        }

        // Copy constructor
        public Lab(Lab lab) {
            this(new HashMap<>());
            for (Point p : lab.floor().keySet()) {
                floor.put(p, lab.floor().get(p));
            }
        }

        /**
         * Return number of blocked squares in lab
         */
        public List<Point> blocked() {
            var result = new ArrayList<Point>();
            for (var entry : floor.entrySet()) {
                if (floor.get(entry.getKey())) {
                    result.add(entry.getKey());
                }
            }
            return result;
        }

        public void obstruct(Point p) {
            floor.put(p, true);
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

        Set<Point> seen = new HashSet<>();
        boolean leftLab;

        Set<SeenFacing> seenFacings = new HashSet<>();
        boolean looped;

        public Guard(Point pos, Direction dir) {
            this.pos = pos;
            this.dir = dir;
            seen.add(pos);
        }

        public void step(Lab lab) {
            var next = new Point(pos.x() + dir.x, pos.y() + dir.y);

            if (lab.contains(next)) {
                while (true) {
                    if (lab.isBlocked(next.x(), next.y())) {
                        turn();
                        next = new Point(pos.x() + dir.x, pos.y() + dir.y);
                    } else {
                        break;
                    }
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
