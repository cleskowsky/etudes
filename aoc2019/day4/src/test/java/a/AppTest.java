package a;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AppTest {

    @Test
    void goodPasswords() {
        App a = new App();

        // day 1
//        assertTrue(a.isCandidatePassword(111111), "This should be candidate (adjacent matching, and non-decreasing left to right");

        // day 2
        assertTrue(a.isCandidatePassword(111122), "22 makes this a good password");
        assertTrue(a.isCandidatePassword(112233), "Max 2, non-decreasing left to right");
    }

    @Test
    void badPasswords() {
        App a = new App();

        // day 1
//        assertFalse(a.isCandidatePassword(223450), "Descreasing digits 5, 0");
//        assertFalse(a.isCandidatePassword(123789), "No double");

        // day 2
        assertFalse(a.isCandidatePassword(111111), "No match groups of max 2");
    }
}