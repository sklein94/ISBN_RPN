package Calculate;

import Exceptions.NumberOfParametersException;

import java.math.BigDecimal;
import java.math.MathContext;

public class Squaring implements Operation {
    static String operator = "sqr";

    public String getOperator() {
        return operator;
    }

    public int getNumberOfArguments() {
        return 1;
    }

    public BigDecimal calculate(BigDecimal... arr) throws Exception{
        if (arr.length == getNumberOfArguments()) {
            return arr[0].multiply(arr[0], MathContext.DECIMAL128);
        }
        else{
            throw new NumberOfParametersException("Parameters: " + arr.length);
        }
    }

    public boolean canCalculate(String operator) {
        return operator.equals(Squaring.operator);
    }
}
