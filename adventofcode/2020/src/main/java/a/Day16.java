package a;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Day16 {
    public static boolean debug = true;

    public static class FieldRule {
        private String name;
        // (1, 10), (15, 20)
        private List<List<Integer>> ranges;

        public FieldRule(String s) {
            // class: 1-3 or 5-7
            // Parse class name
            String[] split = s.split(": ");
            name = split[0];

            // Parse ranges
            ranges = new ArrayList<>();
            for (String r : split[1].split(" or ")) {
                String[] parts = r.split("-");
                ranges.add(List.of(
                        Integer.parseInt(parts[0]),
                        Integer.parseInt(parts[1])
                ));
            }
        }

        public boolean matches(int n) {
            for (List<Integer> r : ranges) {
                if (n >= r.get(0) && n <= r.get(1)) {
                    return true;
                }
            }
            return false;
        }
    }

    public static class Ticket {
        private List<Integer> fields;

        public Ticket(String s) {
            // 7,3,47
            // Parse fields
            fields = new ArrayList<>();
            String[] split = s.split(",");
            for (String f : split) {
                fields.add(Integer.parseInt(f));
            }
        }
    }

    public static void main(String[] args) throws Exception {
//        List<String> lines = Files.readAllLines(Path.of("inputs/16sample.txt"));
        List<String> lines = Files.readAllLines(Path.of("inputs/16.txt"));

        List<FieldRule> rules = new ArrayList<>();
        List<Ticket> tickets = new ArrayList<>();

        for (String l : lines) {
            if (l.contains(" or ")) {
                rules.add(new FieldRule(l));
            } else if (l.contains(",")) {
                tickets.add(new Ticket(l));
            }
        }

        if (debug) {
            System.out.println("Rules:");
            for (FieldRule r : rules) {
                System.out.println(r.name);
                System.out.println(r.ranges);
            }

            System.out.println("Tickets:");
            for (Ticket t : tickets) {
                System.out.println(t.fields);
            }
        }

        // Parta Check nearby tickets for invalid fields
        int sum = 0;
        for (int i = 1; i < tickets.size(); i++) {
            Ticket t = tickets.get(i);
            for (Integer f : t.fields) {
                List<FieldRule> matching = new ArrayList<>();
                for (FieldRule r : rules) {
                    if (r.matches(f)) {
                        matching.add(r);
                    }
                }
                if (matching.isEmpty()) {
                    sum += f;
                }
            }
        }
        System.out.println(sum);
    }
}
