package INT222.Exceptions;

public class SameColorHexException extends RuntimeException{


    public SameColorHexException(String hexColor) {
        super(hexColor + " is already in database");
    }
}
