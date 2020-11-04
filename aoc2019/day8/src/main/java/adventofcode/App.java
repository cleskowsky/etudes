package adventofcode;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        System.out.println("Hello World!");

        // Read input
        // Parse value of first pixel
        // Parse pixels Min first row
        // Parse rest of pixel rows

        // My password image is 25w x 6h
        // Each pixel is encoded as a number (0-9)
        // So row 1 is the first 25 digits of my input
        // Input length 15000, 25x6=150, my image has 100 layers
    }
}

@Data
class Image {
    String raw;
    int layerWidth;
    int layerHeight;
    List<String> layers;

    public Image(String raw, int w, int h) {
        this.raw = raw;
        this.layerWidth = w;
        this.layerHeight = h;
        this.layers = new ArrayList<>();

        int layerLength = layerWidth * layerHeight;
        int layerOffset = 0;
        for (int n = 1; layerOffset < raw.length(); n++) {
            layers.add(raw.substring(layerOffset, layerOffset + layerLength));
            layerOffset = n * layerLength;
        }
    }

    public String getLayer(int n) {
        return layers.get(n);
    }

    public void printLayer(int x) {
        String l = layers.get(x);
        int offset = 0;
        while (offset < l.length()) {
            System.out.println(l.substring(offset, offset + layerWidth).replace('0', ' '));
            offset += layerWidth;
        }
    }
}
