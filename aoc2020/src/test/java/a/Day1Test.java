package a;

import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Day1Test {

	Day1 d1 = new Day1();
	
	@Test
	public void sample() {
		assertEquals(0, d1.findExpenses(List.of(1, 2, 3)));
		assertEquals(514579, d1.findExpenses(List.of(1721, 979, 366, 299, 675, 1456)));
	}
	
	@Test
	public void puzzleInput() {
		List<Integer> input = readInput("day1.txt");

		// Part 1 is find 2 numbers in a list that add to 2020
		assertEquals(357504, d1.findExpenses(input));
		
		// Part 2 is like part 1 but find 3 numbers instead of 2
		assertEquals(12747392, d1.findExpenses2(input));
	}

	private List<Integer> readInput(String fname) {
		List<Integer> expenses = new ArrayList<>();
		try {
			URL url = this.getClass().getClassLoader().getResource(fname);
			expenses = Files.readAllLines(Path.of(url.toURI()))
				.stream()
				.map(x -> Integer.parseInt(x))
				.collect(Collectors.toList());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return expenses;
	}
}