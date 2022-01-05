package a;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Day5 {
	public static int getSeatIdForBoardingPass(String bp) {
		int low = 0;
		int high = 127;
		int left = 0;
		int right = 7;
		for (char c : bp.toCharArray()) {
			switch (c) {
				case 'F':
					high -= (high - low) / 2 + 1;
//					System.out.printf("%s %d %d%n", c, low, high);
					break;
				case 'B':
					low += (high - low) / 2 + 1;
//					System.out.printf("%s %d %d%n", c, low, high);
					break;
				case 'L':
					right -= (right - left) / 2 + 1;
//					System.out.printf("%s %d %d%n", c, left, right);
					break;
				case 'R':
					left += (right - left) / 2 + 1;
//					System.out.printf("%s %d %d%n", c, left, right);
					break;
				default:
					throw new RuntimeException("Found unknown char in boarding pass");
			}
		}
		return low * 8 + left;
	}
	
	public static void main(String[] args) throws Exception {
		// Bit of testing for my boarding pass -> seat id converter
		assert 357 == getSeatIdForBoardingPass("FBFBBFFRLR");
		assert 567 == getSeatIdForBoardingPass("BFFFBBFRRR");
		
		// Looks good. Should be ready to run against input now
		List<String> passes = Files.readAllLines(Path.of("inputs/5.txt"));
		
		// Part a
		int max = 0;
		List<Integer> seen = new ArrayList<>();
		for (String p : passes) {
			int n = getSeatIdForBoardingPass(p);
			if (n > max) {
				max = n;
			}
			seen.add(n);
		}
		System.out.println(max);
		
		// Part b
		Collections.sort(seen);
		for (int i = 0; i < seen.get(seen.size() - 1); i++) {
			if (seen.contains(i)) {
				continue;
			}
			if (seen.contains(i - 1) && seen.contains(i + 1)) {
				System.out.println(i);
			}
		}
	}
}
