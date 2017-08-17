package Calculate;

import Exceptions.NumberOfParametersException;

import java.math.BigDecimal;
import java.math.BigInteger;

public class Modulo implements Operation {
    static String operator = "%";

    static{
        Operations.listOfOperations.add(new Modulo());
        Operations.listOfOperators.add(operator);
    }

    public String getOperator() {
        return operator;
    }

    public int getNumberOfArguments() {
        return 2;
    }

    public BigDecimal calculate(BigDecimal... arr) throws Exception {
        if (arr.length == getNumberOfArguments()) {
            BigInteger leftParameter = arr[0].toBigIntegerExact();
            BigInteger rightParameter = arr[1].toBigIntegerExact();
            BigDecimal moduloOf = new BigDecimal(leftParameter.remainder(rightParameter));
            return moduloOf;
        }
        else {
            throw new NumberOfParametersException("Parameters: " + arr.length);
        }
    }

    public boolean canCalculate(String operator) {
        return operator.equals(Modulo.operator);
    }
}
