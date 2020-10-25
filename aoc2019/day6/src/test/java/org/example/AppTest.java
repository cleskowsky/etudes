package org.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

/**
 * Unit test for simple App.
 */
public class AppTest {
	
	App a;
	
	@Before
	public void setup() {
		a = new App();
	}
	
	@Test
	public void testCountOrbitsSample() {
		Map<String, String> data = readInput("src/test/resources/sampleinput.txt");
		assertThat(a.countOrbits(data), equalTo(42));
	}
	
	@Test
	public void testCountOrbitsPuzzle() {
		Map<String, String> data = readInput("src/test/resources/puzzleinput.txt");
		assertThat(a.countOrbits(data), equalTo(254447));
	}
	
	private Map<String, String> readInput(String filePath) {
		Map<String, String> orbits = new HashMap<>();
		
		List<String> lines;
		try {
			lines = Files.readAllLines(Path.of(filePath));
			lines.forEach(l -> {
				String[] parts = l.split("\\)");
				orbits.put(
					parts[1], // orbiter
					parts[0]  // orbited
				);
			});
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return orbits;
	}
}
