package a;

import junit.framework.TestCase;

public class Day5Test extends TestCase {

	public void testIsNice() {
		Day5 d = new Day5();
		assertTrue(d.isNice("qjhvhtzxzqqjkmpb"));
		assertTrue(d.isNice("xxyxx"));
		assertFalse(d.isNice("uurcxstgmygtbstg"));
		assertFalse(d.isNice("ieodomkazucvgmuy"));
	}
}
