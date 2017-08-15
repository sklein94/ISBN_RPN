package Calculate;

import java.math.BigDecimal;
import java.util.ArrayList;

public interface Operation {
    BigDecimal calculate(BigDecimal... arr) throws Exception;
    boolean canCalculate(String operator);
}
