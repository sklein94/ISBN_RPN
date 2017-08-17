package Calculate;

import Exceptions.NumberOfParametersException;

import java.math.BigDecimal;
import java.math.BigInteger;

public class Factorial implements Operation {
    static String operator = "!";

    static {
        Operations.listOfOperations.add(new Factorial());
        Operations.listOfOperators.add(operator);
    }

    public String getOperator() {
        return operator;
    }

    public int getNumberOfArguments() {
        return 1;
    }

    public BigDecimal calculate(BigDecimal... arr) throws Exception {
        if (arr.length == getNumberOfArguments()) {
            if (arr[0].toBigIntegerExact().compareTo(new BigInteger("0")) >= 0) {


                BigInteger endValue = arr[0].toBigIntegerExact();
                BigDecimal sum = new BigDecimal(1);
                for (int i = 1; endValue.compareTo(BigInteger.valueOf(i)) >= 0; i++) {
                    sum = sum.multiply(BigDecimal.valueOf(i));
                }

                return sum;
            }
            else {
                throw new ArithmeticException("Es wurde versucht, aus einer ungültigen Zahl eine Fakultät zu berechnen: " + arr[0]);
            }
        }
        else throw new NumberOfParametersException("Parameters: " + arr.length);
    }

    public boolean canCalculate(String operator) {
        return operator.equals(Factorial.operator);
    }
}
