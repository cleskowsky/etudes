import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FuelCalculator2Test {

    @Test
    void fuelRequiredForMass() {
        FuelCalculator2 fc = new FuelCalculator2();
        assertEquals(50346, fc.fuelRequiredForMass(100756));
        assertEquals(966, fc.fuelRequiredForMass(1969));
    }
}