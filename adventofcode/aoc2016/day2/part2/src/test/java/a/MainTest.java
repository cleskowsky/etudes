package a;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @org.junit.jupiter.api.Test
    void getNextCodeNumber() {
        Main.Cell p = new Main.Cell(1, 3);
        assertEquals("5", new Main().getNextCodeCell(p, "ULL"));
    }
}