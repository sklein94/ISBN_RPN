package Calculate;

import Exceptions.NumberOfParametersException;
import ch.obermuhlner.math.big.BigDecimalMath;

import java.math.BigDecimal;
import java.math.MathContext;

public class Squareroot implements Operation {
    static String operator = "sqrt";

    static {
        Operations.listOfOperations.add(new Squareroot());
        Operations.listOfOperators.add(operator);
    }

    public String getOperator() {
        return operator;
    }

    public int getNumberOfArguments() {
        return 1;
    }

    public BigDecimal calculate(BigDecimal... arr) throws Exception {
        if (arr.length == getNumberOfArguments()) {
            return BigDecimalMath.sqrt(arr[0], MathContext.UNLIMITED);
        }
        else {
            throw new NumberOfParametersException("Parameters: " + arr.length);
        }
    }

    public boolean canCalculate(String operator) {
        return operator.equals(Squareroot.operator);
    }
}
