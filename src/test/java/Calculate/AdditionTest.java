package Calculate;

import Exceptions.NumberOfParametersException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.math.BigDecimal;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.*;

public class AdditionTest implements OperationTest{
    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void shouldBeCorrect() throws Exception {
        this.additionOfValuesIsCorrect("1", "1", "2");
        this.additionOfValuesIsCorrect("2", "2", "4");
        this.additionOfValuesIsCorrect("3", "3", "6");

        this.additionOfValuesIsCorrect("0", "0", "0");

        this.additionOfValuesIsCorrect("1", "-1", "0");
        this.additionOfValuesIsCorrect("-2", "2", "0");
        this.additionOfValuesIsCorrect("-3", "-3", "-6");

        this.additionOfValuesIsCorrect("1.55", "2.45", "4");
        this.additionOfValuesIsCorrect("-1.15", "-1.15", "-2.3");
        this.additionOfValuesIsCorrect("1.1255", "3.1255", "4.251");
        this.additionOfValuesIsCorrect("1.111", "3.111", "4.222");

        this.additionOfValuesIsCorrect("1.11111111111111111111111111111111111", "3.11111111111111111111111111111111111", "4.22222222222222222222222222222222222");

        this.additionOfValuesIsCorrect("1E+1000", "1E+1000", "2E+1000");
        this.additionOfValuesIsCorrect("1E-1000", "1E-1000", "2E-1000");
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
        checkOperatorToCalculate("+", true);
    }

    @Test
    public void operatorShouldBeInvalid() throws Exception{
        for (String temp : Operators.listOfOperators){
            if (!temp.equals("+")){
                checkOperatorToCalculate(temp, false);
            }
        }
    }

    private void failOnThisNumberOfArguments(BigDecimal... arguments) throws Exception {
        Addition addition = new Addition();

        expectedException.expect(NumberOfParametersException.class);
        expectedException.expectMessage(equalTo("Parameters: " + arguments.length));

        addition.calculate(arguments);
    }

    private void additionOfValuesIsCorrect(String leftArgument, String rightArgument, String expectedArgument) throws Exception {
        Addition addition = new Addition();
        BigDecimal calculate = addition.calculate(new BigDecimal(leftArgument), new BigDecimal(rightArgument));
        BigDecimal expected = new BigDecimal(expectedArgument);
        assertEquals("\nExpected: " + expected.toString() + "\nActual: " + calculate.toString(),0, calculate.compareTo(expected));
    }

    private void checkOperatorToCalculate(String operator, boolean shouldBeCorrect) throws Exception{
        Addition addition = new Addition();
        if (shouldBeCorrect){
            assertTrue("Operator: " + operator, addition.canCalculate(operator));
        }
        else{
            assertFalse("Operator: " + operator, addition.canCalculate(operator));
        }
    }


}