import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Grid {

    Map<Point, Integer> children = new HashMap<>();

    int minx = 0;
    int miny = 0;
    int maxx = 0;
    int maxy = 0;

    Point head;
    Point tail;

    public Grid add(Point p) {
        var x = children.getOrDefault(p, 0) + 1;
        children.put(p, x);
        if (p.x() < minx) {
            minx = p.x();
        }
        if (p.y() < miny) {
            miny = p.y();
        }
        if (p.x() > maxx) {
            maxx = p.x();
        }
        if (p.y() > maxy) {
            maxy = p.y();
        }
        return this;
    }

    public Grid addPoints(List<Point> l) {
        l.forEach(x -> {
            add(x);
        });
        return this;
    }

    public String toString() {
        var ret = "";
        for (int i = miny; i <= maxy; i++) {
            var row = "";
            for (int j = minx; j <= maxx; j++) {
                var x = new Point(j, i);
                if (x.equals(head)) {
                    row += "H";
                } else if (x.equals(tail)) {
                    row += "T";
                } else {
                    row += children.getOrDefault(new Point(j, i), 0);
                }
            }
            ret += row + "\n";
        }
        return ret.trim();
    }

    public Grid head(Point p) {
        head = p;
        return this;
    }

    public Grid tail(Point p) {
        tail = p;
        return this;
    }
}
