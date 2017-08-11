package RPN;

import org.junit.Test;

import static org.junit.Assert.*;

public class RPN_ParserTest {

    @Test
    public void calculatesCorrect() {

        this.testString("1 2 +", true);
        this.testString("1 2 -", true);
        this.testString("1 2 *", true);
        this.testString("1 2 /", true);
        this.testString("1 2 %", true);
        this.testString("1 !", true);
        this.testString("1 2 pow", true);
        this.testString("1 2 sqrt", true);
        this.testString("1 2 sqr", true);
        this.testString("1 2 + 3 *", true);
        this.testString("1 2 + 3 * 1 + 5 / 2 - 4 + sqrt sqr", true);
        this.testString("1 ! ! ! ! ! ! !", true);

    }

    @Test
    public void checkRPNStringShouldBeValid() {

        RPN_Parser rpnValidAddition = new RPN_Parser("1 2 +");
        assertTrue(this.errorMessage("stringToParseIsValid()", rpnValidAddition), RPN_Parser.validStringToParse(rpnValidAddition));

        RPN_Parser rpnValidSubstraction = new RPN_Parser("1 2 -");
        assertTrue(this.errorMessage("stringToParseIsValid()", rpnValidSubstraction), RPN_Parser.validStringToParse(rpnValidSubstraction));

        RPN_Parser rpnValidMultiplication = new RPN_Parser("1 2 *");
        assertTrue(this.errorMessage("stringToParseIsValid()", rpnValidMultiplication), RPN_Parser.validStringToParse(rpnValidMultiplication));

        RPN_Parser rpnValidDivision = new RPN_Parser("1 2 /");
        assertTrue(this.errorMessage("stringToParseIsValid()", rpnValidDivision), RPN_Parser.validStringToParse(rpnValidDivision));

        RPN_Parser rpnValidModulo = new RPN_Parser("1 2 %");
        assertTrue(this.errorMessage("stringToParseIsValid()", rpnValidModulo), RPN_Parser.validStringToParse(rpnValidModulo));

        RPN_Parser rpnValidFaculty = new RPN_Parser("2 !");
        assertTrue(this.errorMessage("stringToParseIsValid()", rpnValidFaculty), RPN_Parser.validStringToParse(rpnValidFaculty));

        RPN_Parser rpnValidPotency = new RPN_Parser("1 2 pow");
        assertTrue(this.errorMessage("stringToParseIsValid()", rpnValidPotency), RPN_Parser.validStringToParse(rpnValidPotency));

        RPN_Parser rpnValidSqrt = new RPN_Parser("4 sqrt");
        assertTrue(this.errorMessage("stringToParseIsValid()", rpnValidSqrt), RPN_Parser.validStringToParse(rpnValidSqrt));

        RPN_Parser rpnValidSquaring = new RPN_Parser("4 sqr");
        assertTrue(this.errorMessage("stringToParseIsValid()", rpnValidPotency), RPN_Parser.validStringToParse(rpnValidPotency));

        RPN_Parser rpnValidMultipleOperators = new RPN_Parser("1 2 + 1 2 - /");
        assertTrue(this.errorMessage("stringToParseIsValid()", rpnValidMultipleOperators), RPN_Parser.validStringToParse(rpnValidMultipleOperators));

        RPN_Parser rpnValidMultipleOperatorsWithOperatorsAtTheEnd = new RPN_Parser("1 2 3 4 + + +");
        assertTrue(this.errorMessage("stringToParseIsValid()", rpnValidMultipleOperatorsWithOperatorsAtTheEnd), RPN_Parser.validStringToParse(rpnValidMultipleOperatorsWithOperatorsAtTheEnd));

        RPN_Parser rpnValidMultipleOperatorsWithOperatorsAtTheEndAndInMiddle = new RPN_Parser("1 2 + 3 4 + +");
        assertTrue(this.errorMessage("stringToParseIsValid()", rpnValidMultipleOperatorsWithOperatorsAtTheEndAndInMiddle), RPN_Parser.validStringToParse(rpnValidMultipleOperatorsWithOperatorsAtTheEndAndInMiddle));

        RPN_Parser rpnValidMultipleOperatorsWithNegativeValuesA = new RPN_Parser("-1 2 + -3 -4 + +");
        assertTrue(this.errorMessage("stringToParseIsValid()", rpnValidMultipleOperatorsWithNegativeValuesA), RPN_Parser.validStringToParse(rpnValidMultipleOperatorsWithNegativeValuesA));

        RPN_Parser rpnValidMultipleOperatorsWithNegativeValuesB = new RPN_Parser("-1 -2 - +3 4 - +");
        assertTrue(this.errorMessage("stringToParseIsValid()", rpnValidMultipleOperatorsWithNegativeValuesB), RPN_Parser.validStringToParse(rpnValidMultipleOperatorsWithNegativeValuesB));

        RPN_Parser rpnValidMultipleFaculty = new RPN_Parser("1 ! ! ! ! ! !");
        assertTrue(this.errorMessage("stringToParseIsValid()", rpnValidMultipleFaculty), RPN_Parser.validStringToParse(rpnValidMultipleFaculty));

    }


    @Test
    public void checkRPNStringWrongNumerOfArguments() {

        RPN_Parser rpnInvalidAdditionNotEnoughOperandsA = new RPN_Parser("1 2 + +");
        assertFalse(this.errorMessage("stringToParseIsValid()", rpnInvalidAdditionNotEnoughOperandsA), RPN_Parser.validStringToParse(rpnInvalidAdditionNotEnoughOperandsA));

        RPN_Parser rpnInvalidAdditionNotEnoughOperandsB = new RPN_Parser("1 2 + 3 4 + + +");
        assertFalse(this.errorMessage("stringToParseIsValid()", rpnInvalidAdditionNotEnoughOperandsB), RPN_Parser.validStringToParse(rpnInvalidAdditionNotEnoughOperandsB));

        RPN_Parser rpnInvalidAdditionNoOperatorA = new RPN_Parser("1 2");
        assertFalse(this.errorMessage("stringToParseIsValid()", rpnInvalidAdditionNoOperatorA), RPN_Parser.validStringToParse(rpnInvalidAdditionNoOperatorA));

        RPN_Parser rpnInvalidAdditionNotEnoughOperators = new RPN_Parser("1 2 + 3 4 + 5");
        assertFalse(this.errorMessage("stringToParseIsValid()", rpnInvalidAdditionNotEnoughOperators), RPN_Parser.validStringToParse(rpnInvalidAdditionNotEnoughOperators));

    }

    @Test
    public void checkRPNStringInvalidCharacters() {

        RPN_Parser rpnInvalidAdditionIllegalCharacterA = new RPN_Parser("1 2 + D");
        assertFalse(this.errorMessage("stringToParseIsValid()", rpnInvalidAdditionIllegalCharacterA), RPN_Parser.validStringToParse(rpnInvalidAdditionIllegalCharacterA));

        RPN_Parser rpnInvalidAdditionIllegalCharacterB = new RPN_Parser("1 2 + #asdf");
        assertFalse(this.errorMessage("stringToParseIsValid()", rpnInvalidAdditionIllegalCharacterB), RPN_Parser.validStringToParse(rpnInvalidAdditionIllegalCharacterB));

        RPN_Parser rpnInvalidAdditionIllegalCharacterC = new RPN_Parser("1 2 + srqt");
        assertFalse(this.errorMessage("stringToParseIsValid()", rpnInvalidAdditionIllegalCharacterC), RPN_Parser.validStringToParse(rpnInvalidAdditionIllegalCharacterC));

        RPN_Parser rpnInvalidAdditionIllegalCharacterD = new RPN_Parser("1 2 + srttqq");
        assertFalse(this.errorMessage("stringToParseIsValid()", rpnInvalidAdditionIllegalCharacterD), RPN_Parser.validStringToParse(rpnInvalidAdditionIllegalCharacterD));

    }

    @Test
    public void checkRPNStringPositionsOfArguments() {

        RPN_Parser rpnInvalidAdditionWrongPositionOfOperatorsA = new RPN_Parser("+ 1 2");
        assertFalse(this.errorMessage("stringToParseIsValid()", rpnInvalidAdditionWrongPositionOfOperatorsA), RPN_Parser.validStringToParse(rpnInvalidAdditionWrongPositionOfOperatorsA));

        RPN_Parser rpnInvalidAdditionWrongPositionOfOperatorsB = new RPN_Parser("1 2 + + 3 4 +");
        assertFalse(this.errorMessage("stringToParseIsValid()", rpnInvalidAdditionWrongPositionOfOperatorsB), RPN_Parser.validStringToParse(rpnInvalidAdditionWrongPositionOfOperatorsB));

        RPN_Parser rpnInvalidAdditionWrongPositionOfOperatorsC = new RPN_Parser("1 + 2 3 4 +  +");
        assertFalse(this.errorMessage("stringToParseIsValid()", rpnInvalidAdditionWrongPositionOfOperatorsC), RPN_Parser.validStringToParse(rpnInvalidAdditionWrongPositionOfOperatorsC));

    }

    @Test
    public void checkRPNNumberOfOperandsFunctionCorrect() {

        RPN_Parser rpnCountOperandsCorrectA = new RPN_Parser("1 2+ ");
        assertEquals(this.errorMessage("numberOfOperands()", rpnCountOperandsCorrectA), 2, rpnCountOperandsCorrectA.numberOfOperands());

        RPN_Parser rpnCountOperandsCorrectB = new RPN_Parser("1 2 3 4 5 6 7 8 9 0 +");
        assertEquals(this.errorMessage("numberOfOperands()", rpnCountOperandsCorrectB), 10, rpnCountOperandsCorrectB.numberOfOperands());
    }


    @Test
    public void checkRPNNumberOfOperantorsWithTwoOperantsFunctionCorrect() {

        RPN_Parser rpnCountOperatorsCorrectA = new RPN_Parser("1 +  - * / ^ % 2");
        assertEquals(this.errorMessage("numberOfOperatorsWithTwoOperands()", rpnCountOperatorsCorrectA), 6, rpnCountOperatorsCorrectA.numberOfOperatorsWithTwoOperands());

        RPN_Parser rpnCountOperatorsCorrectB = new RPN_Parser("- - + + / / % +");
        assertEquals(this.errorMessage("numberOfOperatorsWithTwoOperands()", rpnCountOperatorsCorrectB), 8, rpnCountOperatorsCorrectB.numberOfOperatorsWithTwoOperands());
    }

    @Test
    public void checkRPNNumberOfOperantorsWithOneOperantFunctionCorrect() {

        RPN_Parser rpnCountOperatorsCorrectWithOneOperandA = new RPN_Parser("sqrt sqr sqrt !");
        assertEquals(this.errorMessage("numberOfOperatorsWithTwoOperands()", rpnCountOperatorsCorrectWithOneOperandA), 4, rpnCountOperatorsCorrectWithOneOperandA.numberOfOperatorsWithOneOperand());

        RPN_Parser rpnCountOperatorsCorrectWithOneOperandB = new RPN_Parser("! ! ! sqrt sqr");
        assertEquals(this.errorMessage("numberOfOperatorsWithTwoOperands()", rpnCountOperatorsCorrectWithOneOperandB), 5, rpnCountOperatorsCorrectWithOneOperandB.numberOfOperatorsWithOneOperand());
    }


    public String errorMessage(String method, RPN_Parser rpn) {
        return "Betroffene Methode(n): " + method + ". Betroffener String: " + rpn.getStringToParse();
    }

    public void testString(String stringToTest, boolean shouldBeCorrect) {
        RPN_Parser rpnTestStringToTest = new RPN_Parser(stringToTest);
        if (shouldBeCorrect) {
            assertTrue("Fehler beim Testen von: '" + stringToTest + "'. Der String sollte gueltig sein, ist aber ungueltig.", RPN_Parser.validStringToParse(rpnTestStringToTest));
        }
        else {
            assertFalse("Fehler beim Testen von: '" + stringToTest + "'. Der String sollte ungueltig sein, ist aber gueltig.", RPN_Parser.validStringToParse(rpnTestStringToTest));
        }
    }

    public void testString(String stringToTest, double expectedValue) {
        RPN_Parser rpnTestStringToTest = new RPN_Parser(stringToTest);
        assertEquals(expectedValue, RPN_Parser.parseRPN(rpnTestStringToTest), 0.0001);
    }

}