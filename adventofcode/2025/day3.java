import static java.lang.IO.println;

void main() throws IOException {

    var sampleData = Files.readAllLines(Path.of("inputs/day3_sample.txt"));
    var data = Files.readAllLines(Path.of("inputs/day3.txt"));

    assert 98 == jolts("987654321111111");

    // find joltages in power supply
    assert 357 == partA(sampleData);
    assert 17034 == partA(data);

    partB(sampleData);
    partB(data);
}

long partA(List<String> supply) {
    // for every bank
    long sum = 0;
    for (var line : supply) {
        // find 2 batteries
        sum += jolts(line);
    }

    return sum;
}

// Returns max joltage in battery bank
long jolts(String bank) {
    long max = 0;

    // first battery
    for (int i = 0; i < bank.length() - 1; i++) {
        var left = Character.digit(bank.charAt(i), 10) * 10;

        // second battery
        for (int j = i + 1; j < bank.length(); j++) {
            var right = Character.digit(bank.charAt(j), 10);
            if (left + right > max) {
                max = left + right;
            }
        }
    }

    return max;
}

void partB(List<String> supply) {

    // i'm always looking for the biggest battery to the right of the
    // last one i picked reserving enough batteries in the tail to be
    // able to pick n - 1 more

    long sum = 0;

    for (var bank : supply) {

        int from = 0;
        int remaining = 12;
        String jolts = "";

        for (int i = remaining - 1; i >= 0; i--) {
            var b = maxJoltsInRange(bank, from, bank.length() - i);
            jolts += b.jolts();
            from = b.idx() + 1;
        }

        println(jolts);
        sum += Long.parseLong(jolts);
    }

    println(sum);
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