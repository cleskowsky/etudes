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
//        x.solver(List.of("10", "19"), 0, new ArrayList<>(), 190);
//        x.solver(List.of("81", "40", "27"), 0, new ArrayList<>(), 3267);
        x.solver(List.of("11", "6", "16", "20"), 0, new ArrayList<>(), 292);
    }

    public void solver(List<String> terms, int i, List<String> expr, long answer) {
        int x = terms.size() - i;

        if (x == 0) {
            // final expression to calculate
            System.out.println(expr);

            // calculate expr
            int lhs = Integer.parseInt(expr.getFirst());

            for (int j = 1; j < expr.size(); j += 2) {
                var op = expr.get(j).charAt(0);
                int rhs = Integer.parseInt(expr.get(j + 1));
                if (op == '+') {
                    lhs += rhs;
                } else if (op == '*') {
                    lhs *= rhs;
                }
            }
            // accumulated result
            System.out.println(lhs);

            // does our result match answer
            if (lhs == answer) {
                System.out.println("Good equation " + expr);
            }
        } else if (x == 1) {
            // last term
            var addExpr = new ArrayList<>(expr);
            addExpr.add(terms.get(i));
            solver(terms, i + 1, addExpr, answer);
        } else {
            // try add
            var addExpr = new ArrayList<>(expr);
            addExpr.addAll(List.of(terms.get(i), "+"));
            solver(terms, i + 1, addExpr, answer);

            // try mult
            var multExpr = new ArrayList<>(expr);
            multExpr.addAll(List.of(terms.get(i), "*"));
            solver(terms, i + 1, multExpr, answer);
        }
    }
}

