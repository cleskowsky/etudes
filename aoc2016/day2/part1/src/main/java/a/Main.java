package a;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This is another movement puzzle on a fixed size grid
 * With cells that are labelled
 * 1 2 3
 * 4 5 6
 * 7 8 9
 * <p>
 * Bathroom code as a series of finger presses:
 * ULL
 * RRDDD
 * LURDL
 * UUUUD
 */
public class Main {
    /**
     * Movement changes our current position
     * in well known ways depending on which
     * direction I should move in next
     * <p>
     * Any move that lands me greater than 9
     * or less than 1 I can ignore ...
     */
    static public Map<String, Integer> moves;
    static public List<String> badMoves;

    static {
        moves = new HashMap<>();
        moves.put("U", -3);
        moves.put("R", 1);
        moves.put("D", 3);
        moves.put("L", -1);

        // Sadly my simple arith solution can't easily
        // handle these pesky edges
        // If it doesn't get uglier than this I'm cool
        // with a badMove list
        //
        // I wonder what part 2 is going to want me to
        // do ... :)
        badMoves = List.of("4L", "7L", "3R", "6R" );
    }

    /**
     * Return the next button I should press to get into the bathroom
     * @param startKey the last button in my code
     * @param instructions the instructions my finger should make
     */
    protected int getNextCodeNumber(int startKey, String instructions) {
        System.out.println("Instructions: " + instructions);

        // The key my finger is hovering over
        int currentKey = startKey;

        for (Character c : instructions.toCharArray()) {
            // Skip known badMoves
            if (badMoves.contains(currentKey + c.toString())) {
                continue;
            }

            int nextKey = currentKey + moves.get(c.toString());
            System.out.println("Instruction: " + c + ", Current key: " + currentKey + ", Next key: " + nextKey);

            if (0 < nextKey && nextKey < 10) {
                // nextKey must be a key on the keypad
                // or ignore ...
                currentKey = nextKey;
            }
        }

        return currentKey;
    }

    public String getBathroomCode(List<String> instructions) {
        // My start key
        int key = 5;

        String code = "";

        for (String s : instructions) {
            key = getNextCodeNumber(key, s);
            code += key;
            System.out.println("Code number: " + key);
        }

        return code;
    }

    public static void main(String[] args) throws IOException {

        List<String> input = Files.readAllLines(Paths.get("input.txt"));
        Main a = new Main();
        System.out.println("Bathroom code: " + a.getBathroomCode(input));

        // 74586 is too high ... hrm
        // Ah I am allowing a few bad moves ...
    }
}
