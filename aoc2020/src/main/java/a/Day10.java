package a;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Day10 {
    public static void main(String[] args) throws Exception {
//        var adapters = Input("inputs/10sample.txt");
        var adapters = Input("inputs/10.txt");
        for (Integer a : adapters) {
            System.out.println(a);
        }
        // Add my seat's charging outlet
        adapters.add(0, 0);

        // Part a
        int oneJolt = 0;
        // threeJolts starts at 1 to account for my device
        int threeJolts = 1;
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
