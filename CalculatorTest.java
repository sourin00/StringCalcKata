import org.junit.jupiter.api.Test;
import org.apache.commons.lang3.StringUtils;

import static org.junit.jupiter.api.Assertions.*;

public class CalculatorTest {

    @Test
    public void testCalculator() throws Exception {
        assertEquals(0, CalculatorTest.sum(""));
        assertEquals(1, CalculatorTest.sum("1"));
        assertEquals(3, CalculatorTest.sum("1,2"));
        assertEquals(6, CalculatorTest.sum("1\n3,2"));
        assertEquals(6, CalculatorTest.sum("1\n3,2\n "));
        assertEquals(6, CalculatorTest.sum("//;\n1;3;2; "));
        Exception e1 = assertThrows(Exception.class, () -> CalculatorTest.sum("-1"));
        assertTrue(e1.getMessage().contains("negatives not allowed -1"));
        Exception e2 = assertThrows(Exception.class, () -> CalculatorTest.sum("-1,-2,-3"));
        assertTrue(e2.getMessage().contains("negatives not allowed -1,-2,-3"));
        assertEquals(2, CalculatorTest.sum("1001,2,10009"));
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
        boolean isNegativeNumber = false;
        StringBuilder negativeNumbStr = new StringBuilder("negatives not allowed ");
        for (String number : numbers) {
            String trimmed = number.trim();
            if (StringUtils.isEmpty(trimmed)) {
                continue;
            }
            int num = Integer.parseInt(trimmed);
            if (num < 0) {
                isNegativeNumber = true;
                negativeNumbStr.append(num).append(',');
            }
            if (num > 1000){
                continue;
            }
            sum += num;
        }
        if (isNegativeNumber) {
            throw new Exception(negativeNumbStr.deleteCharAt(negativeNumbStr.length() - 1).toString());
        }
        return sum;
    }

}
