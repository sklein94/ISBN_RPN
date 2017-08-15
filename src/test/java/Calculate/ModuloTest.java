package Calculate;

import Exceptions.NumberOfParametersException;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class ModuloTest implements OperationTest{

    @Test
    public void shouldBeCorrect() throws Exception {
        this.calculationOf("11","5","1");
        this.calculationOf("105","2","1");
        this.calculationOf("35","6","5");
        this.calculationOf("10","5","0");
        this.calculationOf("10","2","0");
        this.calculationOf("15","5","0");

        this.calculationOf("5","3","2");
        this.calculationOf("-5","3","-2");
        this.calculationOf("-5","-3","-2");

    }

    @Test
    public void valueIsNotFloat() throws Exception{
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
        catch (NumberOfParametersException numberOfParameterException) {

        }
    }

    @Test
    public void tooManyParameters() throws Exception {
        Modulo modulo = new Modulo();
        try {
            modulo.calculate(new BigDecimal(1), new BigDecimal(1), new BigDecimal(1));
            modulo.calculate(new BigDecimal(1), new BigDecimal(1), new BigDecimal(1), new BigDecimal(1));
            modulo.calculate(new BigDecimal(1), new BigDecimal(1), new BigDecimal(1), new BigDecimal(1), new BigDecimal(1));
            fail("Es sollte eine Exception geworfen werden. Es wurden zu viele Parameter uebergeben.");
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
        for (String temp : Operators.listOfOperators){
            if (!temp.equals("%")){
                checkOperatorToCalculate(temp, false);
            }
        }
    }

    public void calculationOf(String... arguments) throws Exception {
        Modulo modulo = new Modulo();
        BigDecimal calculate = modulo.calculate(new BigDecimal(arguments[0]),new BigDecimal(arguments[1]));
        BigDecimal expected = new BigDecimal(arguments[2]);
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