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

    var freshIdRanges = rangify(sample);
    var ingredientIds = ingredients(sample);
    assert 3 == partA(ingredientIds, freshIdRanges);

    String input = Files.readString(Path.of("inputs/day5.txt"));
    assert 643 == partA(ingredients(input), rangify(input));
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
