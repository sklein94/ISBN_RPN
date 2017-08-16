package Calculate;

import Exceptions.NumberOfParametersException;
import ch.obermuhlner.math.big.BigDecimalMath;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.math.BigDecimal;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.*;

public class FactorialTest implements OperationTest{
    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void shouldBeCorrect() throws Exception {
        this.factorialOfValuesIsCorrect("0", "1");

        this.factorialOfValuesIsCorrect("1", "1");
        this.factorialOfValuesIsCorrect("2", "2");
        this.factorialOfValuesIsCorrect("3", "6");

        this.factorialOfValuesIsCorrect("10", "3628800");
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

    public void failOnThisArgument(String argument) throws Exception{
        Factorial factorial = new Factorial();
        BigDecimal leftParameterBD = new BigDecimal(argument);

        expectedException.expect(ArithmeticException.class);

        factorial.calculate(leftParameterBD);
    }

    public void failOnThisNumberOfArguments(BigDecimal... arguments) throws Exception {
        Factorial factorial = new Factorial();

        expectedException.expect(NumberOfParametersException.class);
        expectedException.expectMessage(equalTo("Parameters: " + arguments.length));

        factorial.calculate(arguments);
    }


    public void factorialOfValuesIsCorrect(String... arguments) throws Exception {
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