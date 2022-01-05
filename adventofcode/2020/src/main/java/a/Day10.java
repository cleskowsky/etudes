package a;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class Day10 {
    public static void main(String[] args) throws Exception {
        // Read input
//        var adapters = Input("inputs/10sample.txt");
//        var adapters = Input("inputs/10sample2.txt");
        var adapters = Input("inputs/10.txt");

        // Add my seat's charging outlet
        adapters.add(0, 0);
        // Add my device at the end
        adapters.add(adapters.get(adapters.size() - 1) + 3);
        for (Integer a : adapters) {
            System.out.println(a);
        }

        // Part a
        int oneJolt = 0;
        int threeJolts = 0;
        for (int i = 0; i < adapters.size() - 1; i++) {
            int curr = adapters.get(i);
            int next = adapters.get(i + 1);
            switch (next - curr) {
                case 1:
                    oneJolt++;
                    break;
                case 3:
                    threeJolts++;
                    break;
                default:
                    System.out.printf("Difference in jolts is not 1 or 3: %d, i: %d%n", next - curr, i);
                    break;
            }
        }
        System.out.println("oneJolts: " + oneJolt);
        System.out.println("threeJolts: " + threeJolts);
        System.out.println("1s * 3s: " + (oneJolt * threeJolts));

        // Part b
        // Needed much help from lizthegrey here. I have forgotten everything about
        // dynamic programming. Need to think about this one a bit more ...
        var arrangements = new HashMap<Integer, Long>();
        // There's only 1 way to connect my device since it's 3 jolts away from
        // the nearest adapter
        arrangements.put(adapters.size() - 1, 1l);
        // -2 since we know the number of ways to get to my device is 1
        // It's 3 jolts away from my last adapter ...
        for (int i = adapters.size() - 2; i >= 0; i--) {
            int curr = adapters.get(i);
            long ways = 0;
            for (int j : List.of(1, 2, 3)) {
                if (i + j >= adapters.size()) {
                    continue;
                }
                if (adapters.get(i + j) - curr > 3) {
                    continue;
                }
                ways += arrangements.getOrDefault(i + j, 0l);
            }
            arrangements.put(i, ways);
        }
        System.out.println(arrangements.get(0));
    }

    public static List<Integer> Input(String fileName) {
        List<Integer> lines = new ArrayList<>();
        try {
            lines = Files.readAllLines(Path.of(fileName)).stream()
                    .map(s -> Integer.parseInt(s))
                    .collect(Collectors.toList());
            Collections.sort(lines);
        } catch (IOException e) {
            System.out.println("Couldn't read file: " + fileName);
        }
        return lines;
    }
}
