package a;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AppTest {

    @Test
    void goodPasswords() {
        App a = new App();
        assertTrue(a.isCandidatePassword(111111), "This should be candidate (adjacent matching, and non-decreasing left to right");
    }

    @Test
    void badPasswords() {
        App a = new App();
        assertFalse(a.isCandidatePassword(223450), "Descreasing digits 5, 0");
        assertFalse(a.isCandidatePassword(123789), "No double");
    }
}