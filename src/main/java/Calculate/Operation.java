package Calculate;

import java.math.BigDecimal;
import java.util.ArrayList;

public interface Operation {
    String getOperator();
    int getNumberOfArguments();
    BigDecimal calculate(BigDecimal... arr) throws Exception;
    boolean canCalculate(String operator);
}
