import java.util.HashMap;
import java.util.Map;

public class Main {
    private String encode(String s) {
        s = s.toLowerCase();
        Map<Character, Integer> seen = new HashMap<>();
        for (Character c : s.toCharArray()) {
            seen.putIfAbsent(c, 0);
            seen.put(c, seen.get(c) + 1);
        }

        String encoded = "";
        for (Character c : s.toCharArray()) {
            if (seen.get(c) == 1) {
                encoded += "(";
            } else {
                encoded += ")";
            }
        }

        return encoded;
    }

    public static void main(String[] args) {
        System.out.println("hello, world");
        Main m = new Main();
        assert "(((".equals(m.encode("din"));
        assert "()()()".equals(m.encode("recede"));
        assert ")())())".equals(m.encode("Success"));
        assert "))((".equals(m.encode("(( @"));
    }
}
