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
        //Niedrigste Zahl
        this.secondFactorialOfValuesIsCorrect("0", "1");

        //Die ersten 3 Fakultäten
        this.secondFactorialOfValuesIsCorrect("1", "1");
        this.secondFactorialOfValuesIsCorrect("2", "2");
        this.secondFactorialOfValuesIsCorrect("3", "6");

        //Etwas höhere Fakultät
        this.secondFactorialOfValuesIsCorrect("10", "3628800");
    }

    @Test
    public void invalidValues() throws Exception{
        //Negative Zahl
        this.failOnThisArgument("-1");

        //Kommazahl
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
        checkOperatorToCalculate(SecondFactorial.operator, true);
    }

    @Test
    public void operatorShouldBeInvalid() throws Exception {
        for (String temp : Operators.listOfOperatorsOneOperand){
            if (!temp.equals(SecondFactorial.operator)){
                checkOperatorToCalculate(temp, false);
            }
        }
    }

    @Test
    public void operatorIsIncluded() throws Exception {
        for (String temp : Operators.listOfOperatorsOneOperand){
            if (temp.equals(SecondFactorial.operator)){
                return;
            }
        }
        fail("Operator dieser Operation ist nicht in der Liste: " + SecondFactorial.operator);
    }

    private void failOnThisArgument(String argument) throws Exception{
        SecondFactorial secondFactorial = new SecondFactorial();
        BigDecimal leftParameterBD = new BigDecimal(argument);

        expectedException.expect(ArithmeticException.class);

        secondFactorial.calculate(leftParameterBD);
    }

    private void failOnThisNumberOfArguments(BigDecimal... arguments) throws Exception {
        SecondFactorial secondFactorial = new SecondFactorial();

        expectedException.expect(NumberOfParametersException.class);
        expectedException.expectMessage(equalTo("Parameters: " + arguments.length));

        secondFactorial.calculate(arguments);
    }


    private void secondFactorialOfValuesIsCorrect(String... arguments) throws Exception {
        SecondFactorial secondFactorial = new SecondFactorial();
        BigDecimal calculate = secondFactorial.calculate(new BigDecimal(arguments[0]));
        BigDecimal expected = new BigDecimal(arguments[1]);
        String message = "\nExpected: " + expected.toString() + "\nActual: " + calculate.toString();

        assertEquals(message, 0, calculate.compareTo(expected));
    }


    private void checkOperatorToCalculate(String operator, boolean shouldBeCorrect) throws Exception {
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