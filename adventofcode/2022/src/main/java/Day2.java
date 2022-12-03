import java.util.List;

public class Day2 {

    public static final boolean PART_B = true;

    public static void main(String[] args) {
        var d = new Day2();

        List.of("in/day2_sample.txt",
                "in/day2.txt").forEach(in -> {
//            var sum = 0;
//            for (String s : FileUtils.readLines(in)) {
//                sum += d.score(new Round(s));
//            }
//            System.out.println(sum);

            // Streams style of summing vs looping
            // To me the loop version is more readable but the streaming
            // version lets me parallelize when the stream input is
            // independent. This seems powerful ...

            System.out.println(
                    FileUtils.readLines(in)
                            .stream()
//                            .parallel()
                            .map(x -> d.score(new Round(x)))
                            .reduce(0, Integer::sum)
            );
        });
    }

    /**
     * Calculate score for round
     */
    public int score(Round r) {
        var me = r.me() - 'X';
        var them = r.them() - 'A';

        if (PART_B) {
            // For part b we need to set me for a particular
            // as specified by the value in r.me()
            // X -> I should lose
            // Y -> I should draw
            // Z -> I should win

            var winningShape = (them + 1) % 3;
            me = switch (r.me()) {
                case 'X' -> (them + 2) % 3;
                case 'Y' -> them;
                case 'Z' -> winningShape;
                default -> throw new RuntimeException("Unknown shape");
            };
        }

        var losingShape = (me + 2) % 3;
        if (them == losingShape) {
            // win
            me += 6;
        }

        if (me == them) {
            // draw
            me += 3;
        }

        // result is 1-based
        return me + 1;
    }

    record Round(Character them, Character me) {
        Round(String s) {
            this(s.charAt(0), s.charAt(2));
        }
    }
}
