package Calculate;

import Exceptions.NumberOfParametersException;

import java.math.BigDecimal;

public class Multiplication implements Operation {
    static String operator = "*";

    static{
        Operations.listOfOperations.add(new Multiplication());
        Operations.listOfOperators.add(operator);
    }

    public String getOperator() {
        return operator;
    }

    public int getNumberOfArguments() {
        return 2;
    }

    public BigDecimal calculate(BigDecimal... arr) throws Exception {
        if (arr.length == getNumberOfArguments()){
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
