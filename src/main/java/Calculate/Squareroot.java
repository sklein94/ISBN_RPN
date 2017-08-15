//package Calculate;
//
//import Exceptions.NumberOfParametersException;
//
//import java.math.BigDecimal;
//
//public class Squareroot implements Operation {
//    static {
//        Operators.listOfOperators.add("sqrt");
//    }
//
//    public BigDecimal calculate(BigDecimal... arr) throws Exception {
//        if (arr.length == 1) {
//            return this.sqrt(arr[0]);
//        }
//        else {
//            throw new NumberOfParametersException("Parameters: " + arr.length);
//        }
//    }
//
//   /* private BigDecimal sqrt(BigDecimal argument) {
//        int count = 0;
//        BigDecimal increment = BigDecimal.ONE;
//        BigDecimal value = increment;
//        while (!this.multipliedEquals(value, argument) && count < 1000) {
//            if (this.isMultipliedBiggerThan(value, argument)){
//
//            }
//            count++;
//        }
//        return value;
//
//    }
//
//    private boolean multipliedEquals(BigDecimal value, BigDecimal valueToCheck){
//        return value.multiply(value).compareTo(valueToCheck) == 0;
//    }
//
//    private boolean isMultipliedBiggerThan(BigDecimal value, BigDecimal valueToCheck) {
//        BigDecimal result = value.multiply(value);
//        return result.compareTo(valueToCheck) == 1;
//    }
//
//    private boolean isMultipliedBiggerThan(BigDecimal value, BigDecimal valueToCheck) {
//        BigDecimal result = value.multiply(value);
//        return result.compareTo(valueToCheck) == 1;
//        BigDecimal.sq
//    }
//
//    private boolean isWithIncrementMultipliedBiggerThan(BigDecimal value, BigDecimal increment, BigDecimal valueToCheck) {
//        BigDecimal result = value.add(increment).multiply(value.add(increment));
//        return result.compareTo(valueToCheck) == 1;
//    }
//
//    private boolean isWithDeductedIncrementMultipliedSmallerThan(BigDecimal value, BigDecimal increment, BigDecimal valueToCheck) {
//        BigDecimal result = value.subtract(increment).subtract(value.add(increment));
//        return result.compareTo(valueToCheck) == -1;
//    }
//
//    private BigDecimal sqr(BigDecimal argument) {
//        return argument.multiply(argument);
//    }
//
//    public boolean canCalculate(String operator) {
//        return operator.equals("sqrt");
//    }*/
//}
