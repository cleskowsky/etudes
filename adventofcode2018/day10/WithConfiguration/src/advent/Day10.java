package advent;

import java.util.ArrayList;
import java.util.List;

public class Day10 {
    public static List<Light> Input(String f) {
        return new ArrayList<>();
    }

    public static void main(String[] args) {
        List<Light> lights = Input("in.txt");

        int t = 0;
        while (true) {
            Config c = new Config(lights, t);
            t++;
        }
    }
}

class Light {
    private long x, y;
    private long dx, dy;

    public Light(long x, long y, long dx, long dy) {
        this.x = x;
        this.y = y;
        this.dx = dx;
        this.dy = dy;
    }

    public Light atTime(int t) {
        return new Light(x + t * dx, y + t * dy, dx, dy);
    }
}

class Config {
    public static List<Light> getLightsAtTime(int t) {
        List<Light> lights = new ArrayList<>();
        for (Light l : lights) {
            this.lights.add(l.atTime(t));
        }
        this.t = t;
    }
}

class Box {
    int x1, y1;
    int x2, y2;

    public Box(List<Light> lights) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }
}