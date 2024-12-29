import java.util.List;

public class Day5 {
    public static void main(String[] args) {
        System.out.println(1);
    }

    static record PageOrderRule(int lhs, int rhs) {
    }

    static record Update(List<Integer> pages) {
    }

    static class Parser {
        ParserResult parse(String s) {
            return null;
        }

        record ParserResult(List<PageOrderRule> pageOrderRules, List<Update> updates) {
        }
    }
}
