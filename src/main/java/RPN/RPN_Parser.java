package RPN;

public class RPN_Parser {

    //====================================================================================================
    //============================Attribute===============================================================
    //====================================================================================================

    private String stringToParse_Attribute;                 //Der String, der gespeichert wird, um später geparst zu werden
    private String stringToParse_StringWhileParsing;        //Der String, der aktuell geparst wird. Wird bei jedem Zwischenschritt beim Parsen verändert


    //====================================================================================================
    //============================Konstruktoren===========================================================
    //====================================================================================================

    public RPN_Parser(String stringToParse) {
        this.setStringToParse(stringToParse);
    }


    //====================================================================================================
    //============================Getter und Setter=======================================================
    //====================================================================================================

    //Gibt den unveränderlichen String aus, der in dem Objekt gespeichert ist.
    public String getStringToParse() {
        return this.stringToParse_Attribute;
    }

    //Setzt den String, der aktuell gaparst werden soll, auf den in dem Objekt gespeicherten String.
    private void setStringToParse(String stringToParse) {
        this.stringToParse_Attribute = stringToParse;
        this.stringToParse_StringWhileParsing = stringToParse;
    }


    //====================================================================================================
    //============================Parsen - Methoden zum Aufrufen von Außen================================
    //====================================================================================================

    public static double parseString(String stringToParse) {
        RPN_Parser rpn = new RPN_Parser(stringToParse);
        return rpn.parse();
    }

    public static double parseRPN(RPN_Parser RPNParserToCheck) {
        return RPNParserToCheck.parse();
    }

    private double parse() {
        this.setCurrentParsingString();
        if (this.stringToParseIsValid()) {


            return Double.NaN;
        }
        else return Double.NaN;
    }

    //====================================================================================================
    //============================Parsen - Vorgang========================================================
    //====================================================================================================

    private void setCurrentParsingString() {
        this.stringToParse_StringWhileParsing = this.stringToParse_Attribute;
    }

    public String getNextOperatorOrOperand() {
        String currentParsingString = this.stringToParse_StringWhileParsing;
        String nextOperatorOrOperand = "";

        for (int i = 0; i < currentParsingString.length(); i++) {
            char currentCharacter = currentParsingString.charAt(i);
            if (currentCharacter != ' ') {
                nextOperatorOrOperand = currentParsingString.substring(i, this.findEndOfArgumentInString(currentParsingString, i));
                currentParsingString = currentParsingString.substring(this.findEndOfArgumentInString(currentParsingString, i));
                break;
            }
        }

        this.stringToParse_StringWhileParsing = currentParsingString;

        return nextOperatorOrOperand;
    }


    //====================================================================================================
    //============================Überprüfen eines Strings auf Gültigkeit=================================
    //====================================================================================================

    /*
     * Allgemeine Methoden zum Testen auf Gültigkeit
     */

    public boolean stringToParseIsValid() {
        boolean ok = this.correctNumberOfArguments();
        ok = ok && this.noInvalidCharacters();
        ok = ok && this.correctPositionsOfArguments();

        return ok;
    }

    public static boolean validStringToParse(String stringToParse) {
        RPN_Parser rpn = new RPN_Parser(stringToParse);
        return rpn.stringToParseIsValid();
    }

    public static boolean validStringToParse(RPN_Parser rpn) {
        return rpn.stringToParseIsValid();
    }
    
    /*
     * Einzelne Testmethoden auf Gültigkeit der Teilbereiche
     */

    private boolean correctPositionsOfArguments() {
        boolean ok = false;


        return ok;
    }

    private boolean correctNumberOfArguments() {
        boolean ok = (this.numberOfOperands() == (this.numberOfOperatorsWithTwoOperands() + 1));
        return ok;
    }

    private boolean noInvalidCharacters() {
        boolean ok = true;
        String stringToCheck = this.stringToParse_Attribute;

        for (int i = 0; i < stringToCheck.length(); i++) {
            char ch = stringToCheck.charAt(i);
            ok = ok && this.isValidCharacter(ch);
        }


        return ok;
    }

    public int numberOfOperands() {
        int number = 0;

        for (int i = 0; i < this.stringToParse_Attribute.length(); i++) {
            char currentCharOfStringToParse = this.stringToParse_Attribute.charAt(i);
            int valueOfCurrentChar = Character.getNumericValue(currentCharOfStringToParse);
            if (valueOfCurrentChar > -1) {
                number++;
            }
        }

        return number;
    }

    public int numberOfOperatorsWithTwoOperands() {
        int number = 0;

        for (int i = 0; i < this.stringToParse_Attribute.length(); i++) {
            char currentCharOfStringToParse = this.stringToParse_Attribute.charAt(i);
            boolean ok = currentCharOfStringToParse == '+';
            ok = ok || currentCharOfStringToParse == '-';
            ok = ok || currentCharOfStringToParse == '*';
            ok = ok || currentCharOfStringToParse == '/';
            ok = ok || currentCharOfStringToParse == '%';
            ok = ok || currentCharOfStringToParse == '^';
            if (ok) {
                number++;
            }
        }

        return number;
    }

    public int numberOfOperatorsWithOneOperand() {
        int number = 0;

        for (int i = 0; i < this.stringToParse_Attribute.length(); i++) {
            char currentCharOfStringToParse = this.stringToParse_Attribute.charAt(i);
            boolean ok = currentCharOfStringToParse == '!';
            ok = ok || (currentCharOfStringToParse == 's' && this.stringToParse_Attribute.length() >= (i + 4));
            ok = ok || (currentCharOfStringToParse == 's' && this.stringToParse_Attribute.length() >= (i + 3));
            if (ok) {
                number++;
            }
        }

        return number;
    }

    private boolean isValidCharacter(char characterToTest) {
        boolean characterValid = characterToTest >= '0' && characterToTest <= '9';
        characterValid = characterValid || characterToTest == '+';
        characterValid = characterValid || characterToTest == '-';
        characterValid = characterValid || characterToTest == '*';
        characterValid = characterValid || characterToTest == '/';
        characterValid = characterValid || characterToTest == '%';
        characterValid = characterValid || characterToTest == '!';
        characterValid = characterValid || characterToTest == '^';
        characterValid = characterValid || characterToTest == ' ';
        characterValid = characterValid || characterToTest == 's';
        characterValid = characterValid || characterToTest == 'q';
        characterValid = characterValid || characterToTest == 'r';
        characterValid = characterValid || characterToTest == 't';
        return characterValid;
    }

    private int findEndOfArgumentInString(String stringToCheck, int startposition) {
        /*if (Character.isDigit(stringToCheck.charAt(startposition))) {
            for (int i = startposition; i < stringToCheck.length(); i++) {
                if (!Character.isDigit(stringToCheck.charAt(i))) {
                    return i;
                }
                else if (i == stringToCheck.length()-1) return stringToCheck.length();
            }
        }
        return startposition + 1;*/
        int position = stringToCheck.length();
        for (int i = startposition; i < stringToCheck.length(); i++) {
            if (stringToCheck.charAt(i) == ' '){
                position =  i;
                break;
            }
        }
        return position;
    }

}
