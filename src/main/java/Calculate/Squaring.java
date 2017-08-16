package Calculate;

import Exceptions.NumberOfParametersException;

import java.math.BigDecimal;

public class Squaring implements Operation {
    static{
        Operators.listOfOperators.add("sqr");
    }
    public BigDecimal calculate(BigDecimal... arr) throws Exception{
        if (arr.length == 1) {
            return arr[0].pow(2);
        }
        else{
            throw new NumberOfParametersException("Parameters: " + arr.length);
        }
    }

    public boolean canCalculate(String operator) {
        return operator.equals("sqr");
    }
}
