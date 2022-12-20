import aoc.FileUtils;

import java.util.List;

public class Day4 {
    public static void main(String[] args) {
//        var n = aoc.FileUtils.readLines("in/day4_sample.txt")
        var n = FileUtils.readLines("in/day4.txt")
                .stream()
//                .map(x -> part1(parseInput(x)))
                .map(x -> part2(parseInput(x)))
                .filter(x -> x)
                .count();
        System.out.println(n);
    }

    /**
     * Returns true if one range fully overlaps the other
     */
    static boolean part1(List<Range> l) {
        return l.get(0).contains(l.get(1)) || l.get(1).contains(l.get(0));
    }

    /**
     * Returns true if one range overlaps the other at all
     */
    static boolean part2(List<Range> l) {
        return l.get(0).contains(l.get(1)) || l.get(1).contains(l.get(0)) ||
                l.get(0).overlaps(l.get(1)) || l.get(1).overlaps(l.get(0));
    }

    static List<Range> parseInput(String s) {
        var split = s.split(",");
        return List.of(
                new Range(Integer.parseInt(split[0].split("-")[0]),
                        Integer.parseInt(split[0].split("-")[1])),

                new Range(Integer.parseInt(split[1].split("-")[0]),
                        Integer.parseInt(split[1].split("-")[1]))
        );
    }

    record Range(int begin, int end) {
        public boolean contains(Range r) {
            return r.begin() >= begin && r.end() <= end;
        }

        private boolean contains(int x) {
            return x >= begin && x <= end;
        }

        public boolean overlaps(Range r) {
            return contains(r.begin()) || contains(r.end());
        }
    }
}
