package a;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Day3 {
	public static void main(String[] args) throws Exception {
		List<String> lines = Files.readAllLines(Path.of("inputs/3.txt"));
		char[][] map = new char[lines.size()][];
		for (int i = 0; i < lines.size(); i++) {
			map[i] = lines.get(i).toCharArray();
		}
		
		// Part a
		int dy = 1, dx = 3;
		System.out.println(countTreesInPath(map, dy, dx) + " Parta answer");
		
		// Part b
		int[][] routes = {
			// dy, dx
			{1, 1},
			{1, 3},
			{1, 5},
			{1, 7},
			{2, 1}
		};
		long x = 1;
		for (int[] r : routes) {
			x *= countTreesInPath(map, r[0], r[1]);
		}
		System.out.println(x + " Partb answer");
	}
	
	private static long countTreesInPath(char[][] map, int dy, int dx) {
		int numTrees = 0;
		int x = 0, y = 0;
		while (y < map.length) {
			if (map[y][x] == '#') {
				numTrees++;
			}
			y += dy;
			x = (x + dx) % map[0].length;
		}
		System.out.println(numTrees);
		return numTrees;
	}
}
