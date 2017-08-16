package Calculate;

import Exceptions.NumberOfParametersException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.math.BigDecimal;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.*;

public class DivisionTest implements OperationTest{
    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void shouldBeCorrect() throws Exception {
        this.divisionOfValuesIsCorrect("1", "1", "1");
        this.divisionOfValuesIsCorrect("2", "2", "1");
        this.divisionOfValuesIsCorrect("3", "3", "1");

        this.divisionOfValuesIsCorrect("1", "-1", "-1");
        this.divisionOfValuesIsCorrect("-2", "2", "-1");
        this.divisionOfValuesIsCorrect("-3", "-3", "1");

        this.divisionOfValuesIsCorrect("2.25", "5", "0.45");
        this.divisionOfValuesIsCorrect("-3.15", "3", "-1.05");
        this.divisionOfValuesIsCorrect("1.11111", "1.2", "0.925925");


        this.divisionOfValuesIsCorrect("1", "3", "0.3333333333333333333333333333333333333333333333333");
    }

    @Test
    public void invalidArguments() throws Exception {
        this.failOnTheseArguments("1", "0");
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
        checkOperatorToCalculate("/", true);
    }

    @Test
    public void operatorShouldBeInvalid() throws Exception {
        for (String temp : Operators.listOfOperators){
            if (!temp.equals("/")){
                checkOperatorToCalculate(temp, false);
            }
        }
    }

    private void failOnThisNumberOfArguments(BigDecimal... arguments) throws Exception {
        Division division = new Division();

        expectedException.expect(NumberOfParametersException.class);
        expectedException.expectMessage(equalTo("Parameters: " + arguments.length));

        division.calculate(arguments);
    }

    private void failOnTheseArguments(String leftParameter, String rightParameter) throws Exception {
        Division division = new Division();
        BigDecimal leftParameterBD = new BigDecimal(leftParameter);
        BigDecimal rightParameterBD = new BigDecimal(rightParameter);

        expectedException.expect(ArithmeticException.class);

        division.calculate(leftParameterBD, rightParameterBD);
    }

    private void divisionOfValuesIsCorrect(String leftArgument, String rightArgument, String expectedValue) throws Exception {
        Division division = new Division();
        BigDecimal calculate = division.calculate(new BigDecimal(leftArgument),new BigDecimal(rightArgument));
        BigDecimal expected = new BigDecimal(expectedValue);
        String message = "\nExpected: " + expected.toString() + "\nActual: " + calculate.toString();

        assertEquals(message, 0, calculate.compareTo(expected));
    }

    private void checkOperatorToCalculate(String operator, boolean shouldBeCorrect) throws Exception {
        Division division = new Division();
        String message = "Operator: " + operator;
        if (shouldBeCorrect) {
            assertTrue(message, division.canCalculate(operator));
        }
        else {
            assertFalse(message, division.canCalculate(operator));
        }
    }
}