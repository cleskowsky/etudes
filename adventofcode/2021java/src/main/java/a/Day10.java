package a;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class Day10 {
	public static final boolean DEBUG = false;

	public static void main(String[] args) {
//		var data = Input("in/10_sample.txt");
		var data = Input("in/10.txt");

		System.out.println(data);
		System.out.println(data.size());
		System.out.println(validateChunk(data.get(2)));

		var pts = data.stream()
			.map(Day10::validateChunk)
			.map(x -> x.invalidChar)
			.map(Day10::scoreBadChar)
			.reduce(0, Integer::sum);

		System.out.println(pts);
	}

	public static Map<Character, Character> matchingPairs = Map.of(
		')', '(',
		']', '[',
		'}', '{',
		'>', '<'
	);

	public static int scoreBadChar(Character c) {
		if (c == null) {
			return 0;
		}

		return switch (c) {
			case ')' -> 3;
			case ']' -> 57;
			case '}' -> 1197;
			case '>' -> 25137;
			default -> 0;
		};
	}

	public static ValidationStatus validateChunk(String chunk) {
		if (DEBUG) {
			System.out.println("Validating chunk: " + chunk);
		}

		Stack<Character> opens = new Stack<>();

		boolean isValid = true;
		Character invalidChar = null;
		for (Character c : chunk.toCharArray()) {
			if ("([{<".indexOf(c) >= 0) {
				// Chunk opener found
				opens.add(c);
				if (DEBUG) {
					System.out.println(opens);
				}
			} else {
				// Chunk closer
				if (opens.isEmpty()) {
					// Found a closer without a matching opener eg ())
					isValid = false;
					invalidChar = c;
					break;
				}
				Character expectedOpenerForCloser = matchingPairs.get(c);
				if (opens.pop() != expectedOpenerForCloser) {
					// Mismatched opener, closer eg (}
					isValid = false;
					invalidChar = c;
					break;
				}
			}
		}

		if (opens.size() > 0) {
			isValid = false;
		}

		if (DEBUG) {
			System.out.println("Result: " + isValid);
		}
		return new ValidationStatus(isValid, invalidChar);
	}

	record ValidationStatus(boolean valid, Character invalidChar) {
	}

	private static List<String> Input(String fileName) {
		List<String> ret = new ArrayList<>();
		try {
			ret = Files.readAllLines(Path.of(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return ret;
	}
}
