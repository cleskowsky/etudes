package a;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class Day12Test {

    @Test
    void turn() {
        Assertions.assertEquals(3, Day12.turn(0, true, 90));
        Assertions.assertEquals(2, Day12.turn(0, true, 180));
        Assertions.assertEquals(2, Day12.turn(0, false, 180));
    }
}