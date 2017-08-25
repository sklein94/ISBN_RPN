package Calculate;

import Exceptions.NumberOfParametersException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.math.BigDecimal;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.*;

public class SquarerootTest implements OperationTest {
    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void shouldBeCorrect() throws Exception {
        //Normale Wurzelziehung
        this.SquarerootOfValuesIsCorrect("4", "2");
        this.SquarerootOfValuesIsCorrect("268435456", "16384");

        //Kommazahlen
        this.SquarerootOfValuesIsCorrect("2.25", "1.5");
//        this.SquarerootOfValuesIsCorrect("1.78", "1.33416640641");
//        this.SquarerootOfValuesIsCorrect("0.11111", "0.33333");

        //Wurzel aus 0
        this.SquarerootOfValuesIsCorrect("0", "0");

        //Sehr hohe und sehr niedrige Zahlen
        this.SquarerootOfValuesIsCorrect("1E+1000", "1E+500");
        this.SquarerootOfValuesIsCorrect("1E-100", "1E-50");
    }

    @Test
    public void invalidArguments() throws Exception {
        //Negative Zahl
        this.failOnThisArgument("-1");
    }

    @Test
    public void notEnoughParameters() throws Exception {
        this.failOnThisNumberOfArguments();
    }

    @Test
    public void tooManyParameters() throws Exception {
        this.failOnThisNumberOfArguments(new BigDecimal(1), new BigDecimal(1));
        this.failOnThisNumberOfArguments(new BigDecimal(1), new BigDecimal(1), new BigDecimal(1));
        this.failOnThisNumberOfArguments(new BigDecimal(1), new BigDecimal(1), new BigDecimal(1), new BigDecimal(1));
    }

    @Test
    public void operatorShouldBeValid() throws Exception {
        checkOperatorToCalculate(Squareroot.operator, true);
    }


    private void failOnThisArgument(String leftParameter) throws Exception {
        Squareroot squareroot = new Squareroot();
        BigDecimal leftParameterBD = new BigDecimal(leftParameter);

        expectedException.expect(ArithmeticException.class);

        squareroot.calculate(leftParameterBD);
    }

    private void failOnThisNumberOfArguments(BigDecimal... arguments) throws Exception {
        Squareroot squareroot = new Squareroot();

        expectedException.expect(NumberOfParametersException.class);
        expectedException.expectMessage(equalTo("Parameters: " + arguments.length));

        squareroot.calculate(arguments);
    }

    private void SquarerootOfValuesIsCorrect(String... arguments) throws Exception {
        Squareroot squareroot = new Squareroot();
        BigDecimal calculate = squareroot.calculate(new BigDecimal(arguments[0]));
        BigDecimal expected = new BigDecimal(arguments[1]);
        String message = "\nExpected: " + expected.toString() + "\nActual: " + calculate.toString();

        assertEquals(message, 0, calculate.compareTo(expected));
    }

    private void checkOperatorToCalculate(String operator, boolean shouldBeCorrect) throws Exception {
        Squareroot squareroot = new Squareroot();
        String message = "Operator: " + operator;
        if (shouldBeCorrect) {
            assertTrue(message, squareroot.canCalculate(operator));
        }
        else {
            assertFalse(message, squareroot.canCalculate(operator));
        }
    }
}