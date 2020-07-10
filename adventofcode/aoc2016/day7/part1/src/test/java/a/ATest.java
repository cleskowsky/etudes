package a;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ATest {

    @Test
    void isAnABBA() {
        A a = new A();
        assertTrue(a.isAnABBA("abba"));
        assertFalse(a.isAnABBA("abcd"));
        assertFalse(a.isAnABBA("aabba"));
        assertFalse(a.isAnABBA(""));
        assertFalse(a.isAnABBA(null));
    }

    @Test
    void supportsTls() {
        A a = new A();
        assertTrue(a.supportsTls("abba"));
        assertFalse(a.supportsTls("[abba]"));
        assertTrue(a.supportsTls("abba[]"));
        assertTrue(a.supportsTls("[]abba"));

        // from sample input
        assertFalse(a.supportsTls("aaaa[qwer]tyui"));
        assertFalse(a.supportsTls("abcd[bddb]xyyx"));
        assertTrue(a.supportsTls("ioxxoj[asdfgh]zxcvbn"));
    }
}