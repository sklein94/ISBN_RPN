package Calculate;

import Exceptions.NumberOfParametersException;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class DivisionTest implements OperationTest{

    @Test
    public void shouldBeCorrect() throws Exception {
        this.divisionOf("1", "2", "-1");
        this.divisionOf("5", "6", "-1");
        this.divisionOf("-4", "-12", "8");
        this.divisionOf("5", "0", "5");
        this.divisionOf("2", "-1", "3");
        this.divisionOf("1.55", "2.45", "-0.9");
        this.divisionOf("-1.15", "-1.15", "0");
        this.divisionOf("1.1255", "3.1255", "-2");
        this.divisionOf("1.111", "3.111", "-2");
    }


    @Test
    public void notEnoughParameters() throws Exception {
        Division division = new Division();
        try {
            division.calculate(new BigDecimal(1));
            division.calculate();
            fail("Es sollte eine Exception geworfen werden. Es wurden nicht genug Parameter uebergeben.");
        }
        catch (NumberOfParametersException numberOfParameterException) {

        }
    }

    @Test
    public void tooManyParameters() throws Exception {
        Division division = new Division();
        try {
            division.calculate(new BigDecimal(1), new BigDecimal(1), new BigDecimal(1));
            division.calculate(new BigDecimal(1), new BigDecimal(1), new BigDecimal(1), new BigDecimal(1), new BigDecimal(1));
            fail("Es sollte eine Exception geworfen werden. Es wurden nicht genug Parameter uebergeben.");
        }
        catch (NumberOfParametersException numberOfParameterException) {

        }
    }

    @Test
    public void operatorShouldBeValid() throws Exception {
        checkOperatorToCalculate("/", true);
    }

    @Test
    public void operatorShouldBeInvalid() throws Exception{
        checkOperatorToCalculate("+", false);
        checkOperatorToCalculate("*", false);
        checkOperatorToCalculate("-", false);
        checkOperatorToCalculate("!", false);
        checkOperatorToCalculate("sqrt", false);
        checkOperatorToCalculate("sqr", false);
        checkOperatorToCalculate("%", false);
        checkOperatorToCalculate("pow", false);

    }


    public void divisionOf(String leftParameter, String rightParameter, String expectedResult) throws Exception {
        Division division = new Division();
        BigDecimal calculate = division.calculate(new BigDecimal(leftParameter), new BigDecimal(rightParameter));
        BigDecimal expected = new BigDecimal(expectedResult);
        assertEquals("\nExpected: " + expected.toString() + "\nActual: " + calculate.toString(),0, calculate.compareTo(expected));
    }

    public void checkOperatorToCalculate(String operator, boolean shouldBeCorrect) throws Exception{
        Division division = new Division();
        if (shouldBeCorrect){
            assertTrue("Operator: " + operator, division.canCalculate(operator));
        }
        else{
            assertFalse("Operator: " + operator, division.canCalculate(operator));
        }
    }

}