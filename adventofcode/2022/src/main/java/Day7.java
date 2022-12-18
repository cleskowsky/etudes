import java.util.ArrayList;
import java.util.List;

public class Day7 {
    public static final int TOTAL_DISK = 70000000;
    public static final int UPGRADE_SIZE = 30000000;

    public static void main(String[] args) {
//        File root = filesystem("in/day7_sample.txt");
        File root = parseInput("in/day7.txt");

        // Part 1
        System.out.println(directories(root).stream()
                .map(Day7::size)
                .filter(x -> x <= 100000)
                .reduce(0, Integer::sum));

        // Part 2
        var used = size(root);
        var avail = TOTAL_DISK - used;
        var need = UPGRADE_SIZE - avail;

        System.out.println(directories(root).stream()
                .map(Day7::size)
                .filter(x -> x >= need)
                .sorted()
                .findFirst());
    }

    /**
     * Return a list of directories in the filesystem
     */
    public static List<File> directories(File root) {
        var ret = new ArrayList<File>();
        ret.add(root);
        for (File f : root.children) {
            if (f.children.isEmpty()) {
                continue;
            }
            ret.addAll(directories(f));
        }
        return ret;
    }

    /**
     * Return size of filesystem tree at root
     */
    public static int size(File root) {
        int sum = root.size;
        for (File f : root.children) {
            sum += size(f);
        }
        return sum;
    }

    public static class File {
        private final int size;
        private final File parent;
        private final List<File> children = new ArrayList<>();

        public File(int size, File parent) {
            this.size = size;
            this.parent = parent;
        }
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
                var split = s.split(" ");
                cwd.children.add(new File(
                        Integer.parseInt(split[0]), cwd
                ));
            }
        }

        return root;
    }
}
