package Calculate;

import Exceptions.NumberOfParametersException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.math.BigDecimal;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.*;

public class DecimalPotencyTest implements OperationTest{
    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void shouldBeCorrect() throws Exception {
        this.decimalPotencyOfValuesIsCorrect("2","2","4");
        this.decimalPotencyOfValuesIsCorrect("16","2","256");

        this.decimalPotencyOfValuesIsCorrect("-2","2","4");
        this.decimalPotencyOfValuesIsCorrect("-16","2","256");

        this.decimalPotencyOfValuesIsCorrect("-2","3","-8");
        this.decimalPotencyOfValuesIsCorrect("-16","3","-4096");
        this.decimalPotencyOfValuesIsCorrect("2","6","64");
        this.decimalPotencyOfValuesIsCorrect("-2","6","64");

        this.decimalPotencyOfValuesIsCorrect("16","0","1");
        this.decimalPotencyOfValuesIsCorrect("16","1","16");
        this.decimalPotencyOfValuesIsCorrect("0","0","1");

        this.decimalPotencyOfValuesIsCorrect("16","-1","0.0625");
        this.decimalPotencyOfValuesIsCorrect("16","-2","0.00390625");

        this.decimalPotencyOfValuesIsCorrect("16","0.25","2");
//        this.decimalPotencyOfValuesIsCorrect("59","0.236","2.61770724353");

        this.decimalPotencyOfValuesIsCorrect("16","-0.25","0.5");
//        this.decimalPotencyOfValuesIsCorrect("59","-0.236","0.38201368868");


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
        checkOperatorToCalculate("pow", true);
    }

    @Test
    public void operatorShouldBeInvalid() throws Exception {
        for (String temp : Operators.listOfOperators){
            if (!temp.equals("pow")){
                checkOperatorToCalculate(temp, false);
            }
        }
    }

    public void failOnTheseArguments(String leftParameter, String rightParameter) throws Exception{
        DecimalPotency decimalPotency = new DecimalPotency();
        BigDecimal leftParameterBD = new BigDecimal(leftParameter);
        BigDecimal rightParameterBD = new BigDecimal(rightParameter);

        expectedException.expect(ArithmeticException.class);

        decimalPotency.calculate(leftParameterBD, rightParameterBD);
    }

    public void failOnThisNumberOfArguments(BigDecimal... arguments) throws Exception {
        DecimalPotency decimalPotency = new DecimalPotency();

        expectedException.expect(NumberOfParametersException.class);
        expectedException.expectMessage(equalTo("Parameters: " + arguments.length));

        decimalPotency.calculate(arguments);
    }

    public void decimalPotencyOfValuesIsCorrect(String... arguments) throws Exception {
        DecimalPotency decimalPotency = new DecimalPotency();
        BigDecimal calculate = decimalPotency.calculate(new BigDecimal(arguments[0]), new BigDecimal(arguments[1]));
        BigDecimal expected = new BigDecimal(arguments[2]);
        String message = "\nExpected: " + expected.toString() + "\nActual: " + calculate.toString();

        assertEquals(message, 0, calculate.compareTo(expected));
    }

    public void checkOperatorToCalculate(String operator, boolean shouldBeCorrect) throws Exception {
        DecimalPotency decimalPotency = new DecimalPotency();
        String message = "Operator: " + operator;
        if (shouldBeCorrect){
            assertTrue(message, decimalPotency.canCalculate(operator));
        }
        else{
            assertFalse(message, decimalPotency.canCalculate(operator));
        }
    }

}