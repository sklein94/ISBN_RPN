package Calculate;

import Exceptions.NumberOfParametersException;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class SubtractionTest implements OperationTest{

    @Test
    public void shouldBeCorrect() throws Exception {
        this.subtractionnOf("1", "1", "0");
        this.subtractionnOf("2", "2", "0");
        this.subtractionnOf("3", "3", "0");

        this.subtractionnOf("0", "0", "0");

        this.subtractionnOf("1", "-1", "2");
        this.subtractionnOf("-2", "2", "-4");
        this.subtractionnOf("-3", "-3", "0");

        this.subtractionnOf("1.55", "2.45", "-0.9");
        this.subtractionnOf("-1.15", "-1.15", "0");
        this.subtractionnOf("1.1255", "3.1255", "-2");
        this.subtractionnOf("1.111", "3.111", "-2");
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
        checkOperatorToCalculate("/", true);
    }

    @Test
    public void operatorShouldBeInvalid() throws Exception{
        for (String temp : Operators.listOfOperators){
            if (!temp.equals("/")){
                checkOperatorToCalculate(temp, false);
            }
        }
    }

    private void failOnThisNumberOfArguments(BigDecimal... arguments) throws Exception {
        Subtraction subtraction = new Subtraction();
        try {
            subtraction.calculate(arguments);
            fail("Es wurde eine falsche Anzahl an Parametern akzeptiert.");
        }
        catch (NumberOfParametersException numberOfParameterException) {

        }
    }

    private void subtractionnOf(String leftParameter, String rightParameter, String expectedValue) throws Exception {
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