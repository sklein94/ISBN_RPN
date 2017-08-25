package RPN;

import Calculate.Operation;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.reflections.Reflections;

import java.util.Set;

import static org.junit.Assert.*;

public class RPN_ParserTest {
    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void checkRPNStringShouldBeValid() throws Exception {

        this.testString("1 2 +", true);
        this.testString("1 2 -", true);
        this.testString("1 2 *", true);
        this.testString("1 2 /", true);

        this.testString("1 2 pow", true);
        this.testString("2 1 %", true);

        this.testString("1 sqr", true);
        this.testString("4 sqrt", true);
        this.testString("1 !", true);
        this.testString("1 fac", true);

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
        this.testString("1 2 + D +", false);
        this.testString("1 2 + #asdf +", false);
        this.testString("1 2 + srqt +", false);
        this.testString("1 2 + srttqq +", false);
    }

    @Test
    public void checkRPNStringPositionsOfArguments() {
        this.testString("+ 1 2", false);
        this.testString("1 2 + + 3 4 +", false);
        this.testString("1 + 2 3 4 +  +", false);
    }

    @Test
    public void shouldThrowExceptionOnTheseStrings() throws Exception{
        this.testForException("+ 1 2");
        this.testForException("+ 2");
        this.testForException("1 2 - -");
    }

    private void testForException(String stringToCheck){
        this.expectedException.expect(NumberFormatException.class);
        RPN_Parser.parseString(stringToCheck);
    }

    private void testString(String stringToTest, boolean shouldBeCorrect) {
        RPN_Parser rpnTestStringToTest = new RPN_Parser();
        if (shouldBeCorrect) {
            assertTrue("Fehler beim Testen von: '" + stringToTest, rpnTestStringToTest.stringIsValidToParse(stringToTest));
        }
        else {
            assertFalse("Fehler beim Testen von: '" + stringToTest,rpnTestStringToTest.stringIsValidToParse(stringToTest));
        }
    }
}