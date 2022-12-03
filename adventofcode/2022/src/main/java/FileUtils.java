import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

public class FileUtils {
    static List<String> readLines(String fileName) {
        try {
            return Arrays.stream(
                            Files.readString(Path.of(fileName)).trim().split("\n"))
                    .toList();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
