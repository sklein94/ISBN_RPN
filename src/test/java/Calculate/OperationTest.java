package Calculate;

import org.junit.Test;

public interface OperationTest {
    void shouldBeCorrect() throws Exception;
    void notEnoughParameters() throws Exception;
    void tooManyParameters() throws Exception;
    void operatorShouldBeValid() throws Exception;
    void operatorShouldBeInvalid() throws Exception;
}
