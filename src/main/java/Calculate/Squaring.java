package Calculate;

import Exceptions.NumberOfParametersException;
import ch.obermuhlner.math.big.BigDecimalMath;

import java.math.BigDecimal;
import java.math.MathContext;

public class Squaring implements Operation {
    static{
        Operators.listOfOperators.add("sqr");
    }
    public BigDecimal calculate(BigDecimal... arr) throws Exception{
        if (arr.length == 1) {
            return arr[0].multiply(arr[0], MathContext.DECIMAL128);
        }
        else{
            throw new NumberOfParametersException("Parameters: " + arr.length);
        }
    }

    public boolean canCalculate(String operator) {
        return operator.equals("sqr");
    }
}
