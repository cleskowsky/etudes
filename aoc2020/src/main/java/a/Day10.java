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
        var adapters = Input("inputs/10sample.txt");
        for (Integer a : adapters) {
            System.out.println(a);
        }

        // Part a
        List<Integer> using = new ArrayList<>();
        List<Integer> next = new ArrayList<>();
        int curr = 0;

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
