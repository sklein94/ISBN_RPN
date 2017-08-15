//package Calculate;
//
//import Exceptions.NumberOfParametersException;
//import org.junit.Test;
//
//import java.math.BigDecimal;
//
//import static org.junit.Assert.*;
//
//public class SquarerootTest implements OperationTest{
//
//    @Test
//    public void shouldBeCorrect() throws Exception {
//        this.calculationOf("4", "2");
//        this.calculationOf("2.25", "1.5");
//        this.calculationOf("0", "0");
//    }
//
//    @Test
//    public void notNegativeValue(){
//        this.failOnThisArgument("-1");
//        this.failOnThisArgument("-0.25");
//    }
//
//    @Test
//    public void notEnoughParameters() throws Exception {
//        this.failOnThisNumberOfArguments();
//    }
//
//    @Test
//    public void tooManyParameters() throws Exception {
//        this.failOnThisNumberOfArguments(new BigDecimal(1),new BigDecimal(1));
//        this.failOnThisNumberOfArguments(new BigDecimal(1),new BigDecimal(1), new BigDecimal(1));
//        this.failOnThisNumberOfArguments(new BigDecimal(1),new BigDecimal(1), new BigDecimal(1), new BigDecimal(1));
//    }
//
//    @Test
//    public void operatorShouldBeValid() throws Exception {
//        checkOperatorToCalculate("sqrt", true);
//    }
//
//    @Test
//    public void operatorShouldBeInvalid() throws Exception {
//        for (String temp : Operators.listOfOperators){
//            if (!temp.equals("sqrt")){
//                checkOperatorToCalculate(temp, false);
//            }
//        }
//    }
//
//    private void failOnThisArgument(String argument){
//        Squareroot squareroot = new Squareroot();
//        BigDecimal argumentAsBigDecimal = new BigDecimal(argument);
//        try {
//            squareroot.calculate(argumentAsBigDecimal);
//            fail("Es wurde ein falsches Argument akzeptiert.");
//        }
//        catch (Exception e) {
//
//        }
//    }
//
//    public void failOnThisNumberOfArguments(BigDecimal... arguments) throws Exception {
//        Squareroot squareroot = new Squareroot();
//        try {
//            squareroot.calculate(arguments);
//            fail("Es wurde eine falsche Anzahl an Parametern akzeptiert.");
//        }
//        catch (NumberOfParametersException numberOfParameterException) {
//
//        }
//    }
//
//    public void calculationOf(String... arguments) throws Exception {
//        Squareroot squareroot = new Squareroot();
//        BigDecimal calculate = squareroot.calculate(new BigDecimal(arguments[0]));
//        BigDecimal expected = new BigDecimal(arguments[1]);
//        String message = "\nExpected: " + expected.toString() + "\nActual: " + calculate.toString();
//
//        assertEquals(message, 0, calculate.compareTo(expected));
//    }
//
//    public void checkOperatorToCalculate(String operator, boolean shouldBeCorrect) throws Exception {
//        Squareroot squareroot = new Squareroot();
//        String message = "Operator: " + operator;
//        if (shouldBeCorrect){
//            assertTrue(message, squareroot.canCalculate(operator));
//        }
//        else{
//            assertFalse(message, squareroot.canCalculate(operator));
//        }
//    }
//}