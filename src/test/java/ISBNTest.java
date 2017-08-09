import org.junit.Test;

import static org.junit.Assert.*;

public class ISBNTest {

    @Test
    public void checkISBNShouldBeCorrect() {
        /*
         * ISBN-13
         */
        ISBN IsbnCorrectLines13 = new ISBN("978-3-12-732320-7");
        assertTrue(errorMessage("isValid()", IsbnCorrectLines13), ISBN.isValid(IsbnCorrectLines13));

        ISBN IsbnCorrectSpaces13 = new ISBN("97 831273 23207");
        assertTrue(errorMessage("isValid()", IsbnCorrectSpaces13), ISBN.isValid(IsbnCorrectSpaces13));

        ISBN IsbnCorrectWithoutSpacesAndLines13 = new ISBN("9783127323207");
        assertTrue(errorMessage("isValid()", IsbnCorrectWithoutSpacesAndLines13), ISBN.isValid(IsbnCorrectWithoutSpacesAndLines13));

        /*
         * ISBN-10
         */

        //Gültige ISBNs
        ISBN IsbnCorrectLines10 = new ISBN("3-86680-192-0");
        assertTrue(errorMessage("isValid()", IsbnCorrectLines10), ISBN.isValid(IsbnCorrectLines10));

        ISBN IsbnCorrectSpaces10 = new ISBN("368 0087 8   37");
        assertTrue(errorMessage("isValid()", IsbnCorrectSpaces10), ISBN.isValid(IsbnCorrectSpaces10));

        ISBN IsbnCorrectWithoutSpacesAndLines10 = new ISBN("3680087837");
        assertTrue(errorMessage("isValid()", IsbnCorrectWithoutSpacesAndLines10), ISBN.isValid(IsbnCorrectWithoutSpacesAndLines10));
    }


    @Test
    public void checkISBNLength() {
        ISBN IsbnTooShort = new ISBN("978-3-12-2033");
        assertFalse(errorMessage("isValid()", IsbnTooShort), ISBN.isValid(IsbnTooShort));

        ISBN IsbnTooLong = new ISBN("978-3-12-732320-7833");
        assertFalse(errorMessage("isValid()", IsbnTooLong), ISBN.isValid(IsbnTooLong));

        ISBN IsbnNoCharacters = new ISBN("");
        assertFalse(errorMessage("isValid()", IsbnNoCharacters), ISBN.isValid(IsbnNoCharacters));
    }

    @Test
    public void checkISBNIncorrectCharacters() {
        ISBN IsbnIncorrectCharactersA = new ISBN("978-3-12-7323D0-7");
        assertFalse(errorMessage("isValid()", IsbnIncorrectCharactersA), ISBN.isValid(IsbnIncorrectCharactersA));

        ISBN IsbnIncorrectCharactersB = new ISBN("978-3-1%-7--6D0-7");
        assertFalse(errorMessage("isValid()", IsbnIncorrectCharactersB), ISBN.isValid(IsbnIncorrectCharactersB));
    }

    @Test
    public void checkISBNChecksum() {
        /*
         * ISBN-13
         */
        ISBN IsbnWrongChecksum13A = new ISBN("978-3-12-732320-8");
        assertFalse(errorMessage("isValid()", IsbnWrongChecksum13A), ISBN.isValid(IsbnWrongChecksum13A));

        ISBN IsbnWrongChecksum13B = new ISBN("978-3-12-732320-4");
        assertFalse(errorMessage("isValid()", IsbnWrongChecksum13B), ISBN.isValid(IsbnWrongChecksum13B));

        /*
         * ISBN-10
         */

        ISBN IsbnWrongChecksum10A = new ISBN("3-86680-192-1");
        assertFalse(errorMessage("isValid()", IsbnWrongChecksum10A), ISBN.isValid(IsbnWrongChecksum10A));
    }

    @Test
    public void checkISBNPrefix() {
        ISBN IsbnWrongPrefixA = new ISBN("968-3-12-732320-7");
        assertFalse(errorMessage("isValid()", IsbnWrongPrefixA), ISBN.isValid(IsbnWrongPrefixA));

        ISBN IsbnWrongPrefixB = new ISBN("123-3-12-732320-7");
        assertFalse(errorMessage("isValid()", IsbnWrongPrefixB), ISBN.isValid(IsbnWrongPrefixB));
    }


    @Test
    public void deleteLinesAndSpaces() throws Exception {
        ISBN IsbnA = new ISBN("978-3-86680-192-9");
        assertEquals("9783866801929", IsbnA.deleteLines(IsbnA.getISBN()));

        ISBN IsbnB = new ISBN("978 -3 86680 192 911");
        assertEquals("978386680192911", IsbnB.deleteLines(IsbnB.getISBN()));
    }

    @Test
    public void getChecksum() throws Exception {

        ISBN IsbnLine = new ISBN("978-3-12-732320-7");
        //assertEquals(7, IsbnLine.getChecksum(IsbnLine.getISBN()));

        ISBN IsbnSpace = new ISBN("978312  7323207");
        //assertEquals(7, IsbnSpace.getChecksum(IsbnSpace.getISBN()));

        ISBN IsbnOtherChecksum = new ISBN("978-3-22-732320-6");
        //assertEquals(6, IsbnOtherChecksum.getChecksum(IsbnOtherChecksum.getISBN()));
    }

    public static String errorMessage(String method, ISBN Isbn) {
        return "Betroffene Methode(n): " + method + ". Betroffene ISBN: " + Isbn.getISBN();
    }

}