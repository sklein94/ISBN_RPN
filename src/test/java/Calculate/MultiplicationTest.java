package Calculate;

import Exceptions.NumberOfParametersException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.math.BigDecimal;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.*;

public class MultiplicationTest implements OperationTest{
    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void shouldBeCorrect() throws Exception {
        //Normale Multiplikation
        this.multiplicationOfValuesIsCorrect("2", "2", "4");

        //Multiplikation mit 0
        this.multiplicationOfValuesIsCorrect("0", "0", "0");
        this.multiplicationOfValuesIsCorrect("100", "0", "0");

        //Einer oder beide Werte negativ
        this.multiplicationOfValuesIsCorrect("1", "-1", "-1");
        this.multiplicationOfValuesIsCorrect("-2", "2", "-4");
        this.multiplicationOfValuesIsCorrect("-3", "-3", "9");

        //Kommazahlen
        this.multiplicationOfValuesIsCorrect("1.111", "3.111", "3.456321");
        this.multiplicationOfValuesIsCorrect("0.1111111111111111111111111111111111111111", "3", "0.3333333333333333333333333333333333333333");

        //Sehr hohe oder sehr niedrige Zahlen
        this.multiplicationOfValuesIsCorrect("1E+100", "1E+900", "1E+1000");
        this.multiplicationOfValuesIsCorrect("1E-100", "1E-900", "1E-1000");
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
        checkOperatorToCalculate(Multiplication.operator, true);
    }

    @Test
    public void operatorShouldBeInvalid() throws Exception {
        for (String temp : Operators.listOfOperatorsWithTwoOperands){
            if (!temp.equals(Multiplication.operator)){
                checkOperatorToCalculate(temp, false);
            }
        }
    }

    public void operatorIsIncluded() throws Exception {
        for (String temp : Operators.listOfOperatorsWithTwoOperands){
            if (temp.equals(Multiplication.operator)){
                return;
            }
        }
        fail("Operator dieser Operation ist nicht in der Liste: " + Multiplication.operator);
    }

    private void failOnThisNumberOfArguments(BigDecimal... arguments) throws Exception {
        Multiplication multiplication = new Multiplication();

        expectedException.expect(NumberOfParametersException.class);
        expectedException.expectMessage(equalTo("Parameters: " + arguments.length));

        multiplication.calculate(arguments);
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