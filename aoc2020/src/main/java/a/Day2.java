package a;

import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Day2 {
	public static void main(String[] args) throws Exception {
		URL url = Day2.class.getClassLoader().getResource("day2.txt");
		List<String> passwords = Files.readAllLines(Path.of(url.toURI()));
		
		int matchesA = 0;
		int matchesB = 0;
		for (String s : passwords) {
			String[] parts = s.split(" ");
			if (parts.length != 3) {
				System.out.println("Failed to parse password: " + s);
			}
			String lenSpec = parts[0];
			char ch = parts[1].charAt(0);
			String password = parts[2];
			
			parts = lenSpec.split("-");
			if (parts.length != 2) {
				System.out.println("Failed to parse lenSpec: " + lenSpec);
			}
			int min = Integer.parseInt(parts[0]);
			int max = Integer.parseInt(parts[1]);

			// Part a
			long n = password.chars().filter(c -> c == ch).count();
			if (n >= min && n <= max) {
				matchesA++;
			}
			
			// Part b
			int found = 0;
			if (password.charAt(min -1) == ch) {
				found++;
			}
			if (password.charAt(max -1) == ch) {
				found++;
			}
			if (found == 1) {
				matchesB++;
			}
		}
		
		System.out.println(matchesA);
		System.out.println(matchesB);
	}
}
