package RPN;

public class RPN_Parser {

    /*
     * Attribute
     */
    private String stringToParse_Attribute;


    /*
     * Konstruktoren
     */
    public RPN_Parser(String stringToParse) {
        this.stringToParse_Attribute = stringToParse;
    }


    /*
     * Getter und Setter
     */

    public String getStringToParse() {
        return this.stringToParse_Attribute;
    }

    public void setStringToParse(String stringToParse) {
        this.stringToParse_Attribute = stringToParse;
    }


    /*
     * Arbeitsmethoden
     */


    /*
     * Parsen
     */

    public static double parseString(String stringToParse) {
        RPN_Parser rpn = new RPN_Parser(stringToParse);
        return rpn.parse();
    }

    public static double parseRPN(RPN_Parser RPNParserToCheck) {
        return RPNParserToCheck.parse();
    }

    private double parse(){
        if (this.stringToParseIsValid()){

            return 0.0;
        }
        else return Double.NaN;
    }

    /*
     * Überprüfung auf Gültigkeit
     */

    public boolean stringToParseIsValid() {
        boolean ok = true;

        return ok;
    }

    public static boolean validStringToParse(String stringToParse){
        RPN_Parser rpn = new RPN_Parser(stringToParse);
        return rpn.stringToParseIsValid();
    }

    public static boolean validStringToParse(RPN_Parser rpn){
        return rpn.stringToParseIsValid();
    }

    /*
     * Sonderfunktionen
     */

    private int numberOfOperands(){
        int number = 0;


        return number;
    }

    private int numberOfOperators(){
        int number = 0;


        return number;
    }

}
