import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ChecksumCalc {
    private List<Integer> parseInts(String s) {
        ArrayList<Integer> nums = new ArrayList<Integer>();
        for (String x : s.split("\\s+")) {
            nums.add(Integer.parseInt(x));
        }
        return nums;
    }

    public int check(String s) {
        String[] lines = s.split("\n");
        int sum = 0;
        for (String l : lines) {
            List<Integer> numbers = parseInts(l);
            sum += Collections.max(numbers) - Collections.min(numbers);
        }
        return sum;
    }
}
