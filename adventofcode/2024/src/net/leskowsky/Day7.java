package net.leskowsky;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

public class Day7 {
    public static void main(String[] args) throws IOException {

        var x = new Day7();

        assert x.solver(81, List.of(40, 27), 3267);
        assert !x.solver(16, List.of(10, 13), 161011);

        // input
//        String input = Files.readString(Path.of("inputs/day7_sample.txt"));
        String input = Files.readString(Path.of("inputs/day7.txt"));

        // part 1
        long sum_a = 0;

        // part 2
        long sum_b = 0;
        for (String line : input.split("\n")) {
            var split = line.split(": ");
            long answer = Long.parseLong(split[0]);
            List<Integer> terms = Arrays.stream(split[1].split(" "))
                    .map(Integer::parseInt)
                    .toList();
            if (x.solver(terms.getFirst(),
                    terms.subList(1, terms.size()),
                    answer)) {
                sum_a += answer;
            }

            // Part 2
            if (x.solver2(terms.getFirst(),
                    terms.subList(1, terms.size()),
                    answer)) {
                sum_b += answer;
            }
        }
        System.out.println("Part 1: " + sum_a);

        // Part 2
        System.out.println("Part 2: " + sum_b);
    }

    public boolean solver(long lhs, List<Integer> theRest, long answer) {
        if (theRest.isEmpty()) {
            // is lhs == answer
            return lhs == answer;
        } else {
            var t = theRest.getFirst();
            // try add
            return solver(lhs + t, theRest.subList(1, theRest.size()), answer) ||
                    // try mult
                    solver(lhs * t, theRest.subList(1, theRest.size()), answer);
        }
    }

    public boolean solver2(long lhs, List<Integer> theRest, long answer) {
        if (theRest.isEmpty()) {
            // is lhs == answer
            return lhs == answer;
        } else {
            var t = theRest.getFirst();
            var concat = Long.parseLong(String.valueOf(lhs).concat(String.valueOf(t)));
            // try add
            return solver2(lhs + t, theRest.subList(1, theRest.size()), answer) ||
                    // try concat
                    solver2(concat, theRest.subList(1, theRest.size()), answer) ||
                    // try mult
                    solver2(lhs * t, theRest.subList(1, theRest.size()), answer);
        }
    }
}
