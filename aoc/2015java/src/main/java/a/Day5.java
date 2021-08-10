package a;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Day5 {

	public static final boolean DEBUG = true;

	private boolean hasPair(String s) {
		List<String> l = new ArrayList<>();
		for (int i = 0; i < s.length() - 1; i++) {
			String x = s.substring(i, i + 2);
			String rest = s.substring(i + 2);
			if (DEBUG) {
				l.add(String.format("%s %s %s", s, x, rest));
			}
			if (rest.contains(x)) {
				return true;
			}
		}
		if (DEBUG) {
			System.out.println(l);
		}
		return false;
	}

	private boolean hasXYX(String s) {
		List<String> l = new ArrayList<>();
		for (int i = 0; i < s.length() - 2; i++) {
			char x = s.charAt(i);
			char y = s.charAt(i + 2);
			if (DEBUG) {
				l.add(String.format("%s %c %c %c", s, x, y, s.charAt(i + 2)));
			}
			if (x == y) {
				return true;
			}
		}
		if (DEBUG) {
			System.out.println(l);
		}
		return false;
	}

	/**
	 * Returns true if s has a pair of characters that match
	 * somewhere in the string, and also has 3 contiguous characters
	 * that match the mask XYX
	 */
	public boolean isNice(String s) {
		return hasPair(s) && hasXYX(s);
	}

	public static void main(String[] args) {
		try {
			Day5 d = new Day5();
			List<String> lines = Files.readAllLines(Path.of("input/5.txt"));
			System.out.println(lines);
			System.out.println(lines.size());
			long n = lines.stream().filter(x -> d.isNice(x)).count();
			System.out.println(n);

			// 67 is not the right answer
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
