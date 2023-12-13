import org.junit.jupiter.api.Test;
import org.apache.commons.lang3.StringUtils;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculatorTest {

    @Test
    public void testCalculator() {
        assertEquals(0, CalculatorTest.sum(""));
        assertEquals(1, CalculatorTest.sum("1"));
        assertEquals(3, CalculatorTest.sum("1,2"));
        assertEquals(6, CalculatorTest.sum("1\n3,2"));
        assertEquals(6, CalculatorTest.sum("1\n3,2\n "));
        assertEquals(6, CalculatorTest.sum("//;\n1\n3,2\n "));
    }

    private static int sum(String s) {
        if (StringUtils.isEmpty(s)) {
            return 0;
        }
        String[] numbers = s.split("[,\n]");
        int sum = 0;
        for (String number : numbers) {
            String trimmed = number.trim();
            if (StringUtils.isEmpty(trimmed)) continue;
            int num = Integer.parseInt(trimmed);
            sum += num;
        }
        return sum;
    }

}
