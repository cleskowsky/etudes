package a;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day14bTest {

    Day14.Input sampleInput;
    Day14.Input puzzleInput;

    @BeforeEach
    void setUp() throws Exception {
        sampleInput = Day14.Input.parse(
                Files.readString(Path.of("in/14s.txt"))
        );
        puzzleInput = Day14.Input.parse(
                Files.readString(Path.of("in/14.txt"))
        );
    }

    @Test
    void startAndEndWithSameElement() throws Exception {
        PairCounter pairs = Day14b.getPairs("NCBN");
        var x = Day14b.pairInsertion(sampleInput.rules, pairs);

        // Expect NBCHBBN
        // Getting {B=3, C=1, H=1, N=1}
        // *1 less N than I need

        var letterCount = Day14b.getSingles(x, "NCBN");
        assertEquals(3, letterCount.get('B'));
        assertEquals(1, letterCount.get('C'));
        assertEquals(1, letterCount.get('H'));
        assertEquals(2, letterCount.get('N'));
    }

    @Test
    void sample() {
        var pairs = Day14b.getPairs(sampleInput.template);
        for (int i = 0; i < 10; i++) {
            pairs = Day14b.pairInsertion(sampleInput.rules, pairs);
        }
        var letterCnt = Day14b.getSingles(pairs, sampleInput.template);
        assertEquals(1588, letterCnt.max() - letterCnt.min());
    }

    @Test
    void partA() {
        var pairs = Day14b.getPairs(puzzleInput.template);
        for (int i = 0; i < 10; i++) {
            pairs = Day14b.pairInsertion(puzzleInput.rules, pairs);
        }
        var letterCnt = Day14b.getSingles(pairs, puzzleInput.template);
        assertEquals(4244, letterCnt.max() - letterCnt.min());
    }

    @Test
    void partB() {
        var pairs = Day14b.getPairs(puzzleInput.template);
        for (int i = 0; i < 40; i++) {
            pairs = Day14b.pairInsertion(puzzleInput.rules, pairs);
        }
        var letterCnt = Day14b.getSingles(pairs, puzzleInput.template);
        System.out.println(letterCnt.max() - letterCnt.min());
        assertEquals(4807056953866L, letterCnt.max() - letterCnt.min());
    }
}