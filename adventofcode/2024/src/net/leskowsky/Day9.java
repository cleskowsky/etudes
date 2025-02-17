package net.leskowsky;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Day9 {
    public static void main(String[] args) throws IOException {
        System.out.println("Day 9");

        var x = new Day9();
        x.example();
        x.part1();
    }

    void example() {
        System.out.println("example");

        String s = "2333133121414131402";

        System.out.println(unpack(s));
        assert unpack(s).equals("00...111...2...333.44.5555.6666.777.888899");
    }

    /**
     * Returns expanded string after compaction removed
     */
    String unpack(String s) {
        var result = new StringBuilder();

        int fileId = 0;
        boolean fileBlock = true;
        for (char c : s.toCharArray()) {
            if (fileBlock) {
                // add file blocks
                var fileName = String.valueOf(fileId);
                result.append(fileName.repeat(Character.getNumericValue(c)));
                fileBlock = false;

                fileId++;
            } else {
                // add free blocks
                result.append(".".repeat(Character.getNumericValue(c)));
                fileBlock = true;
            }
        }
        System.out.println("finished unpack");

        return result.toString();
    }

    void part1() throws IOException {
        // Finished a version of unpack that uses stringbuilder ...
        // There's no way I'm going to be able to upack the full
        // puzzle input in memory naively as a string right? There's
        // Likely a representation that's going to be easier on ram

        System.out.println("part1");

        var s = Files.readString(Path.of("inputs/day9.txt"));
        System.out.println(unpack(s));

        // Well this does work, but I've lost information about where
        // files begin and end (some free block runs are 0-length)
    }
}
