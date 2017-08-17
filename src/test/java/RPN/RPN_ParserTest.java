package RPN;

import Calculate.Addition;
import Calculate.Operation;
import Calculate.Operations;
import javafx.scene.effect.Reflection;
import org.junit.Test;
import org.reflections.Reflections;

import java.util.Set;

import static org.junit.Assert.*;

public class RPN_ParserTest {

    @Test
    public void checkRPNStringShouldBeValid() throws Exception {
        Reflections reflections = new Reflections("Calculate");
        Set<Class<? extends Operation>> classes = reflections.getSubTypesOf(Operation.class);
        for(Class o : classes){
            o.newInstance();
        }
        String testString = " ";
        for (Object temp : Operations.listOfOperations) {
            Operation o = (Operation) temp;
            if (o.getNumberOfArguments() == 2){
                testString = "1 2 ";
            }
            else if (o.getNumberOfArguments() == 1){
                testString = "16 ";
            }
            testString += o.getOperator();
            this.testString(testString, true);
        }

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
    public void checkRPNNumberOfOperandsFunctionCorrect() {
    }


    @Test
    public void checkRPNNumberOfOperantorsWithTwoOperantsFunctionCorrect() {
    }

    @Test
    public void checkRPNNumberOfOperantorsWithOneOperantFunctionCorrect() {
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