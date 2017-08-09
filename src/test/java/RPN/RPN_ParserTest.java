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
        assertTrue(this.errorMessage("stringToParseIsValid()", rpnValidDivision), RPN_Parser.validStringToParse(rpnValidDivision));

        RPN_Parser rpnValidFaculty = new RPN_Parser("1 2 !");
        assertTrue(this.errorMessage("stringToParseIsValid()", rpnValidDivision), RPN_Parser.validStringToParse(rpnValidDivision));

        RPN_Parser rpnValidPotency = new RPN_Parser("1 2 ^");
        assertTrue(this.errorMessage("stringToParseIsValid()", rpnValidDivision), RPN_Parser.validStringToParse(rpnValidDivision));
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


    public static String errorMessage(String method, RPN_Parser rpn) {
        return "Betroffene Methode(n): " + method + ". Betroffener String: " + rpn.getStringToParse();
    }


}