import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class Day6Test {

    @Test
    void marker() {
        var testTable = Map.of(
                "mjqjpqmgbljsphdztnvjfqwrcgsmlb", 7,
                "bvwbjplbgvbhsrlpgdmjqwftvncz", 5,
                "nppdvjthqldpwncqszvftbrmjlhg", 6,
                "nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg", 10,
                "zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw", 11
        );
        testTable.forEach((k, v) -> {
            assertEquals(Day6.marker(k, 4), v);
        });
    }
}
