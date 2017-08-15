package Calculate;

import Exceptions.NumberOfParametersException;

import java.math.BigDecimal;
import java.math.BigInteger;

public class Factorial implements Operation {
    static{
        Operators.listOfOperators.add("!");
    }
    public BigDecimal calculate(BigDecimal... arr) throws Exception{
        if (arr.length == 1) {
            if (arr[0].toBigIntegerExact().compareTo(new BigInteger("0")) >= 0) {


                BigInteger endValue = arr[0].toBigIntegerExact();
                BigDecimal sum = new BigDecimal(1);
                for (int i = 1; endValue.compareTo(BigInteger.valueOf(i)) >= 0; i++) {
                    sum = sum.multiply(BigDecimal.valueOf(i));
                }


                return sum;
            }
            else {
                throw new ArithmeticException("Es wurde versucht, aus einer ungültigen Zahl eine Fakultät zu berechnen: "+ arr[0]);
            }
        }
        else throw new NumberOfParametersException("Falsche Anzahl an Parametern.");
    }

    public boolean canCalculate(String operator) {
        return operator.equals("!");
    }
}
