package Calculate;

import Exceptions.NumberOfParametersException;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class AdditionTest implements OperationTest{
    @Test
    public void shouldBeCorrect() throws Exception {
        this.additionOf("1", "1", "2");
        this.additionOf("2", "2", "4");
        this.additionOf("3", "3", "6");

        this.additionOf("0", "0", "0");

        this.additionOf("1", "-1", "0");
        this.additionOf("-2", "2", "0");
        this.additionOf("-3", "-3", "-6");

        this.additionOf("1.55", "2.45", "4");
        this.additionOf("-1.15", "-1.15", "-2.3");
        this.additionOf("1.1255", "3.1255", "4.251");
        this.additionOf("1.111", "3.111", "4.222");
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
        try{
            addition.calculate(arguments);
            fail("Es wurde eine falsche Anzahl an Parametern akzeptiert.");
        }
        catch (NumberOfParametersException numberOfParametersException){

        }

    }

    private void additionOf(String leftArgument, String rightArgument, String expectedArgument) throws Exception {
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