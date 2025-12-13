import static java.lang.IO.println;

void main() throws IOException {
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

    var sampleGrid = gridify(sample);
    assert '.' == sampleGrid.get(0, 0);
    assert '.' == sampleGrid.get(1, 0);
    assert '@' == sampleGrid.get(0, 1);

    // Rolls that are accessible have fewer than 4 rolls
    // neighbouring them (4 cardinal directions + diagonals)

    println("partA sample=" + accessible(rolls(sampleGrid), sampleGrid).size());

    var input = Files.readString(Path.of("inputs/day4.txt"));
    var grid = gridify(input);

    println("partA=" + accessible(rolls(grid), grid).size());

    partB(sampleGrid);
    partB(grid);
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

// Return all points with paper rolls on floor
List<Point> rolls(Grid g) {
    return g.keySet().stream()
            .filter(e -> g.get(e) == '@')
            .toList();
}

// Return number of rolls that are accessible
List<Point> accessible(List<Point> rolls, Grid g) {
    var val = new ArrayList<Point>();
    for (Point p : rolls) {
        var x = neighbours(p, g).stream()
                .filter(e -> g.get(e) == '@')
                .count();
        if (x < 4) {
            val.add(p);
        }
    }
    return val;
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

Grid remove(List<Point> rolls, Grid g) {
    var val = new Grid();
    val.putAll(g);
    for (Point p : rolls) {
        val.remove(p);
    }
    return val;
}

void partB(Grid g) {

    // Part b wants me to remove the accessible paper rolls and
    // then look for newly accessible ones on the floor
    //
    // I'll repeat until I can't find any accessible rolls
    //
    // How many did I remove?

    int cnt = 0;

    while (true) {
        // get rolls
        // find accessible ones
        var rolls = accessible(rolls(g), g);

        // if there are none, stop
        if (rolls.isEmpty()) {
            println(cnt);
            return;
        }

        // remove them from g
        g = remove(rolls, g);
        cnt += rolls.size();
    }
}
