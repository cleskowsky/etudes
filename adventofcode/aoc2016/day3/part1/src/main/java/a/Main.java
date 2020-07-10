package a;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
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
        List<Triangle> data = new ArrayList<>();

        try {
            Scanner s = new Scanner(new File(fn));
            Pattern p = Pattern.compile("\\d+");
            for (String line = s.nextLine(); s.hasNextLine(); line = s.nextLine()) {
                Matcher m = p.matcher(line);
                m.find();
                int s1 = Integer.parseInt(m.group());
                m.find();
                int s2 = Integer.parseInt(m.group());
                m.find();
                int s3 = Integer.parseInt(m.group());
                data.add(new Triangle(s1, s2, s3));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return data;
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

//        int[] t = { 5, 10, 25};
//        System.out.println(a.isItATriangle(t));
    }
}
