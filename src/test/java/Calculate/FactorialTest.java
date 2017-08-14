package Calculate;

import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class FactorialTest implements OperationTest{

    @Test
    public void shouldBeCorrect() throws Exception {
        this.factorialOf("1", "1");
        this.factorialOf("2", "2");
        this.factorialOf("3", "6");
        this.factorialOf("4", "24");
        this.factorialOf("5", "120");
        this.factorialOf("6", "720");
    }


    @Test
    public void notEnoughParameters() throws Exception {
        Factorial factorial = new Factorial();
        try {
            factorial.calculate();
            fail("Es sollte eine Exception geworfen werden. Es wurden nicht genug Parameter uebergeben.");
        }
        catch (NumberOfParameterException arithmeticException) {

        }
    }

    @Test
    public void valueTooLow() throws Exception{
        Factorial factorial = new Factorial();
        try {
            factorial.calculate(new BigDecimal(0));
            factorial.calculate(new BigDecimal(-1));
            factorial.calculate(new BigDecimal(-2));
            fail("Es sollte eine Exception geworfen werden. Es wurden nicht genug Parameter uebergeben.");
        }
        catch (ArithmeticException arithmeticException) {

        }
    }

    @Test
    public void valueIsFloat() throws Exception{
        Factorial factorial = new Factorial();
        try {
            factorial.calculate(new BigDecimal("1.1"));
            factorial.calculate(new BigDecimal("2.5"));
            factorial.calculate(new BigDecimal("106.453"));
            fail("Es sollte eine Exception geworfen werden. Es wurde eine ungueltige Zahl angegeben.");
        }
        catch (ArithmeticException arithmeticException) {

        }
    }

    @Test
    public void tooManyParameters() throws Exception {
        Factorial factorial = new Factorial();
        try {
            factorial.calculate(new BigDecimal(1), new BigDecimal(1));
            factorial.calculate(new BigDecimal(1), new BigDecimal(1), new BigDecimal(1));
            factorial.calculate(new BigDecimal(1), new BigDecimal(1), new BigDecimal(1), new BigDecimal(1), new BigDecimal(1));
            fail("Es sollte eine Exception geworfen werden. Es wurden nicht genug Parameter uebergeben.");
        }
        catch (NumberOfParameterException numberOfParameterException) {

        }
    }

    @Test
    public void operatorShouldBeValid() throws Exception {
        checkOperatorToCalculate("!", true);
    }

    @Test
    public void operatorShouldBeInvalid() throws Exception {
        checkOperatorToCalculate("+", false);
        checkOperatorToCalculate("*", false);
        checkOperatorToCalculate("/", false);
        checkOperatorToCalculate("-", false);
        checkOperatorToCalculate("sqrt", false);
        checkOperatorToCalculate("sqr", false);
        checkOperatorToCalculate("%", false);
        checkOperatorToCalculate("pow", false);

    }


    public void factorialOf(String leftParameter, String expectedResult) throws Exception {
        Factorial factorial = new Factorial();
        BigDecimal calculate = factorial.calculate(new BigDecimal(leftParameter));
        BigDecimal expected = new BigDecimal(expectedResult);
        String message = "\nExpected: " + expected.toString() + "\nActual: " + calculate.toString();
        assertEquals(message, 0, calculate.compareTo(expected));
    }

    public void checkOperatorToCalculate(String operator, boolean shouldBeCorrect) throws Exception {
        Factorial factorial = new Factorial();
        String message = "Operator: " + operator;
        if (shouldBeCorrect) {
            assertTrue(message, factorial.canCalculate(operator));
        }
        else {
            assertFalse(message, factorial.canCalculate(operator));
        }
    }
}