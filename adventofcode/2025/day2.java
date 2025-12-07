import static java.lang.IO.println;

void main() throws IOException {

    var v = new DefaultValidator();

    assert v.isValid(1188511880);
    assert !v.isValid(1188511885);

    var t1 = invalidIds(new Range(1188511880, 1188511890), v);
    assert 1 == t1.size();
    assert 1188511885 == t1.getFirst();

    assert v.isValid(101);

    List<Range> sampleRanges = parse("inputs/day2_sample.txt");
    List<Range> ranges = parse("inputs/day2.txt");

    partA(sampleRanges);
    partA(ranges);

    // in part b an id is invalid if it is made up of the same pattern
    // repeated at least twice
    // eg 11 is '1' repeated 2 times
    //    999 is '9' repeated 3 times
    //    824824824 is '824' repeated 3 times
    var v2 = new RepeatValidator();

    assert 2 == v2.split("11", 1).size();
    assert 1 == v2.split("11", 2).size();
    assert 2 == v2.split("111", 2).size();
    assert 3 == v2.split("824824824", 3).size();
    assert 1 == v2.split("11", 3).size();

    // 1 is repeated twice
//    assert !v2.isValid(11);
//    assert !v2.isValid(824824824);

//    partB(sampleRanges);
}

List<Long> invalidIds(Range r, Validator v) {
    var val = new ArrayList<Long>();
    for (long i = r.min(); i <= r.max(); i++) {

        // skip good ids
        if (v.isValid(i)) {
            continue;
        }

        // add bad id
        val.add(i);
    }
    return val;
}

interface Validator {
    boolean isValid(long id);

    // extract id to digit list
    default List<Integer> toDigitList(long id) {
        var val = new ArrayList<Integer>();

        while (id > 0) {
            val.add((int) (id % 10));
            id = id / 10;
        }

        return val;
    }
}

static class DefaultValidator implements Validator {
    @Override
    public boolean isValid(long id) {

        var buf = toDigitList(id);

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
        badIds.addAll(invalidIds(r, new DefaultValidator()));
    }

    // too low : 9283763888 :/
    // That's not the right answer; your answer is too low. If you're stuck, make sure you're using the full input data; there are also some general tips on the about page, or you can ask for hints on the subreddit. Please wait one minute before trying again. [Return to Day 2]
    // i had a bad cast to int when i was stuffing digits into my byte buffer :'(

    println(badIds.stream().reduce(0L, Long::sum));
}

void partB(List<Range> ranges) {
    var badIds = new ArrayList<Long>();
    for (Range r : ranges) {
        badIds.addAll(invalidIds(r, new RepeatValidator()));
    }
    println(badIds.stream().reduce(0L, Long::sum));
}

static class RepeatValidator implements Validator {
    @Override
    public boolean isValid(long id) {
        var s = Long.toString(id);
        int max = s.length() / 2;

        for (int i = 0; i < max; i++) {
            var chunks = split(s, i);
            var first = chunks.getFirst();
            boolean ok = chunks.stream().allMatch(c -> c.equals(first));
            if (ok) {
                return false;
            }
        }

        // id is ok
        // couldn't find a size where all chunks are the same
        return true;
    }

    // Returns list of strings of length n by splitting s
    List<String> split(String s, int n) {
        var val = new ArrayList<String>();

        int from = 0;
        while (true) {
            int to = from + n;
            if (to >= s.length()) {
                val.add(s.substring(from, s.length() - 1));
                break;
            }
            val.add(s.substring(from, to));
            from += n;
        }

        return val;
    }
}