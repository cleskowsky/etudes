import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FuelCalculator {
    /**
     * Returns fuel required to lift mass m
     * @param m Mass
     * @return Fuel amount
     */
    public int fuelRequiredForMass(int m) {
        return m / 3 - 2;
    }

    public static void main(String[] args) throws IOException {
        FuelCalculator fc = new FuelCalculator();

        int fuelNeeded = 0;
        try (BufferedReader br = new BufferedReader(new FileReader("in.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                int moduleMass = Integer.parseInt(line);
                fuelNeeded += fc.fuelRequiredForMass(moduleMass);
            }
        }

        System.out.println("Fuel needed: " + fuelNeeded);
    }
}
