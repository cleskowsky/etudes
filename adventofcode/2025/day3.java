import static java.lang.IO.println;

void main() throws IOException {

    var sampleData = Files.readAllLines(Path.of("inputs/day3_sample.txt"));
    var data = Files.readAllLines(Path.of("inputs/day3.txt"));

    assert 98 == jolts("987654321111111");

    // find joltages in power supply
    assert 357 == partA(sampleData);
    assert 17034 == partA(data);

    println(partA(sampleData));

    // i can pass in a range to look for the next biggest battery in
    // i should reserve the number of batteries left to choose from the
    // right side of the bank so we'll always be able to pick n batteries
    //
    // always picking the next biggest battery after the last should get
    // me the max joltage in a bank since those are most significant
    System.out.println("987654321111111".length());
    println(maxJoltsInRange("987654321111111", 0, 14));
    println(maxJoltsInRange("987654321111111", 1, 15));
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