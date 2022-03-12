package a.day9;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class App {
	public static void main(String[] args) throws IOException {
//		HeightMap heightMap = InputParser.readFromFile("in/9_sample.txt");
		HeightMap heightMap = InputParser.readFromFile("in/9.txt");
		int sum = 0;
		for (Point p : heightMap.enumerate()) {
			var val = heightMap.getHeightAt(p);
			if (heightMap.getNeighbourHeightsOf(p).stream().allMatch(x -> val < x)) {
				sum += 1 + val;
			}
		}
		System.out.println(sum);
	}

	static public class InputParser {
		public static HeightMap readFromFile(String fileName) throws IOException {
			HeightMap heightMap = new HeightMap();
			var lines = Files.readAllLines(Path.of(fileName));
			for (int y = 0; y < lines.size(); y++) {
				var row = lines.get(y);
				for (int x = 0; x < row.length(); x++) {
					var c = row.charAt(x);
					heightMap.addPoint(x, y, Character.getNumericValue(c));
				}
			}
			return heightMap;
		}
	}
}
