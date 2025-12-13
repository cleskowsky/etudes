import static java.lang.IO.println;

void main() {
    String sample = """
            ..@@.@@@@.
            @@@.@.@.@@
            @@@@@.@.@@
            @.@@@@..@.
            @@.@@@@.@@
            .@@@@@@@.@
            .@.@.@.@@@
            @.@@@.@@@@
            .@@@@@@@@.
            @.@.@@@.@.""";

    var g = gridify(sample);
    assert '.' == g.get(0, 0);
    assert '.' == g.get(1, 0);
    assert '@' == g.get(0, 1);

    partA(g);
}

static class Grid extends HashMap<Point, Character> {
    Character get(int x, int y) {
        return get(new Point(x, y));
    }
}

record Point(int x, int y) {
    Point add(Point p) {
        return new Point(x + p.x(), y + p.y());
    }
}

// Return input as grid
Grid gridify(String s) {
    var g = new Grid();

    var lines = s.split("\n");
    System.out.printf("grid rows=%d cols=%d\n", lines.length, lines[0].length());

    for (int i = 0; i < lines.length; i++) {
        var line = lines[i];
        for (int j = 0; j < line.length(); j++) {
            g.put(new Point(j, i), line.charAt(j));
        }
    }

    return g;
}

// Rolls that are accessible have fewer than 4 rolls
// neighbouring them (4 cardinal directions + diagonals)
void partA(Grid g) {
    println("partA");
    println(accessible(rolls(g), g));
}

// Return all points with paper rolls on floor
List<Point> rolls(Grid g) {
    return g.keySet().stream()
            .filter(e -> g.get(e) == '@')
            .toList();
}

// Return number of rolls that are accessible
int accessible(List<Point> rolls, Grid g) {
    var cnt = 0;

    for (Point p : rolls) {
        var x = neighbours(p, g).stream()
                .filter(e -> g.get(e) == '@')
                .count();
        if (x < 4) {
            cnt++;
        }
    }

    return cnt;
}

List<Point> neighbours(Point p, Grid g) {
    ArrayList<Point> val = new ArrayList<>();

    for (Point h : headings) {
        var x = p.add(h);
        if (g.containsKey(x)) {
            val.add(x);
        }
    }

    return val;
}

List<Point> headings = List.of(
        new Point(-1, -1),
        new Point(0, -1),
        new Point(1, -1),
        new Point(-1, 0),
        new Point(1, 0),
        new Point(-1, 1),
        new Point(0, 1),
        new Point(1, 1)
);
