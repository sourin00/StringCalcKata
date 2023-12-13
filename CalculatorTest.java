import org.junit.jupiter.api.Test;
import org.apache.commons.lang3.StringUtils;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculatorTest {

    @Test
    public void testCalculator() {
        assertEquals(0, CalculatorTest.sum(""));
        assertEquals(1, CalculatorTest.sum("1"));
    }

    private static int sum(String s) {
        if (StringUtils.isEmpty(s)) {
            return 0;
        }
        int num = Integer.parseInt(s);
        return num;
    }

}
