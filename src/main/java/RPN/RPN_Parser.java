package RPN;

import java.util.Stack;

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
        if (this.stringToParseIsValid()) {


            return 0.0;
        }
        else return Double.NaN;
    }

    //====================================================================================================
    //============================Parsen - Vorgang========================================================
    //====================================================================================================


    public Stack getArgumentsAsStackWithLeftArgumentOnTop() {
        Stack stackOfArguments = new Stack();
        Stack stackOfArgumentsInverted = new Stack();

        String splittedValues[] = stringToParse_Attribute.split(" ");

        for (int i = 0; i < splittedValues.length; i++){
            stackOfArguments.push(splittedValues[i]);
        }
        while (!stackOfArguments.empty()) {
            stackOfArgumentsInverted.push(stackOfArguments.pop());
        }


        return stackOfArgumentsInverted;
    }


    //====================================================================================================
    //============================Überprüfen eines Strings auf Gültigkeit=================================
    //====================================================================================================

    /*
     * Allgemeine Methoden zum Testen auf Gültigkeit
     */

    public boolean stringToParseIsValid() {
        boolean ok = true;


        Stack arguments = this.getArgumentsAsStackWithLeftArgumentOnTop();
        ok = ok && this.allArgumentsValid((Stack) arguments.clone());
        ok = ok && this.correctNumberAndPositioningOfArguments((Stack) arguments.clone());


        return ok;
    }

    public static boolean validStringToParse(String stringToParse) {
        RPN_Parser rpn = new RPN_Parser(stringToParse);
        return rpn.stringToParseIsValid();
    }

    public static boolean validStringToParse(RPN_Parser rpn) {
        return rpn.stringToParseIsValid();
    }

    private boolean correctNumberAndPositioningOfArguments(Stack arguments) {
        boolean ok = true;
        Stack firstStack = (Stack) arguments.clone();
        Stack secondStack = new Stack();

        while (!firstStack.empty()) {
            secondStack.push(firstStack.pop());
            ok = ok && (this.countOperatorsWithTwoArguments((Stack) secondStack.clone()) < this.countOperands((Stack) secondStack.clone()));
            ok = ok && (this.countOperatorsWithOneArgument((Stack) secondStack.clone()) <= this.countOperands((Stack) secondStack.clone()));
        }

        ok = ok && this.countOperatorsWithTwoArguments((Stack) secondStack.clone()) == (this.countOperands((Stack) secondStack.clone())-1);

        return ok;
    }

    private int countOperands(Stack arguments) {
        int operands = 0;

        while (!arguments.empty()) {
            String elementOfStack = (String) arguments.pop();
            if (this.isInteger(elementOfStack)) {
                operands++;
            }
        }

        return operands;
    }

    private int countOperatorsWithTwoArguments(Stack arguments) {
        int operators = 0;

        while (!arguments.empty()) {
            String elementOfStack = (String) arguments.pop();
            boolean ok = elementOfStack.equals("+");
            ok = ok || elementOfStack.equals("-");
            ok = ok || elementOfStack.equals("/");
            ok = ok || elementOfStack.equals("*");
            ok = ok || elementOfStack.equals("%");
            ok = ok || elementOfStack.equals("pow");
            if (ok) {
                operators++;
            }
        }

        return operators;
    }

    private int countOperatorsWithOneArgument(Stack arguments) {
        int operators = 0;

        while (!arguments.empty()) {
            String elementOfStack = (String) arguments.pop();
            boolean ok = elementOfStack.equals("pow");
            ok = ok || elementOfStack.equals("sqrt");
            ok = ok || elementOfStack.equals("sqr");
            if (ok) {
                operators++;
            }
        }

        return operators;
    }


    private boolean allArgumentsValid(Stack arguments) {
        Stack checkStack = arguments;
        boolean ok = true;

        while (!checkStack.empty()) {
            String elementOfStack = (String) checkStack.pop();
            boolean argumentValid = false;
            argumentValid = argumentValid || elementOfStack.equals("+");
            argumentValid = argumentValid || elementOfStack.equals("-");
            argumentValid = argumentValid || elementOfStack.equals("*");
            argumentValid = argumentValid || elementOfStack.equals("/");
            argumentValid = argumentValid || elementOfStack.equals("!");
            argumentValid = argumentValid || elementOfStack.equals("%");
            argumentValid = argumentValid || elementOfStack.equals("pow");
            argumentValid = argumentValid || elementOfStack.equals("sqrt");
            argumentValid = argumentValid || elementOfStack.equals("sqr");
            argumentValid = argumentValid || this.isInteger(elementOfStack);
            ok = ok && argumentValid;
        }
        return ok;
    }

    private int findEndOfArgumentInString(String stringToCheck, int startposition) {
        int position = stringToCheck.length();
        for (int i = startposition; i < stringToCheck.length(); i++) {
            if (stringToCheck.charAt(i) == ' ') {
                position = i;
                break;
            }
        }
        return position;
    }

    public boolean isInteger(String input) {
        try {
            Integer.parseInt(input);
            return true;
        }
        catch (NumberFormatException e) {
            return false;
        }
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

}
