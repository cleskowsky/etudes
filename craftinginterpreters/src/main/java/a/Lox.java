package a;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Lox {
	public static void main(String[] args) throws IOException {
		System.out.println(Arrays.toString(args));
		System.out.println(args.length);

		Lox lox = new Lox();

		if (args.length > 1) {
			// Print usage
			System.out.println("Usage: jlox [script]");
			System.exit(64);
		} else if (args.length == 1) {
			// Interpret file
			lox.runFile(args[0]);
		} else {
			// repl
			lox.runPrompt();
		}
	}

	private void runFile(String path) throws IOException {
		byte[] b = Files.readAllBytes(Paths.get(path));
		run(new String(b, Charset.defaultCharset()));
	}

	private void runPrompt() throws IOException {
		InputStreamReader input = new InputStreamReader(System.in);
		BufferedReader reader = new BufferedReader(input);

		while (true) {
			System.out.print("> ");
			String line = reader.readLine();
			System.out.println(line);
			if (line.isEmpty()) {
				break;
			}
		}
	}

	private void run(String source) {
		Scanner scanner = new Scanner(source);
		List<String> tokens = scanner.tokens().collect(Collectors.toList());
		System.out.println(tokens);
	}

	private void error(int line, String message) {
		report(line, "", message);
	}

	private void report(int line, String where, String message) {
		String template = "[line: %d] Error%s: %s";
		System.err.println(String.format(template, line, where, message));
	}
}
