package a;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;

public class Day6 {
	public static final boolean PARTB = true;

	enum Action {
		TURN_ON,
		TOGGLE,
		TURN_OFF
	}

	@Data
	public static class Point {
		int x;
		int y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	@Data
	public static class Instruction {
		Action action;
		Point topLeft;
		Point bottomRight;

		public Instruction() {
		}

		public Instruction(Action a, Point p, Point q) {
			this.action = a;
			this.topLeft = p;
			this.bottomRight = q;
		}
	}

	public static void main(String[] args) {
		int[][] grid = new int[1000][1000];

		List<Instruction> l = Input.parse("input/6.txt");
		for (Instruction instr : l) {
			for (int i = instr.getTopLeft().getX(); i <= instr.getBottomRight().getX(); i++) {
				for (int j = instr.getTopLeft().getY(); j <= instr.getBottomRight().getY(); j++) {
					switch (instr.action) {
						case TURN_ON:
							if (PARTB) {
								grid[j][i] += 1;
							} else {
								grid[j][i] = 1;
							}
							break;
						case TURN_OFF:
							if (PARTB) {
								if (grid[j][i] > 0) {
									grid[j][i] -= 1;
								}
							} else {
								grid[j][i] = 0;
							}
							break;
						case TOGGLE:
							if (PARTB) {
								grid[j][i] += 2;
							} else {
								grid[j][i] = grid[j][i] == 1 ? 0 : 1;
							}
							break;
					}
				}
			}
		}

		int sum = 0;
		for (int i = 0; i < 1000; i++) {
			for (int j = 0; j < 1000; j++) {
				if (PARTB) {
					sum += grid[j][i];
				} else {
					if (grid[j][i] == 1) {
						sum++;
					}
				}
			}
		}
		System.out.println(sum);
	}

	public static class Input {
		public static List<Instruction> parse(String fileName) {
			List<Instruction> l = new ArrayList<>();
			try {
				for (String s : Files.readAllLines(Path.of(fileName))) {
					l.add(parseInstruction(s));
				}
			} catch (IOException e) {
				System.out.println("Couldn't open file: " + fileName);
			}
			return l;
		}

		public static Instruction parseInstruction(String s) {
			Instruction instr = new Instruction();

			if (s.startsWith("turn on")) {
				instr.setAction(Action.TURN_ON);
			} else if (s.startsWith("toggle")) {
				instr.setAction(Action.TOGGLE);
			} else if (s.startsWith("turn off")) {
				instr.setAction(Action.TURN_OFF);
			} else {
				throw new RuntimeException("Unknown action type");
			}

			String[] split = s.split(" ");
			if (split.length == 4) {
				// toggle
				instr.setTopLeft(parseCoord(split[1]));
				instr.setBottomRight(parseCoord(split[3]));
			} else if (split.length == 5) {
				// turn on / off
				instr.setTopLeft(parseCoord(split[2]));
				instr.setBottomRight(parseCoord(split[4]));
			}

			return instr;
		}

		public static Point parseCoord(String s) {
			String[] split = s.split(",");
			return new Point(Integer.parseInt(split[0]), Integer.parseInt(split[1]));
		}
	}
}