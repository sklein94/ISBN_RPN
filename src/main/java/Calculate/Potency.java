package Calculate;

import Exceptions.NumberOfParametersException;

import java.math.BigDecimal;

public class Potency implements Operation {
    public BigDecimal calculate(BigDecimal... arr) throws Exception {
        if (arr.length == 2) {
            return arr[0].pow(arr[1].intValueExact());
        }
        else {
            throw new NumberOfParametersException("Parameters: " + arr.length);
        }
    }

    private boolean isInteger(BigDecimal bd) {
        try {
            bd.toBigIntegerExact();
            return true;
        }
        catch (ArithmeticException arithmeticException) {
            return false;
        }
    }

    public boolean canCalculate(String operator) {
        return operator.equals("pow");
    }


}
