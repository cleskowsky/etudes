package adventofcode;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Unit test for simple App.
 */
public class AppTest {
    @Test
    public void readImage() {
        Image i = new Image("123456789012", 3, 2);
        assertThat(i.getLayers().size(), equalTo(2));
        assertThat(i.getLayer(1), equalTo("789012"));
    }

    @Test
    public void part1() {
        // Find layer with fewest 0 digits
        String layerWithMinZeros = "";
        long minZeros = Integer.MAX_VALUE;
        for (String s : puzzleImage.getLayers()) {
            int n = countOccurrencesOf(s, '0');
            if (n < minZeros) {
                layerWithMinZeros = s;
                minZeros = n;
            }
        }

        // Count 1s
        long nOnes = countOccurrencesOf(layerWithMinZeros, '1');

        // Count 2s
        long nTwos = countOccurrencesOf(layerWithMinZeros, '2');

        // Answer is # of 1s * # of 2s
        System.out.println(nOnes * nTwos);

        // First try: 2420 which is too high apparently ... :/

        // I see 100 layers here which is good
        System.out.println("Number of layers: " + puzzleImage.getLayers().size());

        // 2375 ... silly. Forgot to set minZeros to the found layer's
        // minZero count :)
    }

    private int countOccurrencesOf(String s, char c) {
        return (int) s.chars()
                .filter(x -> x == c)
                .count();
    }

    @Test
    public void part2() {
        String decodedImage = "";

        // Scan pixels in layer 0
        // 0 or 1 -> solid colour: Add to decodedImage
        // 2 -> transparent: Look for a solid color in layer 2, 3, ...
        String firstLayer = puzzleImage.getLayer(0);
        for (int i = 0; i < firstLayer.length(); i++) {
            char c = firstLayer.charAt(i);
            if (c == '2') {
                // Look for a non-transparent char in a lower layer
                for (String l : puzzleImage.getLayers()) {
                    c = l.charAt(i);
                    if (c != '2') {
                        break;
                    }
                }
            }
            decodedImage += c;
        }

        new Image(decodedImage, 25, 6).printLayer(0);
    }

    Image puzzleImage;

    @Before
    public void setup() throws IOException {
        String s = Files.readString(Path.of("input.txt"));
        puzzleImage = new Image(s, 25, 6);
    }
}
