package Calculate;

import Exceptions.NumberOfParametersException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.math.BigDecimal;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.*;

public class SquaringTest implements OperationTest{
    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void shouldBeCorrect() throws Exception {
        this.squaringOfValueIsCorrect("-100", "10000");
        this.squaringOfValueIsCorrect("-1", "1");
        this.squaringOfValueIsCorrect("0", "0");
        this.squaringOfValueIsCorrect("1", "1");
        this.squaringOfValueIsCorrect("100", "10000");
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
        checkOperatorToCalculate("sqr", true);
    }

    @Test
    public void operatorShouldBeInvalid() throws Exception {
        for (String temp : Operators.listOfOperators){
            if (!temp.equals("sqr")){
                checkOperatorToCalculate(temp, false);
            }
        }
    }

    private void failOnThisNumberOfArguments(BigDecimal... arguments) throws Exception {
        Squaring squaring = new Squaring();

        expectedException.expect(NumberOfParametersException.class);
        expectedException.expectMessage(equalTo("Parameters: " + arguments.length));

        squaring.calculate(arguments);
    }

    private void squaringOfValueIsCorrect(String leftArgument, String expectedValue) throws Exception {
        Squaring squaring = new Squaring();
        BigDecimal calculate = squaring.calculate(new BigDecimal(leftArgument));
        BigDecimal expected = new BigDecimal(expectedValue);
        String message = "\nExpected: " + expected.toString() + "\nActual: " + calculate.toString();

        assertEquals(message, 0, calculate.compareTo(expected));
    }

    private void checkOperatorToCalculate(String operator, boolean shouldBeCorrect) throws Exception {
        Squaring squaring = new Squaring();
        String message = "Operator: " + operator;
        if (shouldBeCorrect) {
            assertTrue(message, squaring.canCalculate(operator));
        }
        else {
            assertFalse(message, squaring.canCalculate(operator));
        }
    }
}