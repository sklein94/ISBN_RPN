package Calculate;

import Exceptions.NumberOfParametersException;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class MultiplicationTest implements OperationTest{

    @Test
    public void shouldBeCorrect() throws Exception {
        this.calculationOf("1","1","1");
        this.calculationOf("1","2","2");
        this.calculationOf("3","3","9");

        this.calculationOf("-1","2","-2");
        this.calculationOf("1","-2","-2");
        this.calculationOf("-1","-2","2");

        this.calculationOf("0","0","0");
        this.calculationOf("0","5000","0");
        this.calculationOf("5000","0","0");

        this.calculationOf("1.5","2","3");
        this.calculationOf("1.5","1.5","2.25");
        this.calculationOf("1.55","2.123","3.29065");

        this.calculationOf("1.55","-2.123","-3.29065");
        this.calculationOf("-1.55","2.123","-3.29065");
        this.calculationOf("-1.55","-2.123","3.29065");
    }

    @Test
    public void notEnoughParameters() throws Exception {
        Multiplication multiplication = new Multiplication();
        try {
            multiplication.calculate();
            multiplication.calculate(new BigDecimal("22"));
            fail("Es sollte eine Exception geworfen werden. Es wurden nicht genug Parameter uebergeben.");
        }
        catch (NumberOfParametersException numberOfParameterException) {

        }
    }

    @Test
    public void tooManyParameters() throws Exception {
        Multiplication multiplication = new Multiplication();
        try {
            multiplication.calculate(new BigDecimal(1), new BigDecimal(1), new BigDecimal(1));
            multiplication.calculate(new BigDecimal(1), new BigDecimal(1), new BigDecimal(1), new BigDecimal(1));
            multiplication.calculate(new BigDecimal(1), new BigDecimal(1), new BigDecimal(1), new BigDecimal(1), new BigDecimal(1));
            fail("Es sollte eine Exception geworfen werden. Es wurden zu viele Parameter uebergeben.");
        }
        catch (NumberOfParametersException numberOfParameterException) {

        }
    }

    @Test
    public void operatorShouldBeValid() throws Exception {
        checkOperatorToCalculate("*", true);
    }

    @Test
    public void operatorShouldBeInvalid() throws Exception {
        for (String temp : Operators.listOfOperators){
            if (!temp.equals("*")){
                checkOperatorToCalculate(temp, false);
            }
        }
    }

    public void calculationOf(String... arguments) throws Exception {
        Multiplication multiplication = new Multiplication();
        BigDecimal calculate = multiplication.calculate(new BigDecimal(arguments[0]),new BigDecimal(arguments[1]));
        BigDecimal expected = new BigDecimal(arguments[2]);
        String message = "\nExpected: " + expected.toString() + "\nActual: " + calculate.toString();

        assertEquals(message, 0, calculate.compareTo(expected));
    }

    public void checkOperatorToCalculate(String operator, boolean shouldBeCorrect) throws Exception {
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