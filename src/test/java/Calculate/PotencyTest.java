package Calculate;

import Exceptions.NumberOfParametersException;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class PotencyTest implements OperationTest{

    @Test
    public void shouldBeCorrect() throws Exception {
        this.calculationOf("1","2","1");
        this.calculationOf("2","2","4");
        this.calculationOf("3","3","27");
        this.calculationOf("5","2","25");
        this.calculationOf("7","12","13841287201");

        this.calculationOf("7","0","1");
        this.calculationOf("75678678","0","1");

        this.calculationOf("7","1","7");
        this.calculationOf("75678678","1","75678678");

        this.calculationOf("0.25","2","0.0625");
        this.calculationOf("0.5","3","0.125");
    }

    @Test
    public void potencyIsNegative() throws Exception{
        this.failOnTheseArguments("1", "-1");
        this.failOnTheseArguments("-3", "5");
        this.failOnTheseArguments("-12", "-4");
    }

    @Test
    public void potencyIsFloat() throws Exception{
        this.failOnTheseArguments("1", "0.25");
        this.failOnTheseArguments("0.5", "2");
        this.failOnTheseArguments("0.125", "1.5");
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
        checkOperatorToCalculate("pow", true);
    }

    @Test
    public void operatorShouldBeInvalid() throws Exception {
        for (String temp : Operators.listOfOperators){
            if (!temp.equals("pow")){
                checkOperatorToCalculate(temp, false);
            }
        }
    }

    public void failOnTheseArguments(String leftArgument, String rightArgument){
        BigDecimal leftArgumentAsBigDecimal = new BigDecimal(leftArgument);
        BigDecimal rightArgumentAsBigDecimal = new BigDecimal(rightArgument);
        Potency potency = new Potency();
        try {
            potency.calculate(leftArgumentAsBigDecimal, rightArgumentAsBigDecimal);
            fail("Es wurde eine falsche Anzahl an Parametern akzeptiert.");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void failOnThisNumberOfArguments(BigDecimal... arguments) throws Exception {
        Potency potency = new Potency();
        try {
            potency.calculate(arguments);
            fail("Es wurde eine falsche Anzahl an Parametern akzeptiert.");
        }
        catch (NumberOfParametersException numberOfParameterException) {

        }
    }

    public void calculationOf(String... arguments) throws Exception {
        Potency potency = new Potency();
        BigDecimal calculate = potency.calculate(new BigDecimal(arguments[0]), new BigDecimal(arguments[1]));
        BigDecimal expected = new BigDecimal(arguments[2]);
        String message = "\nExpected: " + expected.toString() + "\nActual: " + calculate.toString();

        assertEquals(message, 0, calculate.compareTo(expected));
    }

    public void checkOperatorToCalculate(String operator, boolean shouldBeCorrect) throws Exception {
        Potency potency = new Potency();
        String message = "Operator: " + operator;
        if (shouldBeCorrect){
            assertTrue(message, potency.canCalculate(operator));
        }
        else{
            assertFalse(message, potency.canCalculate(operator));
        }
    }

}