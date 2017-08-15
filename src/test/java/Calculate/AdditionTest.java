package Calculate;

import Exceptions.NumberOfParametersException;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class AdditionTest implements OperationTest{
    @Test
    public void shouldBeCorrect() throws Exception {
        this.additionOf("1", "2", "3");
        this.additionOf("5", "6", "11");
        this.additionOf("-4", "-12", "-16");
        this.additionOf("5", "0", "5");
        this.additionOf("2", "-1", "1");
        this.additionOf("1.55", "2.45", "4");
        this.additionOf("-1.15", "-1.15", "-2.3");
        this.additionOf("1.1255", "3.1255", "4.251");
        this.additionOf("1.111", "3.111", "4.222");
    }


    @Test
    public void notEnoughParameters() throws Exception {
        Addition addition = new Addition();
        try {
            addition.calculate(new BigDecimal(1));
            addition.calculate();
            fail("Es sollte eine Exception geworfen werden. Es wurden nicht genug Parameter uebergeben.");
        }
        catch (NumberOfParametersException numberOfParameterException) {

        }
    }

    @Test
    public void tooManyParameters() throws Exception {
        Addition addition = new Addition();
        try {
            addition.calculate(new BigDecimal(1), new BigDecimal(1), new BigDecimal(1));
            addition.calculate(new BigDecimal(1), new BigDecimal(1), new BigDecimal(1), new BigDecimal(1), new BigDecimal(1));
            fail("Es sollte eine Exception geworfen werden. Es wurden nicht genug Parameter uebergeben.");
        }
        catch (NumberOfParametersException numberOfParameterException) {

        }
    }

    @Test
    public void operatorShouldBeValid() throws Exception {
        checkOperatorToCalculate("+", true);
    }

    @Test
    public void operatorShouldBeInvalid() throws Exception{
        checkOperatorToCalculate("-", false);
        checkOperatorToCalculate("*", false);
        checkOperatorToCalculate("/", false);
        checkOperatorToCalculate("!", false);
        checkOperatorToCalculate("sqrt", false);
        checkOperatorToCalculate("sqr", false);
        checkOperatorToCalculate("%", false);
        checkOperatorToCalculate("pow", false);

    }


    public void additionOf(String leftParameter, String rightParameter, String expectedResult) throws Exception {
        Addition addition = new Addition();
        BigDecimal calculate = addition.calculate(new BigDecimal(leftParameter), new BigDecimal(rightParameter));
        BigDecimal expected = new BigDecimal(expectedResult);
        assertEquals("\nExpected: " + expected.toString() + "\nActual: " + calculate.toString(),0, calculate.compareTo(expected));
    }

    public void checkOperatorToCalculate(String operator, boolean shouldBeCorrect) throws Exception{
        Addition addition = new Addition();
        if (shouldBeCorrect){
            assertTrue("Operator: " + operator, addition.canCalculate(operator));
        }
        else{
            assertFalse("Operator: " + operator, addition.canCalculate(operator));
        }
    }


}