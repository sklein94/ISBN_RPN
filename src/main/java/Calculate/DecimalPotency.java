package Calculate;

import Exceptions.NumberOfParametersException;
import ch.obermuhlner.math.big.BigDecimalMath;

import java.math.BigDecimal;
import java.math.MathContext;

public class DecimalPotency implements Operation {
    public BigDecimal calculate(BigDecimal... arr) throws Exception {
        if (arr.length == 2) {
            return BigDecimalMath.pow(arr[0], arr[1], MathContext.UNLIMITED);
        }
        else {
            throw new NumberOfParametersException("Parameters: " + arr.length);
        }
    }

    public boolean canCalculate(String operator) {
        return operator.equals("pow");
    }


}
