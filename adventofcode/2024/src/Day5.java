import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day5 {
    public static void main(String[] args) throws IOException {
        var result = new Parser().parse(Files.readString(Path.of("inputs/day5.txt")));
//        var result = new Parser().parse(Files.readString(Path.of("inputs/day5_sample.txt")));

        var sum = 0;
        for (Update update: result.updates()) {
            boolean valid = true;
            for (PageOrderRule rule: result.pageOrderRules()) {
                if (rule.validates(update)) {
                    continue;
                } else {
                    valid = false;
                }
            }
            if (valid) {
                int mid = update.pages().size() / 2;
                sum += update.pages.get(mid);
            }
        }
        System.out.println(sum);
    }

    record PageOrderRule(int lhs, int rhs) {
        /**
         * Return false if lhs appears in update after rhs
         */
        public boolean validates(Update update) {
            var foundRhs = false;
            for (int page: update.pages) {
                if (page == rhs) {
                    foundRhs = true;
                }
                if (foundRhs && page == lhs) {
//                    System.out.println("Update failed validation");
//                    System.out.println(update);
//                    System.out.println(this);
                    return false;
                }
            }
            return true;
        }
    }

    record Update(List<Integer> pages) {
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
}
