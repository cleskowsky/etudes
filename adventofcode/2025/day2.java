void main() throws IOException {

    List<Range> sampleRanges = parse("inputs/day2_sample.txt");
    List<Range> ranges = parse("inputs/day2.txt");

    assert 1227775554 == partA(sampleRanges);
    assert 31000881061L == partA(ranges);

    assert 4174379265L == partB(sampleRanges);
    assert 46769308485L == partB(ranges);
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

long partA(List<Range> ranges) {
    var badIds = new ArrayList<Long>();
    for (Range r : ranges) {
        for (long i = r.min(); i <= r.max(); i++) {
            if (!isValidId(i)) {
                badIds.add(i);
            }
        }
    }
    return badIds.stream().reduce(0L, Long::sum);
}

long partB(List<Range> ranges) {
    var badIds = new ArrayList<Long>();
    for (Range r : ranges) {
        for (long i = r.min(); i <= r.max(); i++) {
            if (!isValidId2(i)) {
                badIds.add(i);
            }
        }
    }
    return badIds.stream().reduce(0L, Long::sum);
}

boolean isValidId(long id) {
    var s = Long.toString(id);
    int mid = s.length() / 2;

    var left = s.substring(0, mid);
    var right = s.substring(mid);

    return !left.equals(right);
}

boolean isValidId2(long id) {
    var s = Long.toString(id);
    int mid = s.length() / 2;

    for (int i = mid; i > 0; i--) {
        var chunks = split(s, i);
        var first = chunks.getFirst();
        if (chunks.stream().allMatch(chunk -> chunk.equals(first))) {
            return false;
        }
    }
    return true;
}

List<String> split(String s, int n) {
    var val = new ArrayList<String>();

    int from = 0;
    while (true) {
        int to = from + n;
        if (to >= s.length()) {
            val.add(s.substring(from));
            break;
        }
        val.add(s.substring(from, to));
        from += n;
    }

    return val;
}