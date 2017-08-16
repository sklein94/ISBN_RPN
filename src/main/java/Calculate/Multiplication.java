package Calculate;

import Exceptions.NumberOfParametersException;

import java.math.BigDecimal;

public class Multiplication implements Operation {
    static String operator = "*";

    static{
        Operators.listOfOperatorsWithTwoOperands.add(operator);
    }
    public BigDecimal calculate(BigDecimal... arr) throws Exception {
        if (arr.length == 2){
            return arr[0].multiply(arr[1]);
        }
        else{
            throw new NumberOfParametersException("Parameters: " + arr.length);
        }
    }

    public boolean canCalculate(String operator) {
        return operator.equals(Multiplication.operator);
    }
}
