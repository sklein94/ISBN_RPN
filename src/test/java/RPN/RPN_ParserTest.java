package RPN;

import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class RPN_ParserTest {

    @Test
    public void checkRPNStringShouldBeValid() {
        this.testString("1 2 +", true);
        this.testString("1 2 -", true);
        this.testString("1 2 *", true);
        this.testString("1 2 /", true);
        this.testString("1 2 %", true);
        this.testString("2 !", true);
        this.testString("1 2 pow", true);
        this.testString("4 sqrt", true);
        this.testString("4 sqr", true);
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
        this.testString("1 2+ ", 2);
        this.testString("1 2 3 4 5 6 7 8 9 0 +", 10);
    }


    @Test
    public void checkRPNNumberOfOperantorsWithTwoOperantsFunctionCorrect() {
        this.testString("1 +  - * / ^ % 2", 6);
        this.testString("- - + + / / % + sqrt sqr ! pow", 9);
    }

    @Test
    public void checkRPNNumberOfOperantorsWithOneOperantFunctionCorrect() {
        this.testString("sqrt sqr sqrt !", 4);
        this.testString("! ! ! sqrt sqr", 5);
    }


    private void testString(String stringToTest, boolean shouldBeCorrect) {
        RPN_Parser rpnTestStringToTest = new RPN_Parser(stringToTest);
        if (shouldBeCorrect) {
            assertTrue("Fehler beim Testen von: '" + stringToTest, RPN_Parser.validStringToParse(rpnTestStringToTest));
        }
        else {
            assertFalse("Fehler beim Testen von: '" + stringToTest, RPN_Parser.validStringToParse(rpnTestStringToTest));
        }
    }

    private void testString(String stringToTest, double expectedValue) {
        RPN_Parser rpnTestStringToTest = new RPN_Parser(stringToTest);
        assertEquals(expectedValue, RPN_Parser.parseRPN(rpnTestStringToTest), 0.0001);
    }

}