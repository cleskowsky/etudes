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

// Returns map of (x, y) coordinates to characters
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

void partA(Grid g) {
    println("partA");
    println(accessible(rolls(g)));
}

List<Point> rolls(Grid g) {
    return g.keySet().stream()
            .filter(e -> g.get(e) == '@')
            .toList();
}

int accessible(List<Point> rolls) {
    println(rolls);
    return 0;
}

record Point(int x, int y) {
}