package a;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @org.junit.jupiter.api.Test
    void getNextCodeNumber() {
        assertEquals(7, new Main().getNextCodeNumber(7, "L"));
    }

    @Test
    void getBathroomCode() {
        List<String> input = input = List.of(
            "ULL",
            "RRDDD",
            "LURDL",
            "UUUUD"
        );
        assertEquals("1985", new Main().getBathroomCode(input));
    }
}