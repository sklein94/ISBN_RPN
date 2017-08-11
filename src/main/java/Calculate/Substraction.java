package Calculate;

import java.math.BigDecimal;

public class Substraction implements Operation {

    public BigDecimal calculate(BigDecimal... arr) {
        int length = arr.length;
        BigDecimal sum = new BigDecimal(0);
        for (int i = 0; i < length; i++) {
            sum.subtract(arr[i]);
        }
        return sum;
    }

    public boolean canCalculate(String operator) {
        return operator.equals("-");
    }
}
