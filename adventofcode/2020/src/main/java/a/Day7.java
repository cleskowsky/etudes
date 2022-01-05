package a;

import lombok.Data;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Day7 {

    @Data
    public static class Pair {
        private int qty;
        private String colour;

        public Pair(int qty, String colour) {
            this.qty = qty;
            this.colour = colour;
        }
    }

    public static void main(String[] args) throws Exception {
        Map<String, List<Pair>> bags = new HashMap<>();

        for (String l : Files.readAllLines(Path.of("inputs/7.txt"))) {
            // Parse bag line
            String[] bagSplit = l.split(" bags contain ");
            String bagName = bagSplit[0];

            // Parse contains part
            List<Pair> contains = new ArrayList<>();
            for (String s : bagSplit[1].split(", ")) {
                if (s.equals("no other bags.")) {
                    continue;
                }
                String[] split = s.split(" ");
                int qty = Integer.parseInt(split[0]);
                String innerBagName = String.format("%s %s", split[1], split[2]);
                contains.add(new Pair(qty, innerBagName));
            }

            bags.put(bagName, contains);
        }
        System.out.println(bags);

        // Part a
        int found = 0;
        for (String s : bags.keySet()) {
            // skip top level shiny gold bag
            if (s.equals("shiny gold")) {
                continue;
            }

            List<String> seen = new ArrayList<>();
            List<String> visited = new ArrayList<>();
            seen.add(s);

            while (!seen.isEmpty()) {
                String b = seen.remove(0);
                if (b.equals("shiny gold")) {
                    found++;
                    seen.clear();
                }
                if (visited.contains(b)) {
                    continue;
                }
                visited.add(b);
                seen.addAll(bags.get(b).stream().map(p -> p.getColour()).collect(Collectors.toList()));
            }
        }
        System.out.println(found);

        // Part b
        List<String> seen = new ArrayList<>();
        seen.add("shiny gold");
        for (int i = 0; i < seen.size(); i++) {
            String s = seen.get(i);
            List<Pair> contains = bags.get(s);
            for (Pair p : contains) {
                for (int j = 0; j < p.getQty(); j++) {
                    seen.add(p.getColour());
                }
            }
        }
        System.out.println(seen.size() - 1);
    }
}
