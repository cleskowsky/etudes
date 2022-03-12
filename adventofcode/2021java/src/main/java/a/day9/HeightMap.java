package a.day9;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class HeightMap {
	private Map<Point, Integer> data = new HashMap<>();

	public static List<Point> neighbours = List.of(
		new Point(0, -1), // top
		new Point(1, 0),  // right
		new Point(0, 1),  // bottom
		new Point(-1, 0)  // left
	);

	public void addPoint(int x, int y, int h) {
		data.put(new Point(x, y), h);
	}

	public Integer getHeightAt(Point p) {
		return data.get(p);
	}

	public List<Integer> getNeighbourHeightsOf(Point p) {
		var ret = new ArrayList<Integer>();
		for (Point nb : neighbours) {
			var h = getHeightAt(new Point(p.x() + nb.x(), p.y() + nb.y()));
			if (h == null) {
				continue;
			}
			ret.add(h);
		}
		return ret;
	}

	public Set<Point> enumerate() {
		return data.keySet();
	}
}
