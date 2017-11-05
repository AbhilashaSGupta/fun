package ninja.abhilasha;

import ninja.abhilasha.exceptions.InvalidParamException;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import static java.util.Collections.nCopies;

public class RomanNumeral {
    private static Map<String, Integer> cachedValue = initializeCache();

    public static void main(String[] args) throws InvalidParamException {

        System.out.println("Please enter the integer or roman numeral you want to transform");

        Scanner scan = new Scanner(System.in);

        String input = scan.next().toUpperCase();

        if (input.matches("^\\d+(\\.\\d+)?")) {
            System.out.println(toRomanNumeral(Integer.parseInt(input)));
            return;
        }

        System.out.println(toInteger(input));


    }

    protected static String toRomanNumeral(Integer input) {
        return String.join("", nCopies(input, "I"))
                     .replace("IIIII", "V")
                     .replace("IIII", "IV")
                     .replace("VV", "X")
                     .replace("VIV", "IX")
                     .replace("XXXXX", "L")
                     .replace("XXXX", "XL")
                     .replace("LL", "C")
                     .replace("LXL", "XC")
                     .replace("CCCCC", "D")
                     .replace("CCCC", "CD")
                     .replace("DD", "M")
                     .replace("DCD", "CM");
    }

    protected static Integer toInteger(final String input) throws InvalidParamException {
        boolean valid = input.matches("^M{0,4}(CM|CD|D?C{0,3})(XC|XL|L?X{0,3})(IX|IV|V?I{0,3})$");

        if (!valid) {
            throw new InvalidParamException("Not a valid roman numeral", input);
        }
        return toIntegerCast(input);
    }


    private static int toIntegerCast(String input) throws InvalidParamException {
        if (StringUtils.isEmpty(input)) {
            return 0;
        }

        if (input.length() == 1) {
            if (cachedValue.containsKey(input)) {
                return cachedValue.get(input);
            }
            else {
                throw new InvalidParamException("Cannot convert to integer", input);
            }
        }

        String twoDigitPossibility = input.substring(0, 2);
        if (cachedValue.containsKey(twoDigitPossibility)) {
            return cachedValue.get(twoDigitPossibility) + toIntegerCast(input.substring(2, input.length()));
        }
        else {
            String oneDigitPossibility = input.substring(0, 1);

            if (cachedValue.containsKey(oneDigitPossibility)) {
                return cachedValue.get(oneDigitPossibility) + toIntegerCast(input.substring(1, input.length()));
            }
            else {
                throw new InvalidParamException("Cannot convert to integer", input);
            }
        }

    }

    private static Map<String, Integer> initializeCache() {
        Map<String, Integer> cachedValue = new HashMap<>();
        cachedValue.put("M", 1000);
        cachedValue.put("CM", 900);
        cachedValue.put("D", 500);
        cachedValue.put("CD", 400);
        cachedValue.put("C", 100);
        cachedValue.put("XC", 90);
        cachedValue.put("L", 50);
        cachedValue.put("XL", 40);
        cachedValue.put("X", 10);
        cachedValue.put("IX", 9);
        cachedValue.put("V", 5);
        cachedValue.put("IV", 4);
        cachedValue.put("I", 1);

        return cachedValue;
    }


}
