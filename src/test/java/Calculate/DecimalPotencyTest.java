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
        //Normale Potenzen
        this.decimalPotencyOfValuesIsCorrect("16","2","256");

        //Erster Wert negativ
        this.decimalPotencyOfValuesIsCorrect("-16","2","256");

        //Höhere Potenzen als 2
        this.decimalPotencyOfValuesIsCorrect("-2","3","-8");
        this.decimalPotencyOfValuesIsCorrect("-16","3","-4096");
        this.decimalPotencyOfValuesIsCorrect("2","6","64");
        this.decimalPotencyOfValuesIsCorrect("-2","6","64");

        //0 oder 1 als Potenz
        this.decimalPotencyOfValuesIsCorrect("16","0","1");
        this.decimalPotencyOfValuesIsCorrect("16","1","16");
        this.decimalPotencyOfValuesIsCorrect("0","0","1");

        //Negative Zahl in der Potenz
        this.decimalPotencyOfValuesIsCorrect("16","-1","0.0625");
        this.decimalPotencyOfValuesIsCorrect("16","-2","0.00390625");

        //Kommazahlen in der Potenz
        this.decimalPotencyOfValuesIsCorrect("16","0.25","2");
//        this.decimalPotencyOfValuesIsCorrect("59","0.236","2.61770724353");

        //Lange kommazahl
//        this.decimalPotencyOfValuesIsCorrect("0.33333333333333333333333333333333333","2","0.11111111111111111111111111111111111");

        //Sehr hohe und sehr tiefe Werte
        this.decimalPotencyOfValuesIsCorrect("1E+1000", "2", "1E+2000");
        this.decimalPotencyOfValuesIsCorrect("1E-1000", "2", "1E-2000");
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
        checkOperatorToCalculate(DecimalPotency.operator, true);
    }

    @Test
    public void operatorShouldBeInvalid() throws Exception {
        for (String temp : Operations.listOfOperators){
            if (!temp.equals(DecimalPotency.operator)){
                checkOperatorToCalculate(temp, false);
            }
        }
    }

    @Test
    public void operatorIsIncluded() throws Exception {
        for (String temp : Operations.listOfOperators){
            if (temp.equals(DecimalPotency.operator)){
                return;
            }
        }
        fail("Operator dieser Operation ist nicht in der Liste: " + DecimalPotency.operator);
    }

    private void failOnThisNumberOfArguments(BigDecimal... arguments) throws Exception {
        DecimalPotency decimalPotency = new DecimalPotency();

        expectedException.expect(NumberOfParametersException.class);
        expectedException.expectMessage(equalTo("Parameters: " + arguments.length));

        decimalPotency.calculate(arguments);
    }

    private void decimalPotencyOfValuesIsCorrect(String leftParameter, String rightParameter, String expectedValue) throws Exception {
        DecimalPotency decimalPotency = new DecimalPotency();
        BigDecimal calculate = decimalPotency.calculate(new BigDecimal(leftParameter), new BigDecimal(rightParameter));
        BigDecimal expected = new BigDecimal(expectedValue);
        String message = "\nExpected: " + expected.toString() + "\nActual: " + calculate.toString();

        assertEquals(message, 0, calculate.compareTo(expected));
    }

    private void checkOperatorToCalculate(String operator, boolean shouldBeCorrect) throws Exception {
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