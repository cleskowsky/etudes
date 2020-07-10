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

    public int sumCheck(String s) {
        List<List<Integer>> data = parseInput(s);
        int sum = 0;
        for (List<Integer> row : data) {
            sum += Collections.max(row) - Collections.min(row);
        }
        return sum;
    }

    public int dividesCheck(String s) {
        List<List<Integer>> data = parseInput(s);
        int sum = 0;
        for (List<Integer> row : data) {
            for (int i = 0; i < row.size(); i++) {
                int a = row.get(i);
                for (int j = 0; j < row.size(); j++) {
                    if (i == j) {
                        continue;
                    }
                    int b = row.get(j);
                    if (a % b == 0) {
                        sum += (a / b);
                    }
                }
            }
        }
        return sum;
    }

    private List<List<Integer>> parseInput(String s) {
        String[] lines = s.split("\n");
        List<List<Integer>> data = new ArrayList<List<Integer>>();
        for (String l : lines) {
            data.add(parseInts(l));
        }
        return data;
    }
}
