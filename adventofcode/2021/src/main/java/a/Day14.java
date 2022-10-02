package a;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Day14b {

    public static PairCounter pairInsertion(Rules rules, PairCounter pairs) {
        PairCounter x = new PairCounter();

        // foreach pair xy
        for (var pair : pairs.keySet()) {
            long pairCount = pairs.get(pair);

            // find the char to be inserted n
            var insertChar = rules.get(pair);

            // create 2 new pairs xn,ny
            String lhs = "" + pair.charAt(0) + insertChar;
            String rhs = "" + insertChar + pair.charAt(1);

            // add number of new pairs to counter equal to the number of xy pairs
            x.put(lhs, pairCount + x.getOrDefault(lhs, 0L));
            x.put(rhs, pairCount + x.getOrDefault(rhs, 0L));
        }
        return x;
    }

    public static PairCounter getPairs(String s) {
        var pairs = new PairCounter();
        for (int i = 0; i < s.length() - 1; i++) {
            // the pair
            var x = "" + s.charAt(i) + s.charAt(i + 1);
            pairs.put(x, pairs.getOrDefault(x, 0L) + 1);
        }
        return pairs;
    }

    public static LetterCounter getSingles(PairCounter pairs, String polymer) {
        var letters = pairs.letters();
        var letterCounter = new LetterCounter();

        System.out.println(pairs);
        System.out.println(letters);

        for (char l : letters) {
            double sum = 0;
            for (char m : letters) {
                sum += pairs.getOrDefault("" + l + m, 0L) +
                        pairs.getOrDefault("" + m + l, 0L);
            }
            sum /= 2.0;
            if (l == polymer.charAt(0)) {
                sum += 0.5;
            }
            if (l == polymer.charAt(polymer.length() - 1)) {
                sum += 0.5;
            }
            letterCounter.put(l, (long) sum + letterCounter.getOrDefault(l, 0L));
        }
        System.out.println(letterCounter);
        return letterCounter;
    }
}

class PairCounter extends HashMap<String, Long> {
    public Set<Character> letters() {
        Set<Character> s = new HashSet<>();
        keySet().forEach(k -> {
            s.add(k.charAt(0));
            s.add(k.charAt(1));
        });
        return s;
    }
}

class LetterCounter extends HashMap<Character, Long> {
    public long max() {
        return values().stream().max(Long::compare).get();
    }

    public long min() {
        return values().stream().min(Long::compare).get();
    }
}

class Rules extends HashMap<String, Character> {
}
