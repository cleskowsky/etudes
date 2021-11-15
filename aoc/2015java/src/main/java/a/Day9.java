package a;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.Data;

public class Day9 {

	public static boolean PART_B = false;

	public static void main(String[] args) {
		if (args.length == 1 && args[0].equals("partb")) {
			PART_B = true;
		}

//		Map<Route, Integer> distances = Input("9sample.txt");
		Map<Route, Integer> distances = Input("9.txt");

		List<String> locations = new ArrayList<>();
		for (Route r : distances.keySet()) {
			if (!locations.contains(r.getBegin())) {
				locations.add(r.getBegin());
			}
			if (!locations.contains(r.getEnd())) {
				locations.add(r.getEnd());
			}
		}

		List<FlightPath> paths = new ArrayList<>();
		permute(locations.size(), locations, paths);

		int min = Integer.MAX_VALUE;
		int max = Integer.MIN_VALUE;
		for (FlightPath fp : paths) {
			int sum = 0;
			for (int i = 0; i < fp.getLocations().size() - 1; i++) {
				String x = fp.getLocations().get(i);
				String y = fp.getLocations().get(i + 1);
				sum += distances.get(new Route(x, y));
			}
			if (PART_B) {
				if (sum > max) {
					max = sum;
					System.out.printf("New max: %s %d%n", fp, sum);
				}
			} else {
				if (sum < min) {
					min = sum;
					System.out.printf("New min: %s %d%n", fp, sum);
				}
			}
		}
	}

	static void permute(int k, List<String> a, List<FlightPath> x) {
		if (k == 1) {
			x.add(new FlightPath(List.copyOf(a)));
		} else {
			for (int i = 0; i < k; i++) {
				permute(k - 1, a, x);
				if (k % 2 == 0) {
					swap(a, i, k - 1);
				} else {
					swap(a, 0, k - 1);
				}
			}
		}
	}

	static void swap(List<String> a, int i, int j) {
		String x = a.get(i);
		a.set(i, a.get(j));
		a.set(j, x);
	}

	@Data
	static class Route {
		String begin;
		String end;

		public Route(String begin, String end) {
			this.begin = begin;
			this.end = end;
		}
	}

	static Map<Route, Integer> Input(String fileName) {
		Map<Route, Integer> m = new HashMap<>();
		try {
			List<String> lines = Files.readAllLines(Path.of("input", fileName));
			for (String s : lines) {
				// Expect: London to Belfast = 518
				String[] split = s.split(" = ");
				int d = Integer.parseInt(split[1]);
				split = split[0].split(" to ");
				// May travel between locations in either direction
				m.put(new Route(split[0], split[1]), d);
				m.put(new Route(split[1], split[0]), d);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return m;
	}

	@Data
	static class FlightPath {
		List<String> locations;

		public FlightPath(List<String> locations) {
			this.locations = locations;
		}
	}
}
