import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FuelCalculatorTest {

    @Test
    void fuelRequiredForMass() {
        Map<Integer, Integer> tt = Map.of(
                12, 2,
                14, 2,
                1969, 654,
                100756, 33583
        );

        FuelCalculator fc = new FuelCalculator();

        for (int mass : tt.keySet() ) {
            Integer expectedFuelRequired = tt.get(mass);
            assertEquals(expectedFuelRequired, fc.fuelRequiredForMass(mass));
        }
    }
}