package Calculate;

import Exceptions.NumberOfParametersException;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class FactorialTest implements OperationTest{

    @Test
    public void shouldBeCorrect() throws Exception {
        this.calculationOf("1", "1");
        this.calculationOf("2", "2");
        this.calculationOf("3", "6");
        this.calculationOf("4", "24");
        this.calculationOf("5", "120");
        this.calculationOf("6", "720");
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
    public void valueIsNotFloat() throws Exception{
        Factorial factorial = new Factorial();
        try {
            factorial.calculate(new BigDecimal("1.1"));
            factorial.calculate(new BigDecimal("2.5"));
            factorial.calculate(new BigDecimal("106.453"));
            fail("Es sollte eine Exception geworfen werden. Es wurde mindestens eine ungueltige Zahl angegeben.");
        }
        catch (ArithmeticException arithmeticException) {

        }
    }

    @Test
    public void notEnoughParameters() throws Exception {
        Factorial factorial = new Factorial();
        try {
            factorial.calculate();
            fail("Es sollte eine Exception geworfen werden. Es wurden nicht genug Parameter uebergeben.");
        }
        catch (NumberOfParametersException numberOfParameterException) {

        }
    }

    @Test
    public void tooManyParameters() throws Exception {
        Factorial factorial = new Factorial();
        try {
            factorial.calculate(new BigDecimal(1), new BigDecimal(1));
            factorial.calculate(new BigDecimal(1), new BigDecimal(1), new BigDecimal(1));
            factorial.calculate(new BigDecimal(1), new BigDecimal(1), new BigDecimal(1), new BigDecimal(1), new BigDecimal(1));
            fail("Es sollte eine Exception geworfen werden. Es wurden zu viele Parameter uebergeben.");
        }
        catch (NumberOfParametersException numberOfParameterException) {

        }
    }

    @Test
    public void operatorShouldBeValid() throws Exception {
        checkOperatorToCalculate("!", true);
    }

    @Test
    public void operatorShouldBeInvalid() throws Exception {
        for (String temp : Operators.listOfOperators){
            if (!temp.equals("!")){
                checkOperatorToCalculate(temp, false);
            }
        }
    }


    public void calculationOf(String... arguments) throws Exception {
        Factorial factorial = new Factorial();
        BigDecimal calculate = factorial.calculate(new BigDecimal(arguments[0]));
        BigDecimal expected = new BigDecimal(arguments[1]);
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