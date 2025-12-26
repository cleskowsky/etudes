import java.io.IOException;
import java.util.ArrayList;

void main() throws IOException {

    var sample = Files.readString(Path.of("inputs/day6_sample.txt"));
    var problems = problems(sample);
    assert 33210 == solve(problems.getFirst());

    String input = Files.readString(Path.of("inputs/day6.txt"));

    // part a

    var sum = 0L;
    for (Problem p : problems(input)) {
        sum += solve(p);
    }

    assert 4583860641327L == sum;

    // part b

    var sum2 = 0L;
    for (Problem p : problems2(input)) {
        sum2 += solve(p);
    }

    assert 11602774058280L == sum2;
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

List<Problem> problems2(String input) {

    List<Problem> val = new ArrayList<>();
    List<Long> nums = new ArrayList<>();
    char op = '+';

    // cols
    var rows = input.split("\n");
    for (int i = 0; i < rows[0].length(); i++) {
        // rows
        var num = "";
        for (int j = 0; j < rows.length; j++) {
            char c = rows[j].charAt(i);
            if (List.of('+', '*').contains(c)) {
                op = c;
            } else {
                num += c;
            }
        }

        // we've fully read a problem when we see an empty col
        num = num.trim();
        if (num.isEmpty()) {
            // new problem
            val.add(new Problem(nums, String.valueOf(op)));
            nums = new ArrayList<>();
        } else if (i == rows[0].length() - 1) {
            // last number, last problem
            nums.add(Long.parseLong(num));
            val.add(new Problem(nums, String.valueOf(op)));
        } else {
            // keep parsing problem
            nums.add(Long.parseLong(num));
        }
    }

    return val;
}
