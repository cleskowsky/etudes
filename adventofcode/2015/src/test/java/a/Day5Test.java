package a;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Day5Test {

	@Test
	public void testIsNice() {
		Day5 d = new Day5();
		assertTrue(d.isNice("qjhvhtzxzqqjkmpb"));
		assertTrue(d.isNice("xxyxx"));
		assertFalse(d.isNice("uurcxstgmygtbstg"));
		assertFalse(d.isNice("ieodomkazucvgmuy"));
	}
}
