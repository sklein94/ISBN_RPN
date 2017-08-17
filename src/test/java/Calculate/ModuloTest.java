package Calculate;

import Exceptions.NumberOfParametersException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.math.BigDecimal;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.*;

public class ModuloTest implements OperationTest{
    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void shouldBeCorrect() throws Exception {
        //Einfache Modulo Operationen
        this.moduloOfValuesIsCorrect("1","1","0");
        this.moduloOfValuesIsCorrect("10000","1","0");
        this.moduloOfValuesIsCorrect("10000","5001","4999");

        //Ein Wert oder beide Werte negativ
        this.moduloOfValuesIsCorrect("5","3","2");
        this.moduloOfValuesIsCorrect("-5","3","-2");
        this.moduloOfValuesIsCorrect("-5","-3","-2");

        //Sehr hohe Werte
        this.moduloOfValuesIsCorrect("1E+1000","1E+1000","0");
        this.moduloOfValuesIsCorrect("1E+1000","1E+1001","1E1000");

    }

    @Test
    public void invalidArguments() throws Exception{
        //Ein Wert oder beide Werte eine Kommazahl
        this.failOnTheseArguments("1.11133778","1");
        this.failOnTheseArguments("2","1.45312");
        this.failOnTheseArguments("2.113225","1.3335");

        //Zweiter Wert 0
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
        checkOperatorToCalculate(Modulo.operator, true);
    }

    @Test
    public void operatorShouldBeInvalid() throws Exception {
        for (String temp : Operators.listOfOperatorsWithTwoOperands){
            if (!temp.equals(Modulo.operator)){
                checkOperatorToCalculate(temp, false);
            }
        }
    }

    @Test
    public void operatorIsIncluded() throws Exception {
        for (String temp : Operators.listOfOperatorsWithTwoOperands){
            if (temp.equals(Modulo.operator)){
                return;
            }
        }
        fail("Operator dieser Operation ist nicht in der Liste: " + Modulo.operator);
    }

    private void failOnTheseArguments(String leftParameter, String rightParameter) throws Exception{
        Modulo modulo = new Modulo();
        BigDecimal leftParameterBD = new BigDecimal(leftParameter);
        BigDecimal rightParameterBD = new BigDecimal(rightParameter);

        expectedException.expect(ArithmeticException.class);

        modulo.calculate(leftParameterBD, rightParameterBD);
    }

    private void failOnThisNumberOfArguments(BigDecimal... arguments) throws Exception {
        Modulo modulo = new Modulo();

        expectedException.expect(NumberOfParametersException.class);
        expectedException.expectMessage(equalTo("Parameters: " + arguments.length));

        modulo.calculate(arguments);
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