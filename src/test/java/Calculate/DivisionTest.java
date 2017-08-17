package Calculate;

import Exceptions.NumberOfParametersException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.math.BigDecimal;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.*;

public class DivisionTest implements OperationTest{
    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void shouldBeCorrect() throws Exception {
        //Normale Rechnung
        this.divisionOfValuesIsCorrect("2", "2", "1");

        //1 Wert oder beide Werte negativ
        this.divisionOfValuesIsCorrect("1", "-1", "-1");
        this.divisionOfValuesIsCorrect("-2", "2", "-1");
        this.divisionOfValuesIsCorrect("-3", "-3", "1");

        //Kommazahlen
        this.divisionOfValuesIsCorrect("2.25", "5", "0.45");
        this.divisionOfValuesIsCorrect("-3.15", "3", "-1.05");
        this.divisionOfValuesIsCorrect("1.11111", "1.2", "0.925925");

        //Sehr lange kommazahlen bzw periodisches Ergebnis
        this.divisionOfValuesIsCorrect("1", "3", "0.3333333333333333333333333333333333");   //34 Zeichen nach dem Komma
        this.divisionOfValuesIsCorrect("0.33333333333333333333333333333333333", "3", "0.1111111111111111111111111111111111");  //34 Zeichen nach dem Komma

        //Sehr hohe oder sehr niedrige Zahlen
        this.divisionOfValuesIsCorrect("1E+1000", "1E+1000", "1");
        this.divisionOfValuesIsCorrect("1E+1000", "1E+10", "1E990");
    }

    @Test
    public void invalidArguments() throws Exception {
        //Durch 0 teilen
        this.failOnTheseArguments("1", "0");
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
        checkOperatorToCalculate(Division.operator, true);
    }

    @Test
    public void operatorShouldBeInvalid() throws Exception {
        for (String temp : Operators.listOfOperatorsWithTwoOperands){
            if (!temp.equals(Division.operator)){
                checkOperatorToCalculate(temp, false);
            }
        }
    }

    @Test
    public void operatorIsIncluded() throws Exception {
        for (String temp : Operators.listOfOperatorsWithTwoOperands){
            if (temp.equals(Division.operator)){
                return;
            }
        }
        fail("Operator dieser Operation ist nicht in der Liste: " + Division.operator);
    }

    private void failOnThisNumberOfArguments(BigDecimal... arguments) throws Exception {
        Division division = new Division();

        expectedException.expect(NumberOfParametersException.class);
        expectedException.expectMessage(equalTo("Parameters: " + arguments.length));

        division.calculate(arguments);
    }

    private void failOnTheseArguments(String leftParameter, String rightParameter) throws Exception {
        Division division = new Division();
        BigDecimal leftParameterBD = new BigDecimal(leftParameter);
        BigDecimal rightParameterBD = new BigDecimal(rightParameter);

        expectedException.expect(ArithmeticException.class);

        division.calculate(leftParameterBD, rightParameterBD);
    }

    private void divisionOfValuesIsCorrect(String leftArgument, String rightArgument, String expectedValue) throws Exception {
        Division division = new Division();
        BigDecimal calculate = division.calculate(new BigDecimal(leftArgument),new BigDecimal(rightArgument));
        BigDecimal expected = new BigDecimal(expectedValue);
        String message = "\nExpected: " + expected.toString() + "\nActual: " + calculate.toString();

        assertEquals(message, 0, calculate.compareTo(expected));
    }

    private void checkOperatorToCalculate(String operator, boolean shouldBeCorrect) throws Exception {
        Division division = new Division();
        String message = "Operator: " + operator;
        if (shouldBeCorrect) {
            assertTrue(message, division.canCalculate(operator));
        }
        else {
            assertFalse(message, division.canCalculate(operator));
        }
    }
}