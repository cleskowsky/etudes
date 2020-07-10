package advent;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class App {
    List<Light> lights;

    public App(List<Light> lights) {
        this.lights = lights;
    }

    private static App Input(String fn) {
        List<Light> lights = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fn))) {
            String line;
            while ((line = br.readLine()) != null) {
                List<Integer> nums = Integers(line);
                lights.add(new Light(
                        nums.get(0),
                        nums.get(1),
                        nums.get(2),
                        nums.get(3)
                ));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new App(lights);
    }

    /**
     * Extract numbers from a string
     *
     * @param s A string containing numbers
     * @return An array of numbers
     */
    private static List<Integer> Integers(String s) {
        Pattern p = Pattern.compile("-?\\d+");
        Matcher m = p.matcher(s);
        List<Integer> nums = new ArrayList<>();
        while (m.find()) {
            nums.add(Integer.parseInt(m.group()));
        }
        return nums;
    }

    public static void main(String[] args) {
        System.out.println("hello, world");

        App a = App.Input("in.txt");

        Box b = a.box();

        int n = 0;
        while (true) {
            a.tick();
            n++;

            if (a.box().area() <= b.area()) {
                // our box is getting smaller ... keep going
                b = a.box();
            } else {
                break;
            }
        }

        // to detect we've gone one step too far, we have to actually go to far
        // this makes our message unreadable
        // undo the extra forward movement
        a.untick();

        a.message();
        System.out.println(n);
    }

    public void tick() {
        for (Light l : lights) {
            l.tick();
        }
    }

    public void untick() {
        for (Light l : lights) {
            l.untick();
        }
    }

    /**
     * Calculate bounding box for points
     *
     * @return
     */
    public Box box() {
        int minx = lights.get(0).getX();
        int miny = lights.get(0).getY();
        int maxx = lights.get(0).getX();
        int maxy = lights.get(0).getY();
        for (Light l : lights) {
            if (l.getX() < minx) {
                minx = l.getX();
            }
            if (l.getY() < miny) {
                miny = l.getY();
            }
            if (l.getX() > maxx) {
                maxx = l.getX();
            }
            if (l.getY() > maxy) {
                maxy = l.getY();
            }
        }
        return new Box(minx, miny, maxx, maxy);
    }

    public void message() {
        String[][] s = new String[300][300];
        for (int i = 0; i < s.length; i++) {
            for (int j = 0; j < s[i].length; j++) {
                s[i][j] = ".";
            }
        }
        for (Light l : lights) {
            s[l.getY()][l.getX()] = "X";
        }
        for (String[] row : s) {
            System.out.println(String.join("", row));
        }
    }
}

class Light {
    private int x, y;
    private int dx, dy;

    Light(int x, int y, int dx, int dy) {
        this.x = x;
        this.y = y;
        this.dx = dx;
        this.dy = dy;
    }

    public void tick() {
        x += dx;
        y += dy;
    }

    public void untick() {
        x -= dx;
        y -= dy;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}

class Box {
    long x1, y1; // bottom left
    long x2, y2; // top right

    public long area() {
        return (x2 - x1) * (y2 - y1);
    }

    public Box(long x1, long y1, long x2, long y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }
}