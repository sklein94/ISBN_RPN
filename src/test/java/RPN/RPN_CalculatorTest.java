package RPN;

import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class RPN_CalculatorTest {
    @Test
    public void test() throws Exception{
        assertTrue(new BigDecimal("2").compareTo(RPN_Calculator.calculateString("1 1 +")) == 0);
    }
}