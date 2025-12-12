void main() throws IOException {

    var sampleData = Files.readAllLines(Path.of("inputs/day3_sample.txt"));
    var data = Files.readAllLines(Path.of("inputs/day3.txt"));

    assert 357 == partA(sampleData);
    assert 17034 == partA(data);

    assert 3121910778619L == partB(sampleData);
    assert 168798209663590L == partB(data);
}

long partA(List<String> supply) {

    long sum = 0;
    for (var bank : supply) {
        // find 2 batteries
        sum += jolts(bank, 2);
    }
    return sum;
}

long partB(List<String> supply) {

    // i'm always looking for the biggest battery to the right of the
    // last one i picked reserving enough batteries in the tail to be
    // able to pick n - 1 more

    long sum = 0;
    for (var bank : supply) {
        // find 12 batteries
        sum += jolts(bank, 12);
    }
    return sum;
}

// Return jolts for n batteries picked from bank
long jolts(String bank, int n) {

    int from = 0;
    String jolts = "";

    for (int i = n - 1; i >= 0; i--) {
        var b = maxJoltsInRange(bank, from, bank.length() - i);
        jolts += b.jolts();
        from = b.idx() + 1;
    }

    return Long.parseLong(jolts);
}

record Battery(int jolts, int idx) {
}

Battery maxJoltsInRange(String bank, int from, int to) {
    int jolts = 0;
    int idx = 0;
    for (int i = from; i < to; i++) {
        var x = Character.digit(bank.charAt(i), 10);
        if (x > jolts) {
            jolts = x;
            idx = i;
        }
    }
    return new Battery(jolts, idx);
}