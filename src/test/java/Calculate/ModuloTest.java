package Calculate;

import Exceptions.NumberOfParametersException;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class ModuloTest implements OperationTest{

    @Test
    public void shouldBeCorrect() throws Exception {
        this.moduloOf("11","5","1");
        this.moduloOf("105","2","1");
        this.moduloOf("35","6","5");
        this.moduloOf("10","5","0");
        this.moduloOf("10","2","0");
        this.moduloOf("15","5","0");

        this.moduloOf("5","3","2");
        this.moduloOf("-5","3","-2");
        this.moduloOf("-5","-3","-2");

    }

    @Test
    public void valueIsFloat() throws Exception{
        Modulo modulo = new Modulo();
        try {
            modulo.calculate(new BigDecimal("1.1"), new BigDecimal("1"));
            modulo.calculate(new BigDecimal("2"), new BigDecimal("1.45"));
            modulo.calculate(new BigDecimal("2.5"), new BigDecimal("1.35"));
            fail("Es sollte eine Exception geworfen werden. Es wurde eine ungueltige Zahl angegeben.");
        }
        catch (ArithmeticException arithmeticException) {

        }
    }
    @Test
    public void notEnoughParameters() throws Exception {
        Modulo modulo = new Modulo();
        try {
            modulo.calculate();
            modulo.calculate(new BigDecimal("22"));
            fail("Es sollte eine Exception geworfen werden. Es wurden nicht genug Parameter uebergeben.");
        }
        catch (NumberOfParametersException arithmeticException) {

        }
    }

    @Test
    public void tooManyParameters() throws Exception {
        Modulo modulo = new Modulo();
        try {
            modulo.calculate(new BigDecimal(1), new BigDecimal(1), new BigDecimal(1));
            modulo.calculate(new BigDecimal(1), new BigDecimal(1), new BigDecimal(1), new BigDecimal(1));
            modulo.calculate(new BigDecimal(1), new BigDecimal(1), new BigDecimal(1), new BigDecimal(1), new BigDecimal(1));
            fail("Es sollte eine Exception geworfen werden. Es wurden nicht genug Parameter uebergeben.");
        }
        catch (NumberOfParametersException numberOfParameterException) {

        }
    }

    @Test
    public void operatorShouldBeValid() throws Exception {
        checkOperatorToCalculate("%", true);
    }

    @Test
    public void operatorShouldBeInvalid() throws Exception {

        checkOperatorToCalculate("+", false);
        checkOperatorToCalculate("*", false);
        checkOperatorToCalculate("/", false);
        checkOperatorToCalculate("-", false);
        checkOperatorToCalculate("sqrt", false);
        checkOperatorToCalculate("sqr", false);
        checkOperatorToCalculate("!", false);
        checkOperatorToCalculate("pow", false);
    }

    public void moduloOf(String leftParameter, String rightParameter, String expectedResult) throws Exception {
        Modulo modulo = new Modulo();
        BigDecimal calculate = modulo.calculate(new BigDecimal(leftParameter),new BigDecimal(rightParameter));
        BigDecimal expected = new BigDecimal(expectedResult);
        String message = "\nExpected: " + expected.toString() + "\nActual: " + calculate.toString();

        assertEquals(message, 0, calculate.compareTo(expected));
    }

    public void checkOperatorToCalculate(String operator, boolean shouldBeCorrect) throws Exception {
        Modulo modulo = new Modulo();
        String message = "Operator: " + operator;
        if (shouldBeCorrect) {
            assertTrue(message, modulo.canCalculate(operator));
        }
        else {
            assertFalse(message, modulo.canCalculate(operator));
        }
    }
}