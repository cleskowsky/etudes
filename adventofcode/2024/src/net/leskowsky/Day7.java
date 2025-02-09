package net.leskowsky;

import java.util.List;

public class Day7 {
    public static void main(String[] args) {

//        Sample input
//        190: 10 19
//        3267: 81 40 27
//        83: 17 5
//        156: 15 6
//        7290: 6 8 6 15
//        161011: 16 10 13
//        192: 17 8 14
//        21037: 9 7 18 13
//        292: 11 6 16 20

        var x = new Day7();
//        x.solver(List.of("10", "19"), 0, new ArrayList<>(), 190);
//        x.solver(List.of("81", "40", "27"), 0, new ArrayList<>(), 3267);
//        x.solver(List.of("11", "6", "16", "20"), 0, new ArrayList<>(), 292);

//        190: 10 19
//        x.solver(10, List.of(19), 190);
        x.solver(11, List.of(6, 16, 20), 292);
    }

    public void solver(long lhs, List<Integer> terms, long answer) {
        if (terms.isEmpty()) {
            // is lhs == answer
            System.out.printf("lhs=%d answer=%d good_eq=%b\n", lhs, answer, lhs==answer);
        } else {
            var t = terms.getFirst();
            solver(lhs + t, terms.subList(1, terms.size()), answer);
            solver(lhs * t, terms.subList(1, terms.size()), answer);
        }
    }
}
