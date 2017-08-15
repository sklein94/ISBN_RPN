package Calculate;

import Exceptions.NumberOfParametersException;

import java.math.BigDecimal;

public class Division implements Operation {
    public BigDecimal calculate(BigDecimal... arr) throws NumberOfParametersException {
        if (arr.length == 2) {
            BigDecimal sum = arr[0].subtract(arr[1]);
            return sum;
        }
        else {
            throw new NumberOfParametersException("Parameteranzahl: " + arr.length);
        }
    }

    public boolean canCalculate(String operator) {
        return operator.equals("/");
    }
}
