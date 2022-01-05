package a;

import java.util.Map;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Day10Test {

	@Test
	void lookAndSay() {
		Map<String, String> testTable = Map.of(
			// Input, Expected
			"1", "11",
			"11", "21",
			"21", "1211",
			"1211", "111221"
		);
		testTable.forEach((k, v) -> {
			assertEquals(v, Day10.lookAndSayWithRounds(k, 1));
		});
	}

	@Test
	void rounds() {
		assertEquals("312211", Day10.lookAndSayWithRounds("1", 5));
	}

	@Test
	void throwsExceptionForBadIntegerInput() {
		try	{
			Day10.lookAndSayWithRounds("a", 1);
			fail();
		} catch (Exception e) {
			// Good!
		}
	}

	@Test
	void throwsExceptionForLessThan1Rounds() {
		try {
			Day10.lookAndSayWithRounds("1", 0);
			fail();
		} catch (Exception e) {
			// Good!
		}
	}
}