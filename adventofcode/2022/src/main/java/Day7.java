import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Day7 {
    public static final int TOTAL_DISK = 70000000;
    public static final int UPGRADE_SIZE = 30000000;

    public static void main(String[] args) {
//        File root = filesystem("in/day7_sample.txt");
        File root = filesystem("in/day7.txt");

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
        private final String name;
        private final int size;
        private final File parent;
        private List<File> children = new ArrayList<>();

        public File(String name, int size, File parent) {
            this.name = name;
            this.size = size;
            this.parent = parent;
        }

        /**
         * Find or create and return dir in this file
         */
        public File cd(String s) {
            if (s.equals("..")) {
                return parent == null ? this : parent;
            }

            for (File f : children) {
                if (f.name.equals(s)) {
                    return f;
                }
            }

            throw new RuntimeException("Couldn't find directory");
        }

        /**
         * Return this file's children
         * Notes:
         * - Only directories have children
         */
        public List<File> ls() {
            return Collections.unmodifiableList(children);
        }

        /**
         * Adds a file to filesystem
         */
        public void addFile(String s, int size) {
            children.add(new File(s, size, this));
        }

        /**
         * Adds a directory to filesystem
         */
        public void addDirectory(String s) {
            var d = new File(s, 0, this);
            children.add(d);
        }

        public String getName() {
            return name;
        }
    }

    public static File filesystem(String fileName) {
        File root = new File("root", 0, null);
        File cwd = root;

        for (String s : FileUtils.readLines(fileName)) {

            s = s.trim();

            if (s.startsWith("$ cd")) {
                var dirName = s.split(" ")[2];
                if (dirName.equals("/")) {
                    cwd = root;
                } else {
                    cwd = cwd.cd(dirName);
                }
            } else if (s.startsWith("$ ls")) {
                // no-op
            } else if (s.startsWith("dir")) {
                cwd.addDirectory(s.split(" ")[1]);
            } else {
                var split = s.split(" ");
                cwd.addFile(split[1], Integer.parseInt(split[0]));
            }
        }

        return root;
    }
}
