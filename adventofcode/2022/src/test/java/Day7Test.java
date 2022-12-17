import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Day7Test {

    @Test
    void cd() {
        // Given a dir file
        Day7.File f = new Day7.File("root", null);

        // When dir doesn't exist in that file
        var ret = f.cd("test");

        // Create and return it
        assertEquals("test", ret.getName());
        assertEquals(1, f.ls().size());
        assertEquals("test", f.ls().get(0).getName());
    }

    @Test
    void cdDotDot() {
        // Given a dir file with parent
        Day7.File root = new Day7.File("root", null);
        Day7.File f = new Day7.File("test", root);

        // When "cd .." is received
        // Then return parent
        assertEquals("root", f.cd("..").getName());

        // What happens when dir file is root?
        assertEquals("root", root.cd("..").getName());
    }
}