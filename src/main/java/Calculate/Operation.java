package Calculate;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.ServiceLoader;

public interface Operation {
    ServiceLoader<Operation> services = ServiceLoader.load(Operation.class);
    String getOperator();
    int getNumberOfArguments();
    BigDecimal calculate(BigDecimal... arr) throws Exception;
    boolean canCalculate(String operator);
}
