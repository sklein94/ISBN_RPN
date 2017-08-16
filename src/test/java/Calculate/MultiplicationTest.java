package Calculate;

import Exceptions.NumberOfParametersException;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class MultiplicationTest implements OperationTest{

    @Test
    public void shouldBeCorrect() throws Exception {
        this.multiplicationOfValuesIsCorrect("1", "1", "1");
        this.multiplicationOfValuesIsCorrect("2", "2", "4");
        this.multiplicationOfValuesIsCorrect("3", "3", "9");

        this.multiplicationOfValuesIsCorrect("0", "0", "0");

        this.multiplicationOfValuesIsCorrect("1", "-1", "-1");
        this.multiplicationOfValuesIsCorrect("-2", "2", "-4");
        this.multiplicationOfValuesIsCorrect("-3", "-3", "9");

        this.multiplicationOfValuesIsCorrect("1.55", "2.45", "3.7975");
        this.multiplicationOfValuesIsCorrect("-1.15", "-1.15", "1.3225");
        this.multiplicationOfValuesIsCorrect("1.1255", "3.1255", "3.51775025");
        this.multiplicationOfValuesIsCorrect("1.111", "3.111", "3.456321");
    }

    @Test
    public void notEnoughParameters() throws Exception {
        this.failOnThisNumberOfArguments(new BigDecimal(1));
        this.failOnThisNumberOfArguments();
    }

    @Test
    public void tooManyParameters() throws Exception {
        this.failOnThisNumberOfArguments(new BigDecimal(1), new BigDecimal(1), new BigDecimal(1));
        this.failOnThisNumberOfArguments(new BigDecimal(1), new BigDecimal(1), new BigDecimal(1), new BigDecimal(1));
        this.failOnThisNumberOfArguments(new BigDecimal(1), new BigDecimal(1), new BigDecimal(1), new BigDecimal(1), new BigDecimal(1));
    }

    @Test
    public void operatorShouldBeValid() throws Exception {
        checkOperatorToCalculate("*", true);
    }

    @Test
    public void operatorShouldBeInvalid() throws Exception {
        for (String temp : Operators.listOfOperators){
            if (!temp.equals("*")){
                checkOperatorToCalculate(temp, false);
            }
        }
    }

    private void failOnThisNumberOfArguments(BigDecimal... arguments) throws Exception {
        Multiplication multiplication = new Multiplication();
        try {
            multiplication.calculate(arguments);
            fail("Es wurde eine falsche Anzahl an Parametern akzeptiert");
        }
        catch (NumberOfParametersException numberOfParameterException) {

        }
    }

    private void multiplicationOfValuesIsCorrect(String leftArgument, String rightArgument, String expectedValue) throws Exception {
        Multiplication multiplication = new Multiplication();
        BigDecimal calculate = multiplication.calculate(new BigDecimal(leftArgument),new BigDecimal(rightArgument));
        BigDecimal expected = new BigDecimal(expectedValue);
        String message = "\nExpected: " + expected.toString() + "\nActual: " + calculate.toString();

        assertEquals(message, 0, calculate.compareTo(expected));
    }

    private void checkOperatorToCalculate(String operator, boolean shouldBeCorrect) throws Exception {
        Multiplication multiplication = new Multiplication();
        String message = "Operator: " + operator;
        if (shouldBeCorrect) {
            assertTrue(message, multiplication.canCalculate(operator));
        }
        else {
            assertFalse(message, multiplication.canCalculate(operator));
        }
    }
}