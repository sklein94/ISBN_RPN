package Calculate;

import Exceptions.NumberOfParametersException;
import ch.obermuhlner.math.big.BigDecimalMath;

import java.math.BigDecimal;
import java.math.MathContext;

public class DecimalPotency implements Operation {
    static String operator = "pow";

    public String getOperator() {
        return operator;
    }

    public int getNumberOfArguments() {
        return 2;
    }

    public BigDecimal calculate(BigDecimal... arr) throws Exception {
        if (arr.length == getNumberOfArguments()) {
            return BigDecimalMath.pow(arr[0], arr[1], MathContext.UNLIMITED);
        }
        else {
            throw new NumberOfParametersException("Parameters: " + arr.length);
        }
    }

    public boolean canCalculate(String operator) {
        return operator.equals(DecimalPotency.operator);
    }


}
