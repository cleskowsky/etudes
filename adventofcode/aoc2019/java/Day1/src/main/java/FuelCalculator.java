import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FuelCalculator {
    /**
     * Returns fuel required to lift mass m
     *
     * @param m Mass
     * @return Fuel amount
     */
    public int fuelRequiredForMass(int m) {
        return m / 3 - 2;
    }

    public static void main(String[] args) throws IOException {
        // Input
        List<Integer> modules = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("in.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                modules.add(Integer.parseInt(line));
            }
        }

        // Part 1
        FuelCalculator fc = new FuelCalculator();
        int fuelNeeded = 0;
        for (int m : modules) {
            fuelNeeded += fc.fuelRequiredForMass(m);
        }
        System.out.println("Fuel needed: " + fuelNeeded);

        // Part 2
        FuelCalculator2 fc2 = new FuelCalculator2();
        fuelNeeded = 0;
        for (int m :
                modules) {
            fuelNeeded += fc2.fuelRequiredForMass(m);
        }
        System.out.println("Fuel needed part 2: " + fuelNeeded);
        // 5019725 is too low
        // 5019643 How is this possible? I changed the
        // fuel needed test in fc2 from < 3 to <= 0 ...
    }
}
