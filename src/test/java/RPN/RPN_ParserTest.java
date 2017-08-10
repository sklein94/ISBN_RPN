package RPN;

import org.junit.Test;

import static org.junit.Assert.*;

public class RPN_ParserTest {

    @Test
    public void calculatesCorrect(){
        RPN_Parser rpnCalculatesCorrectResultAddition = new RPN_Parser("1 2 +");
        assertEquals(this.errorMessage("parse()", rpnCalculatesCorrectResultAddition),3, rpnCalculatesCorrectResultAddition.numberOfOperands());

        RPN_Parser rpnCalculatesCorrectSubstraction = new RPN_Parser("1 2 -");
        assertEquals(this.errorMessage("parse()", rpnCalculatesCorrectSubstraction),-1, rpnCalculatesCorrectSubstraction.numberOfOperands());

        RPN_Parser rpnCalculatesCorrectMultiplication = new RPN_Parser("3 2 *");
        assertEquals(this.errorMessage("parse()", rpnCalculatesCorrectSubstraction),6, rpnCalculatesCorrectSubstraction.numberOfOperands());

        RPN_Parser rpnCalculatesCorrectDivision = new RPN_Parser("6 2 /");
        assertEquals(this.errorMessage("parse()", rpnCalculatesCorrectSubstraction),3, rpnCalculatesCorrectSubstraction.numberOfOperands());

        RPN_Parser rpnCalculatesCorrectModulo = new RPN_Parser("10 3 %");
        assertEquals(this.errorMessage("parse()", rpnCalculatesCorrectSubstraction),1, rpnCalculatesCorrectSubstraction.numberOfOperands());

        RPN_Parser rpnCalculatesCorrectFaculty = new RPN_Parser("10 !");
        assertEquals(this.errorMessage("parse()", rpnCalculatesCorrectSubstraction),3628800, rpnCalculatesCorrectSubstraction.numberOfOperands());

        RPN_Parser rpnCalculatesCorrectPotency = new RPN_Parser("3 2 pow");
        assertEquals(this.errorMessage("parse()", rpnCalculatesCorrectSubstraction),9, rpnCalculatesCorrectSubstraction.numberOfOperands());

        RPN_Parser rpnCalculatesCorrectSqrt = new RPN_Parser("4 sqrt");
        assertEquals(this.errorMessage("parse()", rpnCalculatesCorrectSubstraction),2, rpnCalculatesCorrectSubstraction.numberOfOperands());

        RPN_Parser rpnCalculatesCorrectSqr = new RPN_Parser("2 sqr");
        assertEquals(this.errorMessage("parse()", rpnCalculatesCorrectSubstraction),4, rpnCalculatesCorrectSubstraction.numberOfOperands());

        RPN_Parser rpnCalculatesComplexA = new RPN_Parser("1 2 + 3 *");
        assertEquals(this.errorMessage("parse()", rpnCalculatesCorrectSubstraction),9, rpnCalculatesCorrectSubstraction.numberOfOperands());

        RPN_Parser rpnCalculatesComplexB = new RPN_Parser("1 2 + 3 * 1 + 5 / 2 - 4 + sqrt sqr");
        assertEquals(this.errorMessage("parse()", rpnCalculatesCorrectSubstraction),4, rpnCalculatesCorrectSubstraction.numberOfOperands());
    }

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

        RPN_Parser rpnValidMultipleOperatorsWithOperatorsAtTheEndAndInMiddle = new RPN_Parser("1 2 +  3 4 + +");
        assertFalse(this.errorMessage("stringToParseIsValid()", rpnValidMultipleOperatorsWithOperatorsAtTheEndAndInMiddle), RPN_Parser.validStringToParse(rpnValidMultipleOperatorsWithOperatorsAtTheEndAndInMiddle));

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
    public void checkRPNStringPositionsOfArguments(){

        RPN_Parser rpnInvalidAdditionWrongPositionOfOperatorsA = new RPN_Parser("+ 1 2");
        assertFalse(this.errorMessage("stringToParseIsValid()", rpnInvalidAdditionWrongPositionOfOperatorsA), RPN_Parser.validStringToParse(rpnInvalidAdditionWrongPositionOfOperatorsA));

        RPN_Parser rpnInvalidAdditionWrongPositionOfOperatorsB = new RPN_Parser("1 2 + + 3 4 +");
        assertFalse(this.errorMessage("stringToParseIsValid()", rpnInvalidAdditionWrongPositionOfOperatorsB), RPN_Parser.validStringToParse(rpnInvalidAdditionWrongPositionOfOperatorsB));

        RPN_Parser rpnInvalidAdditionWrongPositionOfOperatorsC = new RPN_Parser("1 + 2 3 4 +  +");
        assertFalse(this.errorMessage("stringToParseIsValid()", rpnInvalidAdditionWrongPositionOfOperatorsC), RPN_Parser.validStringToParse(rpnInvalidAdditionWrongPositionOfOperatorsC));

    }

    @Test
    public void checkRPNNumberOfOperandsFunctionCorrect(){

        RPN_Parser rpnCountOperandsCorrectA = new RPN_Parser("1 2+ ");
        assertEquals(this.errorMessage("numberOfOperands()", rpnCountOperandsCorrectA),2, rpnCountOperandsCorrectA.numberOfOperands());

        RPN_Parser rpnCountOperandsCorrectB = new RPN_Parser("1 2 3 4 5 6 7 8 9 0 +");
        assertEquals(this.errorMessage("numberOfOperands()", rpnCountOperandsCorrectB),10, rpnCountOperandsCorrectB.numberOfOperands());
    }


    @Test
    public void checkRPNNumberOfOperantorsWithTwoOperantsFunctionCorrect(){

        RPN_Parser rpnCountOperatorsCorrectA = new RPN_Parser("1 +  - * / ^ % 2");
        assertEquals(this.errorMessage("numberOfOperatorsWithTwoOperands()", rpnCountOperatorsCorrectA),6, rpnCountOperatorsCorrectA.numberOfOperatorsWithTwoOperands());

        RPN_Parser rpnCountOperatorsCorrectB = new RPN_Parser("- - + + / / % +");
        assertEquals(this.errorMessage("numberOfOperatorsWithTwoOperands()", rpnCountOperatorsCorrectB),8, rpnCountOperatorsCorrectB.numberOfOperatorsWithTwoOperands());
    }

    @Test
    public void checkRPNNumberOfOperantorsWithOneOperantFunctionCorrect(){

        RPN_Parser rpnCountOperatorsCorrectWithOneOperandA = new RPN_Parser("sqrt sqr sqrt !");
        assertEquals(this.errorMessage("numberOfOperatorsWithTwoOperands()", rpnCountOperatorsCorrectWithOneOperandA),4, rpnCountOperatorsCorrectWithOneOperandA.numberOfOperatorsWithOneOperand());

        RPN_Parser rpnCountOperatorsCorrectWithOneOperandB = new RPN_Parser("! ! ! sqrt sqr");
        assertEquals(this.errorMessage("numberOfOperatorsWithTwoOperands()", rpnCountOperatorsCorrectWithOneOperandB),5, rpnCountOperatorsCorrectWithOneOperandB.numberOfOperatorsWithOneOperand());
    }

    @Test
    public void checkRPNgetCharactersCorrect(){
        RPN_Parser rpnGetCharactersCorrect = new RPN_Parser("1 55 +  - * / ^ ! % 255");
        assertEquals(this.errorMessage("getNextOperatorOrOperand()", rpnGetCharactersCorrect),"1", rpnGetCharactersCorrect.getNextOperatorOrOperand());
        assertEquals(this.errorMessage("getNextOperatorOrOperand()", rpnGetCharactersCorrect),"55", rpnGetCharactersCorrect.getNextOperatorOrOperand());
        assertEquals(this.errorMessage("getNextOperatorOrOperand()", rpnGetCharactersCorrect),"+", rpnGetCharactersCorrect.getNextOperatorOrOperand());
        assertEquals(this.errorMessage("getNextOperatorOrOperand()", rpnGetCharactersCorrect),"-", rpnGetCharactersCorrect.getNextOperatorOrOperand());
        assertEquals(this.errorMessage("getNextOperatorOrOperand()", rpnGetCharactersCorrect),"*", rpnGetCharactersCorrect.getNextOperatorOrOperand());
        assertEquals(this.errorMessage("getNextOperatorOrOperand()", rpnGetCharactersCorrect),"/", rpnGetCharactersCorrect.getNextOperatorOrOperand());
        assertEquals(this.errorMessage("getNextOperatorOrOperand()", rpnGetCharactersCorrect),"^", rpnGetCharactersCorrect.getNextOperatorOrOperand());
        assertEquals(this.errorMessage("getNextOperatorOrOperand()", rpnGetCharactersCorrect),"!", rpnGetCharactersCorrect.getNextOperatorOrOperand());
        assertEquals(this.errorMessage("getNextOperatorOrOperand()", rpnGetCharactersCorrect),"%", rpnGetCharactersCorrect.getNextOperatorOrOperand());
        assertEquals(this.errorMessage("getNextOperatorOrOperand()", rpnGetCharactersCorrect),"255", rpnGetCharactersCorrect.getNextOperatorOrOperand());
    }


    public static String errorMessage(String method, RPN_Parser rpn) {
        return "Betroffene Methode(n): " + method + ". Betroffener String: " + rpn.getStringToParse();
    }


}