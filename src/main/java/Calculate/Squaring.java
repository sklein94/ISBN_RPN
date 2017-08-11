package Calculate;

import java.math.BigDecimal;

public class Squaring implements Operation {
    public BigDecimal calculate(BigDecimal... arr) {
        return arr[0].pow(2);
    }

    public boolean canCalculate(String operator) {
        return operator.equals("sqr");
    }
}
