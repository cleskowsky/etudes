import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day5 {
    public static boolean validUpdate(Update u, List<PageOrderRule> rules) {
        for (PageOrderRule r : rules) {
            if (!r.validates(u)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        var result = new Parser().parse(Files.readString(Path.of("inputs/day5.txt")));
//        var result = new Parser().parse(Files.readString(Path.of("inputs/day5_sample.txt")));

        // Part 1

        var sum = 0;
        for (Update u : result.updates()) {
            if (validUpdate(u, result.pageOrderRules())) {
                int mid = u.pages().size() / 2;
                sum += u.pages.get(mid);
            }
        }
        System.out.println(sum);

        // Part 2

    }

    record PageOrderRule(int lhs, int rhs) {
        /**
         * Return false if lhs appears in update after rhs
         */
        public boolean validates(Update update) {
            for (int i = 0; i < update.pages().size(); i++) {
                var p1 = update.pages().get(i);
                if (p1 == rhs) {
                    for (int j = i; j < update.pages().size(); j++) {
                        var p2 = update.pages().get(j);
                        if (p2 == lhs) {
                            return false;
                        }
                    }
                }
            }
            return true;
        }
    }

    record Update(List<Integer> pages) {
        Update(List<Integer> pages) {
            // deep copy
            this.pages = new ArrayList<Integer>(pages);
        }
    }

    static class Parser {
        record ParserResult(List<PageOrderRule> pageOrderRules, List<Update> updates) {
        }

        public ParserResult parse(String s) {
            var split = s.split("\n\n");
            return new ParserResult(
                    parsePageOrderRules(split[0]),
                    parseUpdates(split[1])
            );
        }

        private List<PageOrderRule> parsePageOrderRules(String s) {
            var rules = new ArrayList<PageOrderRule>();
            for (String rule : s.split("\n")) {
                var split = rule.split("\\|");
                rules.add(new PageOrderRule(
                        Integer.parseInt(split[0]),
                        Integer.parseInt(split[1]))
                );
            }
            return rules;
        }

        private List<Update> parseUpdates(String s) {
            var updates = new ArrayList<Update>();
            for (String update : s.split("\n")) {
                var pages = Arrays.stream(update.split(","))
                        .map(Integer::parseInt)
                        .toList();
                updates.add(new Update(pages));
            }
            return updates;
        }
    }

    public Update fixUpdate(Update u, List<PageOrderRule> rules) {
        var fixed = new Update(u.pages());
        for (PageOrderRule r : rules) {
            for (int i = 0; i < fixed.pages().size(); i++) {
                int p1 = fixed.pages().get(i);
                if (r.rhs() == p1) {
                    for (int j = i + 1; j < u.pages().size(); j++) {
                        int p2 = fixed.pages().get(j);
                        if (r.lhs() == p2) {
                            // swap pages
                            fixed.pages().set(i, p2);
                            fixed.pages().set(j, p1);
                        }
                    }
                }
            }
        }
        return fixed;
    }
}
