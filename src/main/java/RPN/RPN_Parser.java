package RPN;

import Calculate.Operation;
import Calculate.Operations;

import java.util.Stack;

import static java.lang.Math.pow;
import static jdk.nashorn.internal.objects.NativeMath.sqrt;

public class RPN_Parser {
    boolean stringIsValidToParse(String stringToCheck) {
        String[] argumentList = stringToCheck.split(" ");
        boolean ok = true;
        ok = ok && this.allArgumentsValid(argumentList);
        ok = ok && this.numberOfArgumentsOk(argumentList);
        ok = ok && this.correctNumberAndPositioningOfArguments(argumentList);

        return ok;
    }

    private boolean correctNumberAndPositioningOfArguments(String[] argumentList) {
        boolean ok = true;

        /*for (int i = 0; i < argumentList.length; i++) {
            String subString = "";
            for (int b = 0; b < i; b++) {
                subString += (argumentList[b] + " ");
            }
            String[] subarray = subString.split(" ");
            ok = ok && this.numberOfArgumentsOk(subarray);
        }*/

        //Implementierung fehlt hier


        return ok;
    }

    private boolean numberOfArgumentsOk(String[] argumentList) {
        boolean ok = this.numberOfOperands(argumentList) == (this.numberOfOperators(argumentList, 2) + 1);
        ok = ok && ((this.numberOfOperands(argumentList) > 0) || (this.numberOfOperators(argumentList, 1) == 0));

       /* for (String s : argumentList){
            System.out.print(s+ " ");
        }
        System.out.println();

        System.out.println(this.numberOfOperators(argumentList, 1));
        System.out.println(this.numberOfOperators(argumentList, 2));
        System.out.println(this.numberOfOperands(argumentList));*/

        return ok;
    }

    private int numberOfOperators(String[] argumentList, int numberOfArguments) {
        int operators = 0;

        for (String temp : argumentList) {
            if (this.isOperator(temp)) {
                if (this.getArgumentNumber(temp) == numberOfArguments) {
                    operators++;
                }
            }
        }

        return operators;
    }

    private int numberOfOperands(String[] argumentList) {
        int operands = 0;

        for (String temp : argumentList) {
            if (this.isNumber(temp)) {
                operands++;
            }
        }

        return operands;
    }

    private int getArgumentNumber(String stringToCheck) {
        for (Operation op : Operations.listOfOperations) {
            if (op.getOperator().equals(stringToCheck)) {
                return op.getNumberOfArguments();
            }
        }
        return 0;
    }

    private boolean allArgumentsValid(String[] argumentList) {
        boolean ok = true;

        for (String temp : argumentList) {

            boolean checkSingleArgument = this.isNumber(temp) || this.isOperator(temp);
            ok = ok && checkSingleArgument;
        }

        return ok;
    }

    private boolean isOperator(String stringToCheck) {
        boolean ok = false;
        for (Operation op : Operations.listOfOperations) {
            String operator = op.getOperator();
            ok = ok || operator.equals(stringToCheck);
        }
        return ok;
    }

    public boolean isNumber(String input) {
        return this.isInteger(input) || this.isDouble(input);
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
}
