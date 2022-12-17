import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Day7 {
    public static void main(String[] args) {
        System.out.println(1);

        // Need a representation of the file system in my program
        File root = new File("root", null);
        File cwd = null;

        for (String s : FileUtils.readLines("in/day7_sample.txt")) {

            s = s.trim();

            // Handle change directory /
            // Handle change directory ..
            // Handle change directory [dirname]
            //   What happens when you try to cd into a file?
            if (s.startsWith("$ cd")) {
                var dirName = s.split(" ")[2];
                if (dirName.equals("/")) {
                    cwd = root;
                } else {
                    cwd = cwd.cd(dirName);
                }
            }

            // Handle list files
            if (s.startsWith("$ ls")) {
                // nothing to do here
            }

            // Handle file
            // Handle directory
            // Sum directories with size < 100,000 bytes

            if (s.startsWith("$")) {

            }
        }
    }

    public static class File {

        private final String name;
        private final File parent;
        private List<File> children = new ArrayList<>();
        private int size;

        public File(String name, File parent) {
            this.name = name;
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
            var f = new File(s, this);
            children.add(f);
            return f;
        }

        /**
         * Return this file's children
         * Notes:
         * - Only directories have children
         */
        public List<File> ls() {
            return Collections.unmodifiableList(children);
        }

        public String getName() {
            return name;
        }

        @Override
        public String toString() {
            return "File{" +
                    "parent=" + parent +
                    ", children=" + children +
                    ", size=" + size +
                    '}';
        }
    }
}
