package RPN;

import org.junit.Test;

import static org.junit.Assert.*;

public class RPN_ParserTest {

    @Test
    public void checkRPNStringShouldBeValid(){

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

        RPN_Parser rpnValidFaculty = new RPN_Parser("1 2 !");
        assertTrue(this.errorMessage("stringToParseIsValid()", rpnValidFaculty), RPN_Parser.validStringToParse(rpnValidFaculty));

        RPN_Parser rpnValidPotency = new RPN_Parser("1 2 ^");
        assertTrue(this.errorMessage("stringToParseIsValid()", rpnValidPotency), RPN_Parser.validStringToParse(rpnValidPotency));

        RPN_Parser rpnValidMultipleOperators = new RPN_Parser("1 2 + 1 2 - /");
        assertTrue(this.errorMessage("stringToParseIsValid()", rpnValidMultipleOperators), RPN_Parser.validStringToParse(rpnValidMultipleOperators));

        RPN_Parser rpnValidMultipleOperatorsWithoutSpaces = new RPN_Parser("1 2+1 2-/");
        assertTrue(this.errorMessage("stringToParseIsValid()", rpnValidMultipleOperatorsWithoutSpaces), RPN_Parser.validStringToParse(rpnValidMultipleOperatorsWithoutSpaces));
    }


    @Test
    public void checkRPNStringTooManyArguments(){

        RPN_Parser rpnInvalidAdditionOperandTooMuch = new RPN_Parser("1 2 1 +");
        assertFalse(this.errorMessage("stringToParseIsValid()", rpnInvalidAdditionOperandTooMuch), RPN_Parser.validStringToParse(rpnInvalidAdditionOperandTooMuch));

        RPN_Parser rpnInvalidAdditionOperatorTooMuch = new RPN_Parser("1 2 + +");
        assertFalse(this.errorMessage("stringToParseIsValid()", rpnInvalidAdditionOperatorTooMuch), RPN_Parser.validStringToParse(rpnInvalidAdditionOperatorTooMuch));
    }


    @Test
    public void checkRPNStringWrongNumerOfArguments(){

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
    public void checkRPNStringInvalidCharacters(){

        RPN_Parser rpnInvalidAdditionIllegalCharacterA = new RPN_Parser("1 2 + D");
        assertFalse(this.errorMessage("stringToParseIsValid()", rpnInvalidAdditionIllegalCharacterA), RPN_Parser.validStringToParse(rpnInvalidAdditionIllegalCharacterA));

        RPN_Parser rpnInvalidAdditionIllegalCharacterB = new RPN_Parser("1 2 + #asdf");
        assertFalse(this.errorMessage("stringToParseIsValid()", rpnInvalidAdditionIllegalCharacterB), RPN_Parser.validStringToParse(rpnInvalidAdditionIllegalCharacterB));

    }


    @Test
    public void checkRPNNumberOfOperandsFunctionCorrect(){

        RPN_Parser rpnCountOperandsCorrectA = new RPN_Parser("1 2 +");
        assertEquals(this.errorMessage("numberOfOperands()", rpnCountOperandsCorrectA),2, rpnCountOperandsCorrectA.numberOfOperands());

        RPN_Parser rpnCountOperandsCorrectB = new RPN_Parser("1 2 3 4 5 6 7 8 9 0 +");
        assertEquals(this.errorMessage("numberOfOperands()", rpnCountOperandsCorrectB),10, rpnCountOperandsCorrectB.numberOfOperands());
    }


    @Test
    public void checkRPNNumberOfOperantorsFunctionCorrect(){

        RPN_Parser rpnCountOperatorsCorrectA = new RPN_Parser("1 + - * / ^ ! % 2");
        assertEquals(this.errorMessage("numberOfOperands()", rpnCountOperatorsCorrectA),7, rpnCountOperatorsCorrectA.numberOfOperands());

        RPN_Parser rpnCountOperatorsCorrectB = new RPN_Parser("--++//%+");
        assertEquals(this.errorMessage("numberOfOperands()", rpnCountOperatorsCorrectB),8, rpnCountOperatorsCorrectB.numberOfOperands());
    }

    public static String errorMessage(String method, RPN_Parser rpn) {
        return "Betroffene Methode(n): " + method + ". Betroffener String: " + rpn.getStringToParse();
    }


}