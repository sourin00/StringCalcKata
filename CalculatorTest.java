import org.junit.jupiter.api.Test;
import org.apache.commons.lang3.StringUtils;

import java.util.regex.Pattern;

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
        assertEquals(6, CalculatorTest.sum("//****\n1****3****2"));
        assertEquals(6, CalculatorTest.sum("//[**][++]\n1**3++2"));
    }

    private static int sum(String s) throws Exception {
        if (StringUtils.isEmpty(s)) {
            return 0;
        }

        StringBuilder delimiter;
        if (s.startsWith("//")) {
            int firstNwLineInd = s.indexOf('\n');
            delimiter = new StringBuilder(s.substring(2, firstNwLineInd));
            String delimsTxt = delimiter.toString().replace("][", ",");
            if (delimiter.length()==delimiter.length()){
                delimiter = new StringBuilder(Pattern.quote(delimsTxt));
            }else {
                delimsTxt = delimsTxt.substring(1, delimsTxt.length() - 1);
                String[] delims = delimsTxt.split(",");
                delimiter = new StringBuilder();
                for (String delim : delims) {
                    delimiter.append(Pattern.quote(delim)).append("|");
                }
                delimiter.deleteCharAt(delimiter.length() - 1);
            }
            s = s.substring(firstNwLineInd + 1);
        } else {
            delimiter = new StringBuilder(",\n");
        }

        String[] numbers = s.split("[" + delimiter + "]");
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
            if (num > 1000) {
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
