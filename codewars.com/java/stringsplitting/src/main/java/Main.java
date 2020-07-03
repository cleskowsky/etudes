import java.util.ArrayList;
import java.util.List;

public class Main {
    public String[] splitString(String s) {
        // Splits a string into substrings
        // of length n

        if (s.length() % 2 == 1) {
            s += "_";
        }

        List<String> x = new ArrayList<>();
        for (int i = 0; i < s.length(); i += 2) {
            x.add(s.substring(i, i + 2));
        }

        return x.toArray(new String[x.size()]);
    }
    public static void main(String[] args) {
        System.out.println("Hello, world");
        Main m = new Main();
        System.out.println(m.splitString("hello"));
        assert 3 == m.splitString("hello").length;
        assert "he".equals(m.splitString("hello")[0]);
        assert "ll".equals(m.splitString("hello")[1]);
        assert "o_".equals(m.splitString("hello")[2]);
    }
}
