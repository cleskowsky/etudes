package a;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

import a.Day7.GateType;
import a.Day7.Signal;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Day7Test {

	private static final List<String> testData = List.of(
		"123 -> x",
		"456 -> y",
		"x AND y -> d",
		"x OR y -> e",
		"x LSHIFT 2 -> f",
		"y RSHIFT 2 -> g",
		"NOT x -> h",
		"NOT y -> i"
	);

	@Test
	public void getWireSignal() {
		Map<String, Integer> testTable = Map.of(
			// testData, expectedValue
			"x", 123,
			"h", 65412,
			"f", 492,
			"g", 114,
			"e", 507,
			"d", 72
		);
		for (String wireID : testTable.keySet()) {
			assertEquals((long) testTable.get(wireID), Day7.getWireSignal(
				wireID,
				getTestWireData(),
				new HashMap<>()
			));
		}
	}

	@Test
	public void parseTestData() {
		// Should be 8 wires in my test data
		Map<String, Signal> wireMap = Day7.parseInput(testData);
		System.out.println(wireMap);

		assertEquals(8, wireMap.keySet().size());

		// Check parse of wire d worked
		Signal s = wireMap.get("d");
		assertEquals(2, s.getArgs().size());
		assertTrue(s.getArgs().contains("x"));
		assertTrue(s.getArgs().contains("y"));
		assertEquals(GateType.AND, s.getGateType());
	}

	private Map<String, Signal> getTestWireData() {
		return Day7.parseInput(testData);
	}
}
