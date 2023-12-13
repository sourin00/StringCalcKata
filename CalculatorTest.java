import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculatorTest {

    @Test
    public void testCalculator() {
        assertEquals(0, CalculatorTest.sum(""));
    }

}
