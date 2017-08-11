package Calculate;

import java.math.BigDecimal;

public class Modulo implements Operation {
    public BigDecimal calculate(BigDecimal... arr) {
        while (arr[0].compareTo(arr[1]) >= 0){
            arr[0].subtract(arr[1]);
        }
        return arr[0];
    }

    public boolean canCalculate(String operator) {
        return operator.equals("%");
    }
}
