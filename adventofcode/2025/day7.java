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

    List<Beam> beams = new ArrayList<>();
    List<Point> newBeams = new ArrayList<>();
    newBeams.add(g.findFirst("S").get());
    Set<Point> seen = new HashSet<>();

    assert newBeams.getFirst().x() == 7;
    assert newBeams.getFirst().y() == 0;

    // part a

    int timesSplitterIsEntered = 0;
    while (!newBeams.isEmpty()) {
        var startingAt = newBeams.removeFirst();
        if (seen.contains(startingAt)) {
            // we've already traced this beam
            continue;
        }
        seen.add(startingAt);

        var result = traceBeam(startingAt, g);
        if (DEBUG) {
            System.out.println(result);
        }
        beams.add(result.beam());

        if (seen.containsAll(result.newBeams())) {
            // it's possible a split returns beams we've
            // already seen before ...
        } else {
            timesSplitterIsEntered++;
        }
        newBeams.addAll(result.newBeams());
    }

    System.out.println("Found beams: " + beams);
    System.out.println("Number of splits: " + timesSplitterIsEntered);
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
    var newBeams = new ArrayList<Point>();

    boolean finished = false;
    while (!finished) {
        var next = new Point(points.getLast().x(), points.getLast().y() + 1);

        if (g.get(next) == null) {
            // manifold end
            if (DEBUG) {
                System.out.printf("Stopping at manifold end (%d, %d)%n", next.x(), next.y());
            }
            finished = true;
        } else if (g.get(next).equals("^")) {
            // splitter
            if (DEBUG) {
                System.out.printf("Stopping at splitter (%d, %d)%n", next.x(), next.y());
            }
            var left = new Point(next.x() - 1, next.y());
            var right = new Point(next.x() + 1, next.y());
            newBeams.add(left);
            newBeams.add(right);
            finished = true;
        } else {
            // empty space
            if (DEBUG) {
                System.out.printf("Adding beam point (%d, %d)%n", next.x(), next.y());
            }
            points.add(next);
        }
    }

    return new TraceResult(new Beam(points), newBeams);
}

record TraceResult(Beam beam, List<Point> newBeams) {
}

final boolean DEBUG = true;