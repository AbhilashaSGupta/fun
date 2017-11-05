package ninja.abhilasha.exceptions;

public class InvalidParamException extends Exception {

    public InvalidParamException(String message, Object input, Object expected) {
        super(message + " Provided Input:: " + input + " Expected value:: " + expected);
    }

    public InvalidParamException(String message, Object input) {
        super(message + " Provided Input:: " + input);
    }

}
