package Calculate;

import Exceptions.NumberOfParametersException;

import java.math.BigDecimal;

public class Subtraction implements Operation {
    static String operator = "-";

    static{
        Operators.listOfOperatorsWithTwoOperands.add(Subtraction.operator);
    }

    public BigDecimal calculate(BigDecimal... arr) throws NumberOfParametersException {
        if (arr.length == 2) {
            BigDecimal sum = arr[0].subtract(arr[1]);
            return sum;
        }
        else {
            throw new NumberOfParametersException("Parameters: " + arr.length);
        }
    }

    public boolean canCalculate(String operator) {
        return operator.equals(Subtraction.operator);
    }
}
