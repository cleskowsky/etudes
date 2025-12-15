import java.io.IOException;
import java.util.ArrayList;

void main() throws IOException {

    String sample = """
            123 328  51 64
             45 64  387 23
              6 98  215 314
            *   +   *   +""";

    var problems = problems(sample);
    assert 33210 == solve(problems.getFirst());

    String input = Files.readString(Path.of("inputs/day6.txt"));

    // part a

    var sum = 0L;
    for (Problem p : problems(input)) {
        System.out.println(p);
        sum += solve(p);
    }
    System.out.println(sum);

    // too low : 1130536495
}

record Problem(List<Long> nums, String op) {
}

// Parses input data into a list of problems (not solved)
List<Problem> problems(String input) {

    // rows in input are lists of numbers except for the last one
    // the last row is a list of operators
    // problems are stored column-wise
    // parse input into intermediate strings matrix first, and then
    // in a second pass parse columns to problems (ints, op)

    // build lookup table

    List<List<String>> t = new ArrayList<>();

    var lines = input.split("\n");
    for (String line : lines) {
        var row = Arrays.asList(line.trim().split("\s+"));
        t.add(row);
    }

    // build problems

    List<Problem> val = new ArrayList<>();

    for (int i = 0; i < t.getFirst().size(); i++) {

        List<Long> nums = new ArrayList<>();
        for (int j = 0; j < t.size() - 1; j++) {
            long n = Long.parseLong(t.get(j).get(i));
            nums.add(n);
        }

        val.add(new Problem(nums, t.get(t.size() - 1).get(i)));
    }

    return val;
}

long solve(Problem p) {

    if (p.op().equals("+")) {
        // add
        return p.nums().stream().reduce(0L, Long::sum);
    } else {
        // mult
        return mult(p.nums());
    }
}

long mult(List<Long> nums) {
    return nums.stream().reduce(1L, (a, b) -> a * b);
}
