import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Day7Test {

    @Test
    void cdDotDot() {
        // Given a directory with a parent directory
        Day7.File root = new Day7.File("root", 0, null);
        Day7.File dir = new Day7.File("test", 0, root);

        // When "cd .." is received
        // Then return parent
        assertEquals("root", dir.cd("..").getName());

        // What happens when directory is root?
        assertEquals("root", root.cd("..").getName());
    }

    @Test
    void addFileToDirectory() {
        // Given a data file and a directory
        Day7.File dir = new Day7.File("root", 0, null);

        // When a file is added to the directory
        dir.addFile("a.txt", 1);

        // Then the file is returned by ls
        assertEquals(1, dir.ls().size());
    }
}
