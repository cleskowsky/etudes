package a;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class ComputerTest {
    @Test
    public void diagCodeShouldBeZero() {
        List<Integer> prg = Arrays.asList(3, 0, 4, 0, 99);
        Computer c = new Computer();
        assertEquals(1, c.compute(prg));
    }

    @Test
    public void programHalts() {
        // an opcode with param types packed in
        List<Integer> prg = Arrays.asList(1002, 4, 3, 4, 33);
        Computer c = new Computer();
        assertEquals(-1, c.compute(prg));
    }

    @Test
    public void programWithANegativeParameterHalts() {
        List<Integer> prg = Arrays.asList(1101, 100, -1, 4, 0);
        Computer c = new Computer();
        assertEquals(-1, c.compute(prg));
    }
}
