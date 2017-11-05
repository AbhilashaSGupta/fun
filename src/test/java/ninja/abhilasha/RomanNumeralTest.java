package ninja.abhilasha;

import junit.framework.TestCase;

import static org.assertj.core.api.Assertions.assertThat;


public class RomanNumeralTest extends TestCase {

    public void testToRomanNumeral() throws Exception {

        //verify integer
        for (int i = 1; i < 1024; i++) {

            final String romanNumeral = RomanNumeral.toRomanNumeral(i);

            final int integer = RomanNumeral.toInteger(romanNumeral);

            assertThat(i).isEqualTo(integer);

            System.out.println("i = " + i + " -> " + romanNumeral + " -> " + integer);
        }

    }

    public void testToInteger() throws Exception {
        assertThat(RomanNumeral.toInteger("IV")).isEqualTo(4);
    }


}