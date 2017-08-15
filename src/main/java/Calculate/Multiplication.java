package Calculate;

import Exceptions.NumberOfParametersException;

import java.math.BigDecimal;

public class Multiplication implements Operation {
    public BigDecimal calculate(BigDecimal... arr) throws Exception {
        if (arr.length == 2){
            return new BigDecimal(1);

        }
        else{
            throw new NumberOfParametersException("Parameters: " + arr.length);
        }
    }

    public boolean canCalculate(String operator) {
        return operator.equals("*");
    }
}
