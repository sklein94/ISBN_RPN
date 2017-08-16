package Calculate;

import Exceptions.NumberOfParametersException;
import ch.obermuhlner.math.big.BigDecimalMath;

import java.math.BigDecimal;
import java.math.MathContext;

public class Squareroot implements Operation {
    static {
        Operators.listOfOperators.add("sqrt");
    }

    public BigDecimal calculate(BigDecimal... arr) throws Exception {
        if (arr.length == 1) {
            return BigDecimalMath.sqrt(arr[0], MathContext.UNLIMITED);
        }
        else {
            throw new NumberOfParametersException("Parameters: " + arr.length);
        }
    }

    public boolean canCalculate(String operator) {
        return operator.equals("sqrt");
    }
}
