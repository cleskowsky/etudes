import java.io.IOException;

void main() throws IOException {
    // var turns = Files.readAllLines(Path.of("inputs/day1.txt"));
    var turns = Files.readAllLines(Path.of("inputs/day1_sample.txt"));

    int dial = 50;
    int passwd = 0;

    for (var turn : turns) {
        var clicks = Integer.parseInt(turn.substring(1));
        if (turn.startsWith("L")) {
            for (int i = 0; i < clicks; i++) {
                dial -= 1;
            }
        } else if (turn.startsWith("R")) {
            for (int i = 0; i < clicks; i++) {
                dial += 1;
            }
        } else {
            throw new RuntimeException("Bad turn direction: " + turn.substring(0, 1));
        }

        dial = dial % 100;

        if (dial == 0) {
            passwd += 1;
        }
    }

    System.out.println(passwd);
}