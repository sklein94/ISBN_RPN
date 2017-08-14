package Calculate;

import java.math.BigDecimal;

public class Addition implements Operation {
    public BigDecimal calculate(BigDecimal... arr) throws NumberOfParameterException {
        if (arr.length == 2) {
            BigDecimal sum = arr[0].add(arr[1]);
            return sum;
        }
        else {
            throw new NumberOfParameterException("Parameteranzahl: " + arr.length);
        }
    }

    public boolean canCalculate(String operator) {
        return operator.equals("+");
    }
}
