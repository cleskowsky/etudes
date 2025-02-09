package net.leskowsky;

import java.util.ArrayList;
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
        x.solver(List.of("10", "19"), 0, new ArrayList<>());
    }

    public void solver(List<String> terms, int i, List<String> expr) {
        int x = terms.size() - i;

        if (x == 0) {
            System.out.println(expr);
        } else if (x == 1) {
            var addExpr = new ArrayList<>(expr);
            addExpr.add(terms.get(i));
            solver(terms, i + 1, addExpr);
        } else {
            // try add
            var addExpr = new ArrayList<>(expr);
            addExpr.addAll(List.of(terms.get(i), "+"));
            solver(terms, i + 1, addExpr);

            // try mult
            var multExpr = new ArrayList<>(expr);
            multExpr.addAll(List.of(terms.get(i), "*"));
            solver(terms, i + 1, multExpr);
        }
    }
}
