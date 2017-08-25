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
        //Normale Quadrierung
        this.squaringOfValueIsCorrect("-100", "10000");
        this.squaringOfValueIsCorrect("-1", "1");
        this.squaringOfValueIsCorrect("0", "0");
        this.squaringOfValueIsCorrect("1", "1");
        this.squaringOfValueIsCorrect("100", "10000");

        //Quadrierung mit Kommazahl
        this.squaringOfValueIsCorrect("1.5", "2.25");
//        this.squaringOfValueIsCorrect("0.3333333333", "0.1111111111");

        //Quadrierung mit sehr niedrigen und sehr hohen Werten
        this.squaringOfValueIsCorrect("1E-100", "1E-200");
        this.squaringOfValueIsCorrect("1E+100", "1E+200");
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
        checkOperatorToCalculate(Squaring.operator, true);
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