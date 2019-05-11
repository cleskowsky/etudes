package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class App {
    public List<Point> input() {
        List data = new ArrayList();

        Scanner s = null;
        try {
            s = new Scanner(new File("input/in.txt"));
            while (s.hasNext()) {
                String line = s.nextLine();
                String[] parts = line.split(", ");
                Point p = new Point(
                        Integer.parseInt(parts[0]),
                        Integer.parseInt(parts[1])
                );
                data.add(p);
            }
        } catch (FileNotFoundException e) {
            // don't care
        } finally {
            if (s != null) {
                s.close();
            }
        }

        return data;
    }

    public static void main(String[] args) {
        App a = new App();
        List<Point> pts = a.input();

        // Get a bounding box for our coordinates (maxx, maxy)
        int maxx = 0, maxy = 0;
        for (Point pt : pts) {
            if (pt.getX() > maxx) {
                maxx = pt.getX();
            }
            if (pt.getY() > maxy) {
                maxy = pt.getY();
            }
        }
        System.out.println("maxx: " + maxx + " maxy: " + maxy);

        // Calc distances in our box
        // Track points nearest to those in pts
        Map<Point, Integer> ptAreas = new HashMap<>();
        for (int j = 0; j < maxy +1; j++) {
            String r = "";
            for (int i = 0; i < maxx +2; i++) {
                Point p = a.findNearestPoint(i, j, pts);
                r += p.getName() + " ";

                // accounting
                int cnt = ptAreas.getOrDefault(p, 0);
                ptAreas.put(p, cnt + 1);
            }
            System.out.println(r);
        }

        // Which pois are infinite?
        // Is this any poi that is on a boundary?
        // Should I add an extra row,col and look at pois on this?
        // Maybe I don't care and I eyeball for now, and
        // Hope for the best in part 2 ... :)
        for (Point p : ptAreas.keySet()) {
            System.out.printf("%s %d\n", p.getName(), ptAreas.get(p));
        }
    }

    public Point findNearestPoint(int x, int y, List<Point> pts) {
        Map<Integer, List<Point>> distances = new HashMap<>();

        for (Point p : pts) {
            int d = distance(x, y, p);
            if (distances.containsKey(d)) {
                distances.get(d).add(p);
            } else {
                List<Point> l = new ArrayList<>();
                l.add(p);
                distances.put(d, l);
            }
        }

        int dmin = Collections.min(distances.keySet());
        if (distances.get(dmin).size() > 1) {
            return Point.DOT_POINT;
        } else {
            return distances.get(dmin).get(0);
        }
    }

    public int distance(int x, int y, Point p1) {
        return Math.abs(p1.getX() - x) + Math.abs(p1.getY() - y);
    }
}
