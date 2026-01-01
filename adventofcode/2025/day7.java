
final boolean DEBUG = false;

void main() throws IOException {

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

    var sampleGrid = Grid.gridify(sample);

    assert sampleGrid.getRows() == 16;
    assert sampleGrid.getCols() == 15;

    assert sampleGrid.get(new Point(12, 12)).equals("^");
    assert sampleGrid.get(new Point(14, 15)).equals(".");
    assert sampleGrid.get(new Point(13, 14)).equals("^");

    var S = sampleGrid.findFirst("S").get();
    assert S.x() == 7;
    assert S.y() == 0;

    assert 21 == partA(sampleGrid);
    // too high: 1768
    // too low: 1432
    assert 1678 == partA(Grid.gridify(Files.readString(Path.of("inputs/day7.txt"))));
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

            // update grid
            g.put(left, "|");
            g.put(right, "|");

            finished = true;
        } else {
            // empty space
            if (DEBUG) {
                System.out.printf("Adding beam point (%d, %d)%n", next.x(), next.y());
            }
            points.add(next);

            // update grid
            g.put(next, "|");
        }
    }

    return new TraceResult(new Beam(points), newBeams);
}

record TraceResult(Beam beam, List<Point> newBeams) {
}

int partA(Grid g) {

    List<Beam> beams = new ArrayList<>();

    List<Point> newBeams = new ArrayList<>();
    newBeams.add(g.findFirst("S").get());

    // skip trace if we've seen the start point before
    var seen = new ArrayList<Point>();

    while (!newBeams.isEmpty()) {
        var start = newBeams.removeFirst();
        if (seen.contains(start)) {
            continue;
        }
        seen.add(start);

        var result = traceBeam(start, g);
        if (DEBUG) {
            System.out.println(result);
        }
        beams.add(result.beam());
        newBeams.addAll(result.newBeams());
    }

    return beamSplits(g);
}

int beamSplits(Grid g) {

    // find beams entering splitters

    int cnt = 0;

    var splitters = g.findAll("^");
    for (var p : splitters) {
        var top = g.get(p.x(), p.y() - 1);
        if (top.equals("|")) {
            // a beam enters the splitter
            cnt++;
        }
    }

    return cnt;
}