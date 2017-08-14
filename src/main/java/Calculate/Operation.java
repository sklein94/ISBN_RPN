package Calculate;

import java.math.BigDecimal;

public interface Operation {
    BigDecimal calculate(BigDecimal... arr) throws Exception;
    boolean canCalculate(String operator);
}
