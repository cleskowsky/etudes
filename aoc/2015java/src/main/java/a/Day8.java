package a;

import java.nio.file.Files;
import java.nio.file.Path;

public class Day8 {
	public static boolean PART_A = true;

	public int getCharCount(String s) {
		// Trim leading and trailing "
		String x = s;
		if (PART_A) {
			x = s.substring(1, s.length() - 1);
		}

		int cnt = 0;
		int i = 0;

		if (PART_A) {
			// Returns length of decoded s
			while (i < x.length()) {
				// Handle escaped char
				if (x.charAt(i) == '\\') {
					i++;
					switch (x.charAt(i)) {
						case '\\':
						case '"':
							i++;
							break;
						case 'x':
							i += 3;
							break;
						default:
							throw new RuntimeException("Bad escaped character detected: \\" + x.charAt(i));
					}
				} else {
					i++;
				}

				cnt++;
			}
		} else {
			// Returns length of double encoded s
			StringBuilder sb = new StringBuilder();
			sb.append('"');
			for (char c : x.toCharArray()) {
				switch (c) {
					case '"':
					case '\\':
						sb.append("\\" + c);
						break;
					default:
						sb.append(c);
				}
			}
			sb.append('"');
			cnt = sb.toString().length();
		}

		return cnt;
	}

	public static void main(String[] args) throws Exception {
		if (args[0].equals("partB")) {
			PART_A = false;
		}

		Day8 d = new Day8();
		int sum = 0;
		for (String s : Files.readAllLines(Path.of("input/8.txt"))) {
			if (PART_A) {
				sum += s.length() - d.getCharCount(s);
			} else {
				sum += d.getCharCount(s) - s.length();
			}
		}
		System.out.println(sum);
	}
}
