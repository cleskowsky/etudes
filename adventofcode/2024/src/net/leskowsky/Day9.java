package net.leskowsky;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

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

        // unpack input
        var x = unpack(s);
        System.out.println(x);
        assert x.toString().equals("00...111...2...333.44.5555.6666.777.888899");

        // then compact
        compact(x);
        System.out.println(x);
        assert x.toString().equals("0099811188827773336446555566..............");
    }

    record Block(String fileId) {
        boolean free() {
            return fileId.equals(".");
        }
    }

    static class FileSystem {
        List<Block> blocks = new ArrayList<>();

        @Override
        public String toString() {
            var sb = new StringBuilder();
            for (var block : blocks) {
                sb.append(block.fileId());
            }
            return sb.toString();
        }

        boolean hasFree() {
            for (int i = 0; i < blocks.size(); i++) {
                if (blocks.get(i).free()) {
                    return true;
                }
            }
            return false;
        }

        int nextFree() {
            for (int i = 0; i < blocks.size(); i++) {
                if (blocks.get(i).free()) {
                    return i;
                }
            }
            throw new RuntimeException("Couldn't find free block");
        }
    }

    /**
     * Returns expanded string after compaction removed
     */
    FileSystem unpack(String s) {
        var result = new FileSystem();

        int fileId = 0;
        boolean fileBlock = true;
        for (char c : s.toCharArray()) {
            if (fileBlock) {
                // add file blocks
                for (int i = 0; i < Character.getNumericValue(c); i++) {
                    result.blocks.add(new Block(String.valueOf(fileId)));
                }
                fileBlock = false;
                fileId++;
            } else {
                // add free blocks
                for (int i = 0; i < Character.getNumericValue(c); i++) {
                    result.blocks.add(new Block("."));
                }
                fileBlock = true;
            }
        }
        System.out.println("finished unpack");

        return result;
    }

    void part1() throws IOException {
        // Finished a version of unpack that uses stringbuilder ...
        // There's no way I'm going to be able to upack the full
        // puzzle input in memory naively as a string right? There's
        // Likely a representation that's going to be easier on ram

        System.out.println("part1");

        var s = Files.readString(Path.of("inputs/day9.txt"));

        // Well this does work, but I've lost information about where
        // files begin and end (some free block runs are 0-length)
    }

    /**
     * Returns fs with free blocks packed by file data
     */
    void compact(FileSystem fs) {
        System.out.println("compact");

        int tail = fs.blocks.size() - 1;
        while (true) {
            // stop if there are no free data blocks
            if (!fs.hasFree()) {
                return;
            }

            // find next free block
            // stop when next free is past tail
            int nextFree = fs.nextFree();
            if (nextFree > tail) {
                return;
            }

            // swap free and data blocks
            Block b = fs.blocks.get(nextFree);
            fs.blocks.set(nextFree, fs.blocks.get(tail));
            fs.blocks.set(tail, b);

            // find next data block at fs end
            for (int i = tail; i >= 0; i--) {
                if (fs.blocks.get(i).free()) {
                    continue;
                }
                tail = i;
                break;
            }
        }
    }
}
