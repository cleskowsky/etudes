package net.leskowsky;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class InputTest {
    class TestCase {
        String input;
        Integer count;
        Integer sum;

        public TestCase(String input, Integer count, Integer sum) {
            this.input = input;
            this.count = count;
            this.sum = sum;
        }
    }

    @Test
    void Parse() {
        List<TestCase> tt = new ArrayList<>();
        tt.add(new TestCase("0 1 0", 1, 0)); // 1 meta item
        tt.add(new TestCase("0 2 1 1", 1, 2)); // 2 meta items

        // we have a bit of state to track here
        // parent node, and
        // consumed metadata values
        // how do we do this? :)
        // 1 1 is root : meta 1
        // 0 1 is a child : meta 2
        tt.add(new TestCase("1 1 0 1 2 1", 2, 3));

        // sample data from day 8
        tt.add(new TestCase("2 3 0 3 10 11 12 1 1 0 1 99 2 1 1 2", 4, 138));

        for (TestCase tc : tt) {
            Node n = Input.parse(tc.input);
            assertEquals(tc.count, n.count());
            assertEquals(tc.sum, n.sum());
        }
    }
}