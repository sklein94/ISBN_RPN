package Calculate;

import Exceptions.NumberOfParametersException;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class FactorialTest implements OperationTest{

    @Test
    public void shouldBeCorrect() throws Exception {
        this.calculationOf("0", "1");

        this.calculationOf("1", "1");
        this.calculationOf("2", "2");
        this.calculationOf("3", "6");

        this.calculationOf("10", "3628800");
    }

    @Test
    public void valueTooLow() throws Exception{
        this.failOnThisArgument("-1");
        this.failOnThisArgument("-2");
        this.failOnThisArgument("-10");
    }

    @Test
    public void valueIsNotFloat() throws Exception{
        this.failOnThisArgument("1.1");
        this.failOnThisArgument("2.5");
        this.failOnThisArgument("106.453");
    }

    @Test
    public void notEnoughParameters() throws Exception {
        this.failOnThisNumberOfArguments();
    }

    @Test
    public void tooManyParameters() throws Exception {
        this.failOnThisNumberOfArguments(new BigDecimal(1), new BigDecimal(1));
        this.failOnThisNumberOfArguments(new BigDecimal(1), new BigDecimal(1), new BigDecimal(1));
        this.failOnThisNumberOfArguments(new BigDecimal(1), new BigDecimal(1), new BigDecimal(1), new BigDecimal(1), new BigDecimal(1));
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

    public void failOnThisArgument(String argument){
        Factorial factorial = new Factorial();
        BigDecimal argumentAsBigDecimal = new BigDecimal(argument);
        try {
            factorial.calculate(argumentAsBigDecimal);
            fail("Es wurde ein ungueltiger Parameter akzeptiert.");
        }
        catch (Exception e) {
        }
    }

    public void failOnThisNumberOfArguments(BigDecimal... arguments) throws Exception {
        Factorial factorial = new Factorial();
        try {
            factorial.calculate(arguments);
            fail("Es wurde eine falsche Anzahl an Parametern akzeptiert..");
        }
        catch (NumberOfParametersException numberOfParameterException) {

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