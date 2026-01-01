void main() {

    var sample = """
            .......S.......
            ...............
            .......^.......
            ...............
            ......^.^......
            ...............
            .....^.^.^.....
            ...............
            ....^.^...^....
            ...............
            ...^.^...^.^...
            ...............
            ..^...^.....^..
            ...............
            .^.^.^.^.^...^.
            ...............""";

    // i'll try finding all the beams
    // first beam enters the manifold at (S)
    // beam entering splitter creates 2 new beams (^)
    // beams always flow down
    // beams end at a splitter or when they would leave the manifold

    var g = Grid.gridify(sample);
    assert g.getRows() == 16;
    assert g.getCols() == 15;
    assert g.get(new Point(12, 12)).equals("^");
    assert g.get(new Point(14, 15)).equals(".");
    assert g.get(new Point(13, 14)).equals("^");

    List<Point> seen = new ArrayList<>();
    seen.add(g.findFirst("S").get());
    assert seen.getFirst().x() == 7;
    assert seen.getFirst().y() == 0;

    List<Beam> beams = new ArrayList<>();
    while (!seen.isEmpty()) {
        var tr = traceBeam(seen.removeFirst(), g);
        System.out.println(tr);
        beams.add(tr.beam());
    }

    System.out.println("Found beams: " + beams);
}

record Beam(List<Point> points) {
}

// Returns beam and new beam start points if found
TraceResult traceBeam(Point startingAt, Grid g) {

    // a beam goes to the bottom of the manifold
    // or to the first splitter below it
    // everything between start and stop points are part of the beam

    // the beam
    var points = new ArrayList<Point>();
    if (DEBUG) {
        System.out.printf("Adding beam point (%d, %d)%n", startingAt.x(), startingAt.y());
    }
    points.add(startingAt);

    // new beam start points if we hit a splitter
    var found = new ArrayList<Point>();

    boolean finished = false;
    while (!finished) {
        var next = new Point(points.getLast().x(), points.getLast().y() + 1);

        if (g.get(next).equals("^")) {
            // splitter
            if (DEBUG) {
                System.out.printf("Stopping at splitter (%d, %d)%n", next.x(), next.y());
            }
            var left = new Point(next.x() - 1, next.y());
            var right = new Point(next.x() + 1, next.y());
            found.add(left);
            found.add(right);
            finished = true;
        } else if (g.get(next) == null) {
            // manifold end
            if (DEBUG) {
                System.out.printf("Stopping at manifold end (%d, %d)%n", next.x(), next.y());
            }
            finished = true;
        } else {
            // empty space
            if (DEBUG) {
                System.out.printf("Adding beam point (%d, %d)%n", next.x(), next.y());
            }
            points.add(next);
        }
    }

    return new TraceResult(new Beam(points), new ArrayList<Point>());
}

record TraceResult(Beam beam, List<Point> newBeams) {
}

final boolean DEBUG = true;