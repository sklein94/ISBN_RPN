package Calculate;

import Exceptions.NumberOfParametersException;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class SquarerootTest implements OperationTest{

    public void shouldBeCorrect() throws Exception {

    }

    public void notEnoughParameters() throws Exception {
        Squareroot squareroot = new Squareroot();
        try {
            squareroot.calculate();
            squareroot.calculate(new BigDecimal("22"));
            fail("Es sollte eine Exception geworfen werden. Es wurden nicht genug Parameter uebergeben.");
        }
        catch (NumberOfParametersException numberOfParameterException) {

        }
    }

    public void tooManyParameters() throws Exception {
        Squareroot squareroot = new Squareroot();
        try {
            squareroot.calculate(new BigDecimal(1), new BigDecimal(1));
            squareroot.calculate(new BigDecimal(1), new BigDecimal(1), new BigDecimal(1));
            squareroot.calculate(new BigDecimal(1), new BigDecimal(1), new BigDecimal(1), new BigDecimal(1), new BigDecimal(1));
            fail("Es sollte eine Exception geworfen werden. Es wurden zu viele Parameter uebergeben.");
        }
        catch (NumberOfParametersException numberOfParameterException) {

        }
    }

    public void operatorShouldBeValid() throws Exception {
        checkOperatorToCalculate("sqrt", true);
    }

    public void operatorShouldBeInvalid() throws Exception {
        for (String temp : Operators.listOfOperators){
            if (!temp.equals("sqrt")){
                checkOperatorToCalculate(temp, false);
            }
        }
    }

    public void calculationOf(String... arguments) throws Exception {
        Squareroot squareroot = new Squareroot();
        BigDecimal calculate = squareroot.calculate(new BigDecimal(arguments[0]));
        BigDecimal expected = new BigDecimal(arguments[1]);
        String message = "\nExpected: " + expected.toString() + "\nActual: " + calculate.toString();

        assertEquals(message, 0, calculate.compareTo(expected));
    }

    public void checkOperatorToCalculate(String operator, boolean shouldBeCorrect) throws Exception {
        Squareroot squareroot = new Squareroot();
        String message = "Operator: " + operator;
        if (shouldBeCorrect){
            assertTrue(message, squareroot.canCalculate(operator));
        }
        else{
            assertFalse(message, squareroot.canCalculate(operator));
        }
    }
}