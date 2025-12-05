import java.io.IOException;

void main() throws IOException {
    var sample = Files.readAllLines(Path.of("inputs/day1_sample.txt"));
    var turns = Files.readAllLines(Path.of("inputs/day1.txt"));

    // part 1
    assert 3 == passwd(50, sample);
    assert 1123 == passwd(50, turns);

    // part 2
    assert 6 == passwd2(50, sample);
    assert 6695 == passwd2(50, turns);
}

// Counts number of times dial is zero after turn
int passwd(int dial, List<String> turns) {
    int passwd = 0;

    for (var turn : turns) {
        var ticks = Integer.parseInt(turn.substring(1));
        if (turn.startsWith("L")) {
            dial -= ticks;
        } else {
            dial += ticks;
        }

        dial = dial % 100;

        if (dial == 0) {
            passwd += 1;
        }
    }

    return passwd;
}

// Counts number of times dial crosses 0 across turns
int passwd2(int dial, List<String> turns) {
    int zeros = 0;

    for (var turn : turns) {
        var ticks = Integer.parseInt(turn.substring(1));
        var incr = turn.startsWith("R") ? 1 : -1;

        for (int i = 0; i < ticks; i++) {
            dial += incr;
            if (dial % 100 == 0) {
                zeros++;
            }
        }
    }

    return zeros;
}