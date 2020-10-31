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
	Map<String, String> orbitsMap;
	
	@Before
	public void setup() {
		a = new App();
		orbitsMap = readInput("src/test/resources/puzzleinput.txt");
	}
	
	@Test
	public void countOrbitsSample() {
		Map<String, String> data = readInput("src/test/resources/sampleinput.txt");
		assertThat(a.countOrbits(data), equalTo(42));
	}
	
	@Test
	public void countOrbitsPuzzle() {
		assertThat(a.countOrbits(orbitsMap), equalTo(254447));
	}
	
	@Test
	public void findAllOrbitedObjects() {
// 		Sample data
//		Map<String, String> data = readInput("src/test/resources/sampleinput2.txt");
//		List<String> x = a.findAllOrbitedObjects(data, "YOU");
//		List<String> y = a.findAllOrbitedObjects(data, "SAN");
		
		// Find the common orbited object for for YOU and SAN. It's the first
		// one in the intersection of both lists
		List<String> x = a.findAllOrbitedObjects(orbitsMap, "YOU");
		List<String> y = a.findAllOrbitedObjects(orbitsMap, "SAN");
		x.retainAll(y);
		System.out.println(x);
		
		// XN5 in my input
		// Print out the index of XN5 in both lists
		// Re-initializing x because retainall mutates it
		x = a.findAllOrbitedObjects(orbitsMap, "YOU");
		int n = x.indexOf("XN5");
		int m = y.indexOf("XN5");
		// -2 to exclude transfers YOU and SAN (These aren't objects that
		// orbit anything in our data)
		System.out.println(n + m - 2);
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
