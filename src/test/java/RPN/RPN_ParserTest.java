package RPN;

import Calculate.Operators;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class RPN_ParserTest {

    @Test
    public void checkRPNStringShouldBeValid() {
        String testStringTwoOperands = "1 2 ";
        for (String temp : Operators.listOfOperatorsWithTwoOperands) {
            this.testString(testStringTwoOperands + temp, true);
        }

        String testStringOneOperand = "4";
        for (String temp : Operators.listOfOperatorsWithTwoOperands) {
            this.testString(testStringOneOperand + temp, true);
        }
        System.out.print(Operators.listOfOperatorsWithTwoOperands.get(0));

        this.testString("1 2 + 1 2 - /", true);
        this.testString("1 2 3 4 + + +", true);
        this.testString("-1 2 + -3 -4 + +", true);
        this.testString("-1 -2 - +3 4 - +", true);
        this.testString("1 ! ! ! ! ! !", true);
    }


    @Test
    public void checkRPNStringWrongNumerOfArguments() {
        this.testString("1 2 + +", false);
        this.testString("1 2 + 3 4 + + +", false);
        this.testString("1 2", false);
        this.testString("1 2 + 3 4 + 5", false);
    }

    @Test
    public void checkRPNStringInvalidCharacters() {
        this.testString("1 2 + D", false);
        this.testString("1 2 + #asdf", false);
        this.testString("1 2 + srqt", false);
        this.testString("1 2 + srttqq", false);
    }

    @Test
    public void checkRPNStringPositionsOfArguments() {
        this.testString("+ 1 2", false);
        this.testString("1 2 + + 3 4 +", false);
        this.testString("1 + 2 3 4 +  +", false);
    }

    @Test
    public void checkRPNNumberOfOperandsFunctionCorrect() {
    }


    @Test
    public void checkRPNNumberOfOperantorsWithTwoOperantsFunctionCorrect() {
    }

    @Test
    public void checkRPNNumberOfOperantorsWithOneOperantFunctionCorrect() {
    }


    private void testString(String stringToTest, boolean shouldBeCorrect) {
        RPN_Parser rpnTestStringToTest = new RPN_Parser(stringToTest);
        if (shouldBeCorrect) {
            assertTrue("Fehler beim Testen von: '" + stringToTest, rpnTestStringToTest.stringIsValidToParse(stringToTest));
        }
        else {
            assertFalse("Fehler beim Testen von: '" + stringToTest,rpnTestStringToTest.stringIsValidToParse(stringToTest));
        }
    }
}