package RPN;

import Calculate.Operators;

import java.util.Stack;

import static java.lang.Math.pow;
import static jdk.nashorn.internal.objects.NativeMath.sqrt;

public class RPN_Parser {

    //====================================================================================================
    //============================Attribute===============================================================
    //====================================================================================================

    private String stringToParse_Attribute;                 //Der String, der gespeichert wird, um später geparst zu werden

    //====================================================================================================
    //============================Konstruktoren===========================================================
    //====================================================================================================

    protected RPN_Parser(String stringToParse) {
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
    }


    //====================================================================================================
    //============================Parsen - Methoden zum Aufrufen von Außen================================
    //====================================================================================================


    //====================================================================================================
    //============================NEUE METHODEN ANFANG ANGADSASDASDASDASDA================================
    //====================================================================================================

    boolean stringIsValidToParse(String stringToCheck) {
        boolean ok = this.allArgumentsValid(stringToCheck);
        ok = ok && this.correctNumberAndPositioningOfArguments(stringToCheck);

        return ok;
    }


    private boolean allArgumentsValid(String stringToCheck){
        boolean ok = false;

        String[] splittedStringToCheck = stringToCheck.split(" ");
        for (String temp : splittedStringToCheck){
            for(String tempOperatorsTwoOperands : Operators.listOfOperatorsWithTwoOperands){
                ok = ok || temp.equals(tempOperatorsTwoOperands);
            }
            for(String tempOperatorsOneOperand : Operators.listOfOperatorsWithTwoOperands){
                ok = ok || temp.equals(tempOperatorsOneOperand);
            }
            ok = ok || this.isNumber(temp);
        }

        return ok;
    }

    private boolean correctNumberAndPositioningOfArguments(String stringToCheck){
        boolean ok = true;

        for (int i = 1; i < stringToCheck.length(); i++){
            String temp = stringToCheck.substring(0, i);
            ok = ok && this.hasMoreOperandsThanOperatorsWithTwoArguments(temp);
            ok = ok && this.hasMoreOrEqualOperandsComparedToOperatorsWithOneArgument(temp);
        }

        return ok;
    }

    private boolean hasMoreOperandsThanOperatorsWithTwoArguments(String stringToCheck){
        return this.countNumberOfOperatorsWithTwoOperators(stringToCheck) < this.countNumberOfOperands(stringToCheck);
    }

    private boolean hasMoreOrEqualOperandsComparedToOperatorsWithOneArgument(String stringToCheck){
        return this.countNumberOfOperatorsWithOneOperator(stringToCheck) >= this.countNumberOfOperands(stringToCheck);
    }

    private int countNumberOfOperands(String stringToCheck){
        int count = 0;
        String[] splittedString = stringToCheck.split(" ");
        for(String temp : splittedString){
            if (this.isNumber(temp)){
                count++;
            }
        }
        return count;
    }

    private int countNumberOfOperatorsWithTwoOperators(String stringToCheck){
        int count = 0;
        String[] splittedString = stringToCheck.split(" ");
        for(String temp : splittedString){
            for (String op : Operators.listOfOperatorsWithTwoOperands){
                if(temp.equals(op)){
                    count++;
                }
            }
        }
        return count;
    }

    private int countNumberOfOperatorsWithOneOperator(String stringToCheck){
        int count = 0;
        String[] splittedString = stringToCheck.split(" ");
        for(String temp : splittedString){
            for (String op : Operators.listOfOperatorsOneOperand){
                if(temp.equals(op)){
                    count++;
                }
            }
        }
        return count;
    }

    //====================================================================================================
    //============================NEUE MEthoden ende ENDE ENDE ASDASDASDASDASDASDASD======================
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
            double calculatedValue = 0.0;
            Stack stackOfValues = this.getArgumentsAsStackWithLeftArgumentOnTop();
            Stack stackToCalculate = new Stack();

            while (!stackOfValues.empty()) {
                stackToCalculate.push(stackOfValues.pop());
                if (!this.isNumber((String) stackToCalculate.peek())) {
                    String operator = (String) stackToCalculate.pop();
                    if (operator.equals("+")) {
                        double operandRight = Double.parseDouble((String) stackToCalculate.pop());
                        double operandLeft = Double.parseDouble((String) stackToCalculate.pop());
                        stackToCalculate.push("" + (operandLeft + operandRight));
                    }
                    else if (operator.equals("-")) {
                        double operandRight = Double.parseDouble((String) stackToCalculate.pop());
                        double operandLeft = Double.parseDouble((String) stackToCalculate.pop());
                        stackToCalculate.push("" + (operandLeft - operandRight));

                    }
                    else if (operator.equals("*")) {
                        double operandRight = Double.parseDouble((String) stackToCalculate.pop());
                        double operandLeft = Double.parseDouble((String) stackToCalculate.pop());
                        stackToCalculate.push("" + (operandLeft * operandRight));

                    }
                    else if (operator.equals("/")) {
                        double operandRight = Double.parseDouble((String) stackToCalculate.pop());
                        double operandLeft = Double.parseDouble((String) stackToCalculate.pop());
                        stackToCalculate.push("" + (operandLeft / operandRight));
                    }
                    else if (operator.equals("%")) {
                        double operandRight = Double.parseDouble((String) stackToCalculate.pop());
                        double operandLeft = Double.parseDouble((String) stackToCalculate.pop());
                        stackToCalculate.push("" + (operandLeft % operandRight));
                    }
                    else if (operator.equals("!")) {
                        double operandRight = Double.parseDouble((String) stackToCalculate.pop());
                        if (((int) operandRight) == operandRight) {
                            stackToCalculate.push("" + (this.faculty((int) operandRight)));
                        }
                        else return Double.NaN;
                    }
                    else if (operator.equals("pow")) {
                        double operandRight = Double.parseDouble((String) stackToCalculate.pop());
                        double operandLeft = Double.parseDouble((String) stackToCalculate.pop());
                        if (((int) operandRight) == operandRight) {
                            stackToCalculate.push("" + (pow(operandLeft, operandRight)));
                        }
                        else return Double.NaN;
                    }
                    else if (operator.equals("sqrt")) {
                        double operandRight = Double.parseDouble((String) stackToCalculate.pop());
                        stackToCalculate.push("" + (Math.sqrt(operandRight)));
                    }
                    else if (operator.equals("sqr")) {
                        double operandRight = Double.parseDouble((String) stackToCalculate.pop());
                        stackToCalculate.push("" + (pow(operandRight, 2)));
                    }
                    else return Double.NaN;
                }
            }

            return Double.parseDouble((String) stackToCalculate.pop());
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

        for (int i = 0; i < splittedValues.length; i++) {
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

        ok = ok && this.countOperatorsWithTwoArguments((Stack) secondStack.clone()) == (this.countOperands((Stack) secondStack.clone()) - 1);

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
            argumentValid = argumentValid || this.isNumber(elementOfStack);
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
        catch (NullPointerException e) {
            return false;
        }
    }

    public boolean isDouble(String input) {
        try {
            Double.parseDouble(input);
            return true;
        }
        catch (NumberFormatException e) {
            return false;
        }
        catch (NullPointerException e) {
            return false;
        }
    }

    public boolean isNumber(String input) {
        return this.isInteger(input) || this.isDouble(input);
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

    public double faculty(int number) {
        int sum = 0;
        for (int i = 1; i <= number; i++) {
            if (i == 1) sum = 1;
            sum *= i;
        }
        return sum;
    }

}
