package aoc;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

public class FileUtils {
    public static List<String> readLines(String fileName) {
        try {
            return Arrays.stream(
                            Files.readString(Path.of(
                                    "in/" + fileName + ".txt")).trim().split("\n"))
                    .toList();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String readAll(String fileName) {
        try {
            return Files.readString(Path.of("in/" + fileName + ".txt"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
