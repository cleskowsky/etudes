import static java.lang.IO.println;

void main() throws IOException {

    var sampleData = Files.readAllLines(Path.of("inputs/day3_sample.txt"));
    var data = Files.readAllLines(Path.of("inputs/day3.txt"));

    assert 98 == maxJoltage("987654321111111");

    // find joltages in power supply
    println(totalJolts(sampleData));
    println(totalJolts(data));
}

int totalJolts(List<String> supply) {
    var sum = 0;
    for (var line : supply) {
        sum += maxJoltage(line);
        System.out.println(maxJoltage(line));
    }
    return sum;
}

// Returns max joltage in battery bank
int maxJoltage(String bank) {

    var jolts = 0;

    // first battery
    for (int i = 0; i < bank.length() - 1; i++) {
        var left = Character.digit(bank.charAt(i), 10) * 10;

        // second battery
        for (int j = i + 1; j < bank.length(); j++) {
            var right = Character.digit(bank.charAt(j), 10);
            if (left + right > jolts) {
                jolts = left + right;
            }
        }
    }

    return jolts;
}