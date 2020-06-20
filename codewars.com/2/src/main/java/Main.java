import java.util.HashMap;
import java.util.Map;

public class Main {
    private boolean isPangram(String s) {
        Map letters = new HashMap<>();
        for (Character c : s.toCharArray()) {
            if (Character.isLetter(c)) {
                letters.put(Character.toLowerCase(c), 1);
            }
        }
        return 26 == letters.keySet().size();
    }

    public static void main(String[] args) {
        System.out.println("hello world");
        Main m = new Main();
        assert m.isPangram("The quick brown fox jumps over the lazy dog.");
        assert ! m.isPangram("You shall not pass!");
    }
}
