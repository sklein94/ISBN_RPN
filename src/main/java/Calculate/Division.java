package Calculate;

import Exceptions.NumberOfParametersException;

import java.math.BigDecimal;
import java.math.MathContext;

public class Division implements Operation {

    public BigDecimal calculate(BigDecimal... arr) throws Exception {
        if (arr.length == 2) {
            return arr[0].divide(arr[1], MathContext.DECIMAL128);
        }
        else {
            throw new NumberOfParametersException("Parameters: " + arr.length);
        }

    }

    public boolean canCalculate(String operator) {
        return operator.equals("/");
    }
}
