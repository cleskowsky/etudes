import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;

public class Day1 {
    public static void main(String[] args) throws Exception {
//        var input =
//                Files.readAllLines(Path.of("in/day1_sample.txt"));
        var input =
                Files.readAllLines(Path.of("in/day1.txt"));

        var caloriesPerElf = new ArrayList<Integer>();
        caloriesPerElf.add(0);

        for (String s : input) {
            if (s.isEmpty()) {
                caloriesPerElf.add(0);
            } else {
                var elf = caloriesPerElf.size() - 1;
                caloriesPerElf.set(elf,
                        caloriesPerElf.get(elf) + Integer.parseInt(s)
                );
            }
        }

        caloriesPerElf.sort(Collections.reverseOrder());

        System.out.println(caloriesPerElf);
        System.out.println(
                caloriesPerElf.stream().limit(3).reduce(0, Integer::sum)
        );
        System.out.println("Number of elves: " + caloriesPerElf.size());
    }
}
