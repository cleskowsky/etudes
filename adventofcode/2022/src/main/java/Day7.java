import util.FileUtils;

import java.util.ArrayList;
import java.util.List;

public class Day7 {
    public static void main(String[] args) {
//        File root = filesystem("in/day7_sample.txt");
        File root = parseInput("in/day7.txt");

        // Part 1
        var part1 = new FileVisitor() {
            int total;

            @Override
            public void visit(File f) {
                if (f.size <= 100000) {
                    total += f.size;
                }
            }
        };
        root.walk(part1);
        System.out.println(part1.total);

        // Part 2
        var part2 = new FileVisitor() {
            int min;

            @Override
            public void visit(File f) {
                if (root.size - f.size < 70000000 - 30000000) {
                    if (min == 0 || f.size < min) {
                        min = f.size;
                    }
                }
            }
        };
        root.walk(part2);
        System.out.println(part2.min);
    }

    public static class File {
        private int size;
        private File parent;
        private List<File> children = new ArrayList<>();

        public File(int size, File parent) {
            this.size = size;
            this.parent = parent;
        }

        public void walk(FileVisitor v) {
            v.visit(this);
            for (File f : this.children) {
                f.walk(v);
            }
        }
    }

    interface FileVisitor {
        void visit(File f);
    }

    public static File parseInput(String fileName) {
        File root = new File(0, null);
        File cwd = root;

        for (String s : FileUtils.readLines(fileName)) {
            if (s.startsWith("$ cd")) {
                var dirName = s.split(" ")[2];
                if (dirName.equals("..")) {
                    cwd = cwd.parent;
                } else {
                    var f = new File(0, cwd);
                    cwd.children.add(f);
                    cwd = f;
                }
            } else if (s.startsWith("$ ls")) {
                // no-op
            } else if (s.startsWith("dir")) {
                // no-op
                // created when we change to it
            } else {
                var size = Integer.parseInt(s.split(" ")[0]);
                var f = cwd;
                while (f != null) {
                    f.size += size;
                    f = f.parent;
                }
            }
        }

        return root;
    }
}
