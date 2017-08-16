package Calculate;

import Exceptions.NumberOfParametersException;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class ModuloTest implements OperationTest{

    @Test
    public void shouldBeCorrect() throws Exception {
        this.moduloOfValuesIsCorrect("11","5","1");
        this.moduloOfValuesIsCorrect("105","2","1");
        this.moduloOfValuesIsCorrect("35","6","5");
        this.moduloOfValuesIsCorrect("10","5","0");
        this.moduloOfValuesIsCorrect("10","2","0");
        this.moduloOfValuesIsCorrect("15","5","0");

        this.moduloOfValuesIsCorrect("5","3","2");
        this.moduloOfValuesIsCorrect("-5","3","-2");
        this.moduloOfValuesIsCorrect("-5","-3","-2");

    }

    @Test
    public void checkFOrInvalidValues() throws Exception{
        this.failOnTheseArguments("1.1","1");
        this.failOnTheseArguments("2","1.45");
        this.failOnTheseArguments("2.5","1.35");

        this.failOnTheseArguments("10","0");
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

    private void failOnTheseArguments(String leftParameter, String rightParameter){
        Modulo modulo = new Modulo();
        BigDecimal leftParameterBigDecimal = new BigDecimal(leftParameter);
        BigDecimal rightParameterBigDecimal = new BigDecimal(rightParameter);
        try{
            modulo.calculate(leftParameterBigDecimal, rightParameterBigDecimal);
            fail("Es wurde ein ungueltiger Wert akzeptiert.");
        }
        catch (Exception e){

        }
    }

    private void failOnThisNumberOfArguments(BigDecimal... arguments) throws Exception {
        Modulo modulo = new Modulo();
        try {
            modulo.calculate(arguments);
            fail("Es wurde eine falsche Anzahl an Parametern akzeptiert.");
        }
        catch (NumberOfParametersException numberOfParameterException) {

        }
    }

    private void moduloOfValuesIsCorrect(String leftParameter, String rightParameter, String expectedValue) throws Exception {
        Modulo modulo = new Modulo();
        BigDecimal calculate = modulo.calculate(new BigDecimal(leftParameter),new BigDecimal(rightParameter));
        BigDecimal expected = new BigDecimal(expectedValue);
        String message = "\nExpected: " + expected.toString() + "\nActual: " + calculate.toString();

        assertEquals(message, 0, calculate.compareTo(expected));
    }

    private void checkOperatorToCalculate(String operator, boolean shouldBeCorrect) throws Exception {
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