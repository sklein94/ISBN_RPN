package Calculate;

import Exceptions.NumberOfParametersException;
import ch.obermuhlner.math.big.BigDecimalMath;

import java.math.BigDecimal;

public class SecondFactorial implements Operation {
    static String operator = "fac";

    static {
        Operations.listOfOperations.add(new SecondFactorial());
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
            return BigDecimalMath.factorial(arr[0].intValueExact());
        }
        else throw new NumberOfParametersException("Parameters: " + arr.length);
    }

    public boolean canCalculate(String operator) {
        return operator.equals(SecondFactorial.operator);
    }
}
