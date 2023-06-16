import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

class Day11 {

    public static void main(String[] args) {
        // Part 1
        var r = new WorryReducer() {
            @Override
            public long reduce(long worry) {
                return worry / 3;
            }
        };
        System.out.println(
                keepAway(parse(FileUtils.readLines("11s")), 20, r));
        System.out.println(
                keepAway(parse(FileUtils.readLines("11")), 20, r));

        // Part 2
        var r2 = new WorryReducer() {
            int lcm;

            @Override
            public long reduce(long worry) {
                return worry % lcm;
            }

            @Override
            public void monkeys(Monkeys m) {
                lcm = m.stream()
                        .map(x -> x.t.by)
                        .reduce(1, (a, b) -> a * b);
            }
        };

//        System.out.println(
//                keepAway(parse(FileUtils.readLines("11s")), 1000));
//        System.out.println(
//                keepAway(parse(FileUtils.readLines("11")), 10000));
    }

    interface WorryReducer {
        long reduce(long worry);

        default void monkeys(Monkeys m) {}
    }

    /**
     * Play n keepAway rounds
     */
    static long keepAway(Monkeys monkeys, int n, WorryReducer r) {
        var lcm = monkeys.stream()
                .map(m -> m.t.by)
                .reduce(1, (a, b) -> a * b);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < monkeys.size(); j++) {
                var m = monkeys.get(j);
                m.items.forEach(x -> {
                    var item = r.reduce(worry(m, x));
                    var next = monkeys.get(test(m, item));
                    next.items.add(item);
//                    next.items.add(item % lcm);
                });
                m.items.clear();
            }
        }

        var top2 = monkeys.stream()
                .map(x -> x.inspected)
                .sorted(Comparator.reverseOrder())
                .limit(2)
                .collect(Collectors.toList());

        return top2.get(0) * top2.get(1);
    }

    static long worry(Monkey m, long item) {
        m.inspected++;
        return m.op.eval(item);
    }

    static int test(Monkey m, long item) {
        return m.t.eval(item);
    }

    static class Monkey {
        List<Long> items;
        Op op;
        Test t;
        long inspected;

        Monkey(List<Long> items, Op op, Test t) {
            this.items = items;
            this.op = op;
            this.t = t;
        }
    }

    static Monkeys parse(List<String> in) {
        var val = new Monkeys();
        for (int i = 0; i < in.size(); i++) {
            // id
            var split = in.get(i).split(":");
            split = split[0].split(" ");
            var id = Integer.parseInt(split[1]);
            i++;

            // items
            split = in.get(i).split(":");
            split = split[1].split(",");
            var items = new ArrayList<Long>();
            for (String s : split) {
                items.add(Long.parseLong(s.trim()));
            }
            i++;

            // operation
            split = in.get(i).split(":");
            split = split[1].split("=");
            split = split[1].trim().split(" ");
            var f = split[1].trim().equals("+") ? Op.FUNC.ADD : Op.FUNC.MULT;
            var op = new Op(split[0].trim(), f, split[2].trim());
            i++;

            // test
            split = in.get(i).split(":");
            split = split[1].split(" ");
            var by = Integer.parseInt(split[split.length - 1]);
            i++;

            //   true
            split = in.get(i).split("monkey");
            var nextTrue = Integer.parseInt(split[1].trim());
            i++;

            //   false
            split = in.get(i).split("monkey");
            var nextFalse = Integer.parseInt(split[1].trim());
            i++;
            var t = new Test(by, nextTrue, nextFalse);

            val.add(new Monkey(items, op, t));
        }
        return val;
    }

    static record Op(String lhs, FUNC f, String rhs) {
        enum FUNC {
            ADD,
            MULT;
        }

        long eval(long old) {
            long x;
            try {
                x = Long.parseLong(lhs);
            } catch (NumberFormatException e) {
                x = old;
            }

            long y;
            try {
                y = Long.parseLong(rhs);
            } catch (NumberFormatException e) {
                y = old;
            }

            return switch (f) {
                case ADD -> x + y;
                case MULT -> x * y;
            };
        }
    }

    static record Test(int by, int matched, int notMatched) {
        int eval(long item) {
            if (item % by == 0) {
                return matched;
            }
            return notMatched;
        }
    }

    static class Monkeys extends ArrayList<Monkey> {}
}
