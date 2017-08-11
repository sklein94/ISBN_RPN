package Calculate;

import java.math.BigDecimal;

public interface Operation {
    BigDecimal calculate(BigDecimal... arr);
    boolean canCalculate(String operator);
}
