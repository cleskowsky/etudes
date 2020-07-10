package a;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    @Test
    void takeTurn() {
        // 1 turn
        Game g = new Game(2, 3);
        assertEquals(0, g.getCurrentPlayer());
        if (!g.isFinished()) {
            g.turn();
        }
        assertEquals(1, g.getCurrentMarble());
        assertEquals(1, g.getJar().count());
        verifyBoard(new int[] {0, 1}, g);

        // and another, player 2
        assertEquals(1, g.getCurrentPlayer());
        if (!g.isFinished()) {
            g.turn();
        }
        assertEquals(1, g.getCurrentMarble());
        assertEquals(0, g.getJar().count());
        verifyBoard(new int[] {0, 2, 1}, g);

        assertTrue(g.isFinished());
    }

    @Test
    void scorePoints() {
        Game g = new Game(9, 26);
        while (!g.isFinished()) {
            g.turn();
        }
        // Inspected printBoard output visually :)
    }

    private void verifyBoard(int[] expect, Game g) {
        for (int i = 0; i < expect.length; i++) {
            assertEquals(expect[i], g.getMarbleAt(i));
        }
    }
}