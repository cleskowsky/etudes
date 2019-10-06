package a;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    class Triangle {
        int s1, s2, s3;

        public Triangle(int s1, int s2, int s3) {
            this.s1 = s1;
            this.s2 = s2;
            this.s3 = s3;
        }

        /**
         * Returns true if the sum of all possible combinations
         * of 2 sides in t are larger than the third
         */
        public boolean isValid() {
            // This is probably going to bite me later but the number
            // of sides is low enough that the number of possible
            // combinations is 3 ...
            if (s1 + s2 > s3 &&
                    s2 + s3 > s1 &&
                    s1 + s3 > s2) {
                return true;
            }
            return false;
        }

        @Override
        public String toString() {
            return "Triangle{" +
                    "s1=" + s1 +
                    ", s2=" + s2 +
                    ", s3=" + s3 +
                    '}';
        }
    }

    /**
     * Parses my input file
     * <p>
     * This seems like a lot of work to get at 3 numbers
     * in a line ...
     * <p>
     * I must be doing something harder than I need to here
     * Right?!
     */
    List<Triangle> parse(String fn) {
        // My triangles
        List<Triangle> triangles = new ArrayList<>();

        // I have to read triangles in columns for part 2
        Map<String, List<Integer>> data = new HashMap<>();
        data.put("A", new ArrayList<>());
        data.put("B", new ArrayList<>());
        data.put("C", new ArrayList<>());

        // Read in data
        try {
            Scanner s = new Scanner(new File(fn));
            Pattern p = Pattern.compile("\\d+");
            while (s.hasNextLine()) {
            String line = s.nextLine();
                Matcher m = p.matcher(line);
                m.find();
                data.get("A").add(Integer.parseInt(m.group()));
                m.find();
                data.get("B").add(Integer.parseInt(m.group()));
                m.find();
                data.get("C").add(Integer.parseInt(m.group()));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // Build triangles
        List<String> cols = Arrays.asList("A", "B", "C");
        for (String c : cols) {
            List<Integer> sides = data.get(c);
            System.out.println(sides.size());
            while (!sides.isEmpty()) {
                int s1 = sides.remove(0);
                int s2 = sides.remove(0);
                int s3 = sides.remove(0);
                triangles.add(new Triangle(s1, s2, s3));
            }
        }

        return triangles;
    }

    public static void main(String[] args) {
        Main a = new Main();
        List<Triangle> in = a.parse("input.txt");

        int n = 0;
        for (Triangle t : in) {
            if (t.isValid()) {
                System.out.println(t);
                n++;
            }
        }
        System.out.println("Number of good triangles: " + n);
    }
}
