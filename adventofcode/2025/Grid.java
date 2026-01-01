import java.util.HashMap;
import java.util.Optional;

public class Grid extends HashMap<Point, String> {

    private int rows;
    private int cols;
    
    public static Grid gridify(String s) {

        var lines = s.split("\n");

        var g = new Grid();
        g.rows = lines.length;
        g.cols = lines[0].length();
        System.out.printf("grid rows=%d cols=%d\n", lines.length, lines[0].length());

        for (int i = 0; i < lines.length; i++) {
            var line = lines[i];
            for (int j = 0; j < line.length(); j++) {
                g.put(new Point(j, i), line.substring(j, j + 1));
            }
        }

        return g;
    }
    
    public String get(int x, int y) {
        return get(new Point(x, y));
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }
    
    public Optional<Point> findFirst(String val) {
        return keySet().stream()
                .filter(p -> get(p).equals(val))
                .findFirst();
    }
    
    public String toString() {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                var c = get(j, i);
                sb.append(c);
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}