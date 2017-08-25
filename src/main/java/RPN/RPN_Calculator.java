package RPN;

import Calculate.Operation;
import com.sun.prism.shader.Solid_TextureRGB_AlphaTest_Loader;

import java.math.BigDecimal;
import java.util.ServiceLoader;
import java.util.Stack;

public class RPN_Calculator {
    private static ServiceLoader<Operation> services = ServiceLoader.load(Operation.class);

    public static BigDecimal calculateString(String stringToCalculate) throws Exception{
        String[] splittedString = RPN_Parser.parseString(stringToCalculate);
        Stack firstStack = new Stack();
        Stack calculateStack = new Stack();

        for (int i = splittedString.length-1; i >= 0; i--){
            firstStack.push(splittedString[i]);
        }

        while (!firstStack.isEmpty()){
            if (isNumber((String) firstStack.peek())){
                calculateStack.push(new BigDecimal((String) firstStack.pop()));
            }
            else{
                for (Operation o : services){
                    if (o.canCalculate((String) firstStack.peek())){
                        BigDecimal[] t = new BigDecimal[o.getNumberOfArguments()];
                        for(int i = 0; i < o.getNumberOfArguments(); i++){
                            t[i] = new BigDecimal((String) calculateStack.pop());
                        }
                        calculateStack.push(o.calculate(t));
                    }
                }
            }
        }

        return new BigDecimal((String) calculateStack.pop());
    }


    private static boolean isNumber(String s){
        try{
            new BigDecimal(s);
        }
        catch (NumberFormatException nfe){
            return false;
        }
        return true;
    }
}
