package a;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Day8Test {

	@org.junit.jupiter.api.Test
	void getCharacterCount() {
		Day8 d = new Day8();
		assertEquals(0, d.getCharCount("\"\""));
		assertEquals(3, d.getCharCount("\"abc\""));
		assertEquals(7, d.getCharCount("\"aaa\\\"aaa\""));
		assertEquals(1, d.getCharCount("\"\\x27\""));
	}

	@Test
	void partB() {
		Day8 d = new Day8();
		Day8.PART_A = false;
		assertEquals(6, d.getCharCount("\"\""));
		assertEquals(9, d.getCharCount("\"abc\""));
		assertEquals(16, d.getCharCount("\"aaa\\\"aaa\""));
		assertEquals(11, d.getCharCount("\"\\x27\""));
	}
}
