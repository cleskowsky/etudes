package net.leskowsky;

import lombok.extern.java.Log;

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

        // sample input
//        String input = Files.readString(Path.of("inputs/day7_sample.txt"));
        String input = Files.readString(Path.of("inputs/day7.txt"));

        long sum = 0;

        for (String line : input.split("\n")) {
            var split = line.split(": ");
            long answer = Long.parseLong(split[0]);
            List<Integer> terms = Arrays.stream(split[1].split(" "))
                    .map(Integer::parseInt)
                    .toList();
            if (x.solver2(terms.getFirst(),
                    terms.subList(1, terms.size()),
                    answer)) {
                sum += answer;
            }
        }

        System.out.println(sum);
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
