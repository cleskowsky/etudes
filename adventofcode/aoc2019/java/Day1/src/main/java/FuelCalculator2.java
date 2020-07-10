/**
 * This time when we calculate fuel needed,
 * we include fuel weights as modules until
 * the fuel is no longer adding substantive
 * weight ...
 */
public class FuelCalculator2 {
    public int fuelRequiredForMass(int m) {
        int sum = 0;
        while (true) {
            int fuelNeeded = m / 3 - 2;
            if (fuelNeeded <= 0) {
                return sum;
            }
            sum += fuelNeeded;
            m = fuelNeeded;
        }
    }
}
