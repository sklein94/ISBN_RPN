package Calculate;

import Exceptions.NumberOfParametersException;

import java.math.BigDecimal;

public class Squareroot implements Operation {
    public BigDecimal calculate(BigDecimal... arr) throws Exception {
        if (arr.length == 1) {
            return new BigDecimal(1);
        }
        else {
            throw new NumberOfParametersException("Parameters: " + arr.length);
        }
    }

    private BigDecimal sqrt(){
        return new BigDecimal(1);
    }

    public boolean canCalculate(String operator) {
        return false;
    }
}
