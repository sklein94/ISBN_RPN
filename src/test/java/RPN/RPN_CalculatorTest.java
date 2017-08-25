package RPN;

import Calculate.Operation;
import org.junit.Test;
import org.reflections.Reflections;

import java.math.BigDecimal;
import java.util.Set;

import static org.junit.Assert.*;

public class RPN_CalculatorTest {
    @Test
    public void test() throws Exception {
        Reflections reflections = new Reflections("Calculate");
        Set<Class<? extends Operation>> classes = reflections.getSubTypesOf(Operation.class);
        for (Class o : classes) {
            o.newInstance();
        }

        calculate("1 1 +", "2");
        calculate("1 1 -", "0");
        calculate("1 1 *", "1");
        calculate("1 1 /", "1");
        calculate("1 1 pow", "1");
        calculate("4 sqr", "16");
        calculate("16 sqrt", "4");
        calculate("2 !", "2");
        calculate("3 fac", "6");

    }


    private void calculate(String stringToCalculate, String expectedResult) throws Exception {
        String result = RPN_Calculator.calculateString(stringToCalculate).stripTrailingZeros().toString();
        String message = "\nString: " + stringToCalculate + "\nExpected: " + expectedResult + "\nActual: " + result;
        assertTrue(message, result.equals(expectedResult));
    }
}