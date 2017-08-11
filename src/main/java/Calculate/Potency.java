package Calculate;

import java.math.BigDecimal;

public class Potency implements Operation {
    public BigDecimal calculate(BigDecimal... arr) {
        return arr[0].pow(arr[1].intValueExact());
    }

    public boolean canCalculate(String operator) {
        return operator.equals("pow");
    }
}
