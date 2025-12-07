import static java.lang.IO.println;

void main() throws IOException {

    var checker = new DefaultChecker();

    assert checker.check(1188511880);
    assert !checker.check(1188511885);

    var t1 = invalidIds(new Range(1188511880, 1188511890));
    assert 1 == t1.size();
    assert 1188511885 == t1.getFirst();

    assert checker.check(101);

    List<Range> sampleRanges = parse("inputs/day2_sample.txt");
    List<Range> ranges = parse("inputs/day2.txt");

    partA(sampleRanges);
    partA(ranges);
}

List<Long> invalidIds(Range r) {
    var val = new ArrayList<Long>();
    for (long i = r.min(); i <= r.max(); i++) {

        // skip good ids
        var checker = new DefaultChecker();
        if (checker.check(i)) {
            continue;
        }

        // add bad id
        val.add(i);
    }
    return val;
}

interface Checker {
    boolean check(long id);

    // extract id to digit list
    default List<Integer> makeDigitList(long id) {
        var val = new ArrayList<Integer>();

        while (id > 0) {
            val.add((int) (id % 10));
            id = id / 10;
        }

        return val;
    }
}

static class DefaultChecker implements Checker {
    @Override
    public boolean check(long id) {

        var buf = makeDigitList(id);

        // ids with an odd number of digits are valid
        if (buf.size() % 2 == 1) {
            return true;
        }

        // look at pairs starting at 0, mid
        // an id is valid when we find a pair of different numbers
        for (int i = 0, j = buf.size() / 2; j < buf.size(); i++, j++) {
            int left = buf.get(i);
            int right = buf.get(j);
            if (left != right) {
                return true;
            }
        }

        // we're not valid so we must be invalid
        return false;
    }
}

record Range(long min, long max) {
}

List<Range> parse(String file) {
    var val = new ArrayList<Range>();

    try {
        String data = Files.readString(Path.of(file));
        for (String range : data.split(",")) {
            String[] split = range.split("-");
            var min = Long.parseLong(split[0]);
            var max = Long.parseLong(split[1]);
            val.add(new Range(min, max));
        }
    } catch (IOException e) {
        throw new RuntimeException(e);
    }

    return val;
}

void partA(List<Range> ranges) {
    var badIds = new ArrayList<Long>();
    for (Range r : ranges) {
        badIds.addAll(invalidIds(r));
    }

    // too low : 9283763888 :/
    // That's not the right answer; your answer is too low. If you're stuck, make sure you're using the full input data; there are also some general tips on the about page, or you can ask for hints on the subreddit. Please wait one minute before trying again. [Return to Day 2]
    // i had a bad cast to int when i was stuffing digits into my byte buffer :'(

    println(badIds.stream().reduce(0L, Long::sum));
}

void partB(List<Range> ranges) {
    var badIds = new ArrayList<Long>();
    for (Range r : ranges) {
        badIds.addAll(invalidIds(r));
    }
    println(badIds.stream().reduce(0L, Long::sum));
}