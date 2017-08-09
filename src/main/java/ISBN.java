public class ISBN {

    /*
     * Attribute
     */
    private String Isbn_Attribute;


    /*
     * Konstruktoren
     */

    public ISBN(String Isbn) {
        setISBN(Isbn);
    }


    /*
     * Getter und Setter
     */

    public String getISBN() {
        return this.Isbn_Attribute;
    }

    public void setISBN(String Isbn) {
        this.Isbn_Attribute = Isbn;
    }


    /*
     * Arbeitsmethoden
     */

    public boolean isValid() {
        boolean ok = true;
        String Isbn_CheckValid = this.deleteLines(this.Isbn_Attribute);
        int length = Isbn_CheckValid.length();

        ok = this.checkLength(Isbn_CheckValid);
        ok = ok && this.checkCharacters(Isbn_CheckValid);
        ok = ok && this.checkChecksum(Isbn_CheckValid);
        ok = ok && this.checkPrefix(Isbn_CheckValid);

        return ok;
    }

    public static boolean isValid(String Isbn) {
        ISBN Isbn_Object = new ISBN(Isbn);
        return Isbn_Object.isValid();
    }

    public static boolean isValid(ISBN Isbn) {
        return Isbn.isValid();
    }


    //Längenüberprüfung
    private boolean checkLength(String Isbn) {
        if (Isbn.length() == 13 || Isbn.length() == 10) {
            return true;
        }
        else return false;
    }

    //Zeichenüberprüfung
    private boolean checkCharacters(String Isbn) {
        boolean ok = true;
        int length = Isbn.length();

        for (int i = 0; i < length; i++) {
            ok = ok && ((Isbn.charAt(i) >= 48 && Isbn.charAt(i) <= 57));
        }
        return ok;
    }

    //Checksummenüberprüfung
    private boolean checkChecksum(String Isbn) {
        boolean ok = true;

        String lastChar = Isbn.substring(Isbn.length() - 1);
        String a = "" + this.getChecksum(Isbn);

        if (lastChar.charAt(0) != a.charAt(0)) {
            ok = false;
        }

        return ok;
    }

    //Präfixüberprüfung
    private boolean checkPrefix(String Isbn) {
        boolean ok = false;

        String substring = Isbn.substring(0, 3);

        ok = ok || substring.equals("978");
        ok = ok || substring.equals("979");
        ok = ok || (Isbn.length() == 10);


        return ok;
    }

    //Checksumme berechnen
    public int getChecksum(String Isbn) {
        int sum = 0;
        String Isbn_Checksum = this.deleteLines(Isbn);
        int length = Isbn_Checksum.length() - 1;
        int checksum = -1;


        if (Isbn.length() == 13) {
            for (int i = 1; i <= length; i++) {
                int value = Isbn_Checksum.charAt(i - 1) - 48;
                if ((i % 2) != 0) {
                    sum += value;
                }
                else {
                    sum += value * 3;
                }
            }
            checksum = (10 - (sum % 10)) % 10;
        }
        else {
            for (int i = 0; i < length; i++) {
                int value = Isbn_Checksum.charAt(i) - 48;
                sum += value * (i + 1);
            }
            checksum = sum % 11;
        }
        return checksum;
    }

    //Linien löschen
    public String deleteLines(String Isbn) {
        String replacedMinus = Isbn.replace("-", "");
        String replacedSpaceAndMinus = replacedMinus.replace(" ", "");
        return replacedSpaceAndMinus;
    }


}
