void main() throws IOException {

    String sample = """
            3-5
            10-14
            16-20
            12-18
            
            1
            5
            8
            11
            17
            32""";

    // part a

    var freshIds = rangify(sample);
    var ingredients = ingredients(sample);
    assert 3 == partA(ingredients, freshIds);

    String input = Files.readString(Path.of("inputs/day5.txt"));
    assert 643 == partA(ingredients(input), rangify(input));

    // part b wants me to enumerate all available ingredient ids
    // i can probably merge overlapping ranges and then sum the differences
    // of the ranges left ...
    //
    // does sorting by r.begin() help?

    System.out.println(partB(rangify(sample)));
    System.out.println(partB(rangify(input)));
}

record Range(long begin, long end) {
    boolean contains(long id) {
        return id >= begin && id <= end;
    }
}

// Returns a list of ranges from s (eg 1-10)
List<Range> rangify(String s) {
    var val = new ArrayList<Range>();

    for (String line : s.split("\n")) {
        var split = line.split("-");
        if (split.length == 2) {
            var range = new Range(Long.parseLong(split[0]), Long.parseLong(split[1]));
            val.add(range);
        }
    }

    return val;
}

// Returns a list of ingredient ids from s
List<Long> ingredients(String s) {
    var val = new ArrayList<Long>();

    for (String line : s.split("\n")) {
        try {
            var id = Long.parseLong(line);
            val.add(id);
        } catch (NumberFormatException e) {
            // Not a number ...
        }
    }

    return val;
}

// Returns the number of fresh ingredients found in input
int partA(List<Long> ingredientIds, List<Range> freshIdRanges) {
    int cnt = 0;

    for (var id : ingredientIds) {
        var fresh = freshIdRanges.stream().anyMatch(r -> r.contains(id));
        if (fresh) {
            cnt++;
        }
    }

    return cnt;
}

long partB(List<Range> freshIds) {

    System.out.println("partB");

    freshIds.sort(Comparator.comparingLong(Range::begin));
    System.out.println(freshIds);

    int size = freshIds.size();
    freshIds = merge(freshIds);
    while (freshIds.size() != size) {
        size = freshIds.size();
        freshIds = merge(freshIds);
    }

    System.out.println(freshIds);

    return freshIds.stream()
            .map(r -> r.end() - r.begin() + 1)
            .reduce(0L, Long::sum);
}

List<Range> merge(List<Range> freshIds) {
    var val = new ArrayList<Range>();

    for (int i = 0; i < freshIds.size(); i++) {
        var left = freshIds.get(i);
        if (i + 1 >= freshIds.size()) {
            val.add(left);
            continue;
        }
        var right = freshIds.get(i + 1);
        if (left.end() >= right.begin()) {
            // Left and right are mergeable
            // Since we've made a new range, we
            // won't add the original ranges to
            // our new merge list
            var merged = new Range(
                    left.begin(),
                    Math.max(left.end(), right.end())
            );
            val.add(merged);
            System.out.printf("merged left_begin=%d left_end=%d right_begin=%d right_end=%d merged_begin=%d merged_end=%d\n",
                    left.begin(), left.end(),
                    right.begin(), right.end(),
                    merged.begin(), merged.end());
            i++;
        } else {
            // Can't merge the 2 ranges
            // I know left range is not mergeable
            // but I don't know about right yet (it
            // could merge with its forward sibling)
            val.add(left);
        }
    }

    return val;
}
