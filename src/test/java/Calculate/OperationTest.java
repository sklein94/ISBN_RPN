package Calculate;

import org.junit.Test;

import java.math.BigDecimal;

public interface OperationTest {
    void shouldBeCorrect() throws Exception;
    void notEnoughParameters() throws Exception;
    void tooManyParameters() throws Exception;
    void operatorShouldBeValid() throws Exception;
    void operatorShouldBeInvalid() throws Exception;
    void failOnThisNumberOfArguments(BigDecimal... arguments) throws Exception;
    void calculationOf(String... arguments) throws Exception;
    void checkOperatorToCalculate(String operator, boolean shouldBeCorrect) throws Exception;
}
