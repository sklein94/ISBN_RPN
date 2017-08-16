package Calculate;

import Exceptions.NumberOfParametersException;

import java.math.BigDecimal;

public class Addition implements Operation {
    static String operator = "+";

    static{
        Operators.listOfOperatorsWithTwoOperands.add(operator);
    }

    public BigDecimal calculate(BigDecimal... arr) throws NumberOfParametersException {
        if (arr.length == 2) {
            BigDecimal sum = arr[0].add(arr[1]);
            return sum;
        }
        else {
            throw new NumberOfParametersException("Parameters: " + arr.length);
        }
    }

    public boolean canCalculate(String operator) {
        return operator.equals(Addition.operator);
    }
}
