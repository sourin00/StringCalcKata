import org.junit.jupiter.api.Test;
import org.apache.commons.lang3.StringUtils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CalculatorTest {

    @Test
    public void testCalculator() throws Exception {
        assertEquals(0, CalculatorTest.sum(""));
        assertEquals(1, CalculatorTest.sum("1"));
        assertEquals(3, CalculatorTest.sum("1,2"));
        assertEquals(6, CalculatorTest.sum("1\n3,2"));
        assertEquals(6, CalculatorTest.sum("1\n3,2\n "));
        assertEquals(6, CalculatorTest.sum("//;\n1;3;2; "));
        assertThrows(Exception.class, () -> CalculatorTest.sum("-1"), "negatives not allowed -1");
    }

    private static int sum(String s) throws Exception {
        if (StringUtils.isEmpty(s)) {
            return 0;
        }

        String delimiter;
        if (s.startsWith("//")) {
            delimiter = s.substring(2, 3);
            s = s.substring(3);
        } else {
            delimiter = "[,\n]";
        }

        String[] numbers = s.split(delimiter);
        int sum = 0;
        for (String number : numbers) {
            String trimmed = number.trim();
            if (StringUtils.isEmpty(trimmed)) {
                continue;
            }
            int num = Integer.parseInt(trimmed);
            if (num < 0) throw new Exception("negatives not allowed " + num);
            sum += num;
        }
        return sum;
    }

}
