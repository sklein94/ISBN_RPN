package Calculate;

import java.math.BigDecimal;

public class Factorial implements Operation {
    public BigDecimal calculate(BigDecimal... arr) {
        BigDecimal sum = new BigDecimal(0);
        for (int i = 1; arr[0].compareTo(BigDecimal.valueOf(i)) > 0; i++) {
            if (i == 1) sum.add(BigDecimal.valueOf(1));
            sum.multiply(BigDecimal.valueOf(i));
        }
        return sum;
    }

    public boolean canCalculate(String operator) {
        return operator.equals("!") || operator.equals("fac");
    }
}
