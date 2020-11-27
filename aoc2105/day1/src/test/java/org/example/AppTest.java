package org.example;

import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.Assert.assertEquals;

public class AppTest {

    private App a = new App();

    @Test
    public void sample() {
        assertEquals(3, a.findFloor("(()(()("));
        assertEquals(3, a.findFloor("))((((("));
        assertEquals(-3, a.findFloor(")())())"));
    }

    @Test
    public void puzzleInput() throws IOException {
        String s = new String(Files.readAllBytes(Paths.get("in.txt")));
        assertEquals(232, a.findFloor(s));
    }
}
