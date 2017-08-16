package Calculate;

import Exceptions.NumberOfParametersException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.math.BigDecimal;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.*;

public class SubtractionTest implements OperationTest{
    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void shouldBeCorrect() throws Exception {
        this.subtractionOfValuesIsCorrect("1", "1", "0");
        this.subtractionOfValuesIsCorrect("2", "2", "0");
        this.subtractionOfValuesIsCorrect("3", "3", "0");

        this.subtractionOfValuesIsCorrect("0", "0", "0");

        this.subtractionOfValuesIsCorrect("1", "-1", "2");
        this.subtractionOfValuesIsCorrect("-2", "2", "-4");
        this.subtractionOfValuesIsCorrect("-3", "-3", "0");

        this.subtractionOfValuesIsCorrect("1.55", "2.45", "-0.9");
        this.subtractionOfValuesIsCorrect("-1.15", "-1.15", "0");
        this.subtractionOfValuesIsCorrect("1.1255", "3.1255", "-2");
        this.subtractionOfValuesIsCorrect("1.111", "3.111", "-2");
    }


    @Test
    public void notEnoughParameters() throws Exception {
        this.failOnThisNumberOfArguments(new BigDecimal(1));
        this.failOnThisNumberOfArguments();
    }

    @Test
    public void tooManyParameters() throws Exception {
        this.failOnThisNumberOfArguments(new BigDecimal(1), new BigDecimal(1), new BigDecimal(1));
        this.failOnThisNumberOfArguments(new BigDecimal(1), new BigDecimal(1), new BigDecimal(1), new BigDecimal(1), new BigDecimal(1));
    }

    @Test
    public void operatorShouldBeValid() throws Exception {
        checkOperatorToCalculate("-", true);
    }

    @Test
    public void operatorShouldBeInvalid() throws Exception{
        for (String temp : Operators.listOfOperators){
            if (!temp.equals("-")){
                checkOperatorToCalculate(temp, false);
            }
        }
    }

    private void failOnThisNumberOfArguments(BigDecimal... arguments) throws Exception {
        Subtraction subtraction = new Subtraction();

        expectedException.expect(NumberOfParametersException.class);
        expectedException.expectMessage(equalTo("Parameters: " + arguments.length));

        subtraction.calculate(arguments);
    }

    private void subtractionOfValuesIsCorrect(String leftParameter, String rightParameter, String expectedValue) throws Exception {
        Subtraction subtraction = new Subtraction();
        BigDecimal calculate = subtraction.calculate(new BigDecimal(leftParameter), new BigDecimal(rightParameter));
        BigDecimal expected = new BigDecimal(expectedValue);
        assertEquals("\nExpected: " + expected.toString() + "\nActual: " + calculate.toString(),0, calculate.compareTo(expected));
    }

    private void checkOperatorToCalculate(String operator, boolean shouldBeCorrect) throws Exception{
        Subtraction subtraction = new Subtraction();
        if (shouldBeCorrect){
            assertTrue("Operator: " + operator, subtraction.canCalculate(operator));
        }
        else{
            assertFalse("Operator: " + operator, subtraction.canCalculate(operator));
        }
    }

}