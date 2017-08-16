package Calculate;

import Exceptions.NumberOfParametersException;
import ch.obermuhlner.math.big.BigDecimalMath;

import java.math.BigDecimal;
import java.math.BigInteger;

public class SecondFactorial implements Operation {
    static String operator = "fac";

    static {
        Operators.listOfOperatorsOneOperand.add(SecondFactorial.operator);
    }

    public BigDecimal calculate(BigDecimal... arr) throws Exception {
        if (arr.length == 1) {
            return BigDecimalMath.factorial(arr[0].intValueExact());
        }
        else throw new NumberOfParametersException("Parameters: " + arr.length);
    }

    public boolean canCalculate(String operator) {
        return operator.equals(SecondFactorial.operator);
    }
}
