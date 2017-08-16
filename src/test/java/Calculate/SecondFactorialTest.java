package Calculate;

import Exceptions.NumberOfParametersException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.math.BigDecimal;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.*;

public class SecondFactorialTest implements OperationTest{
    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void shouldBeCorrect() throws Exception {
        this.secondFactorialOfValuesIsCorrect("0", "1");

        this.secondFactorialOfValuesIsCorrect("1", "1");
        this.secondFactorialOfValuesIsCorrect("2", "2");
        this.secondFactorialOfValuesIsCorrect("3", "6");

        this.secondFactorialOfValuesIsCorrect("10", "3628800");
    }

    @Test
    public void invalidValues() throws Exception{
        this.failOnThisArgument("-1");
        this.failOnThisArgument("-10000");

        this.failOnThisArgument("1.5");
        this.failOnThisArgument("3.67895678");
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
        checkOperatorToCalculate("fac", true);
    }

    @Test
    public void operatorShouldBeInvalid() throws Exception {
        for (String temp : Operators.listOfOperators){
            if (!temp.equals("fac")){
                checkOperatorToCalculate(temp, false);
            }
        }
    }

    public void failOnThisArgument(String argument) throws Exception{
        SecondFactorial secondFactorial = new SecondFactorial();
        BigDecimal leftParameterBD = new BigDecimal(argument);

        expectedException.expect(ArithmeticException.class);

        secondFactorial.calculate(leftParameterBD);
    }

    public void failOnThisNumberOfArguments(BigDecimal... arguments) throws Exception {
        SecondFactorial secondFactorial = new SecondFactorial();

        expectedException.expect(NumberOfParametersException.class);
        expectedException.expectMessage(equalTo("Parameters: " + arguments.length));

        secondFactorial.calculate(arguments);
    }


    public void secondFactorialOfValuesIsCorrect(String... arguments) throws Exception {
        SecondFactorial secondFactorial = new SecondFactorial();
        BigDecimal calculate = secondFactorial.calculate(new BigDecimal(arguments[0]));
        BigDecimal expected = new BigDecimal(arguments[1]);
        String message = "\nExpected: " + expected.toString() + "\nActual: " + calculate.toString();

        assertEquals(message, 0, calculate.compareTo(expected));
    }


    public void checkOperatorToCalculate(String operator, boolean shouldBeCorrect) throws Exception {
        SecondFactorial secondFactorial = new SecondFactorial();
        String message = "Operator: " + operator;
        if (shouldBeCorrect) {
            assertTrue(message, secondFactorial.canCalculate(operator));
        }
        else {
            assertFalse(message, secondFactorial.canCalculate(operator));
        }
    }
}