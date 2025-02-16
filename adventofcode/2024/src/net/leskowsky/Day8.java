package net.leskowsky;

import java.util.List;
import java.util.Map;

public class Day8 {
    record SignalMap(Map<Character, List<Point>> signals) {
        int count() {
            return signals.size();
        }

        List<Point> get(char c) {
            return signals.get(c);
        }
    }
}
