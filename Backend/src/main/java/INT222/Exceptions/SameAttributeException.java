package INT222.Exceptions;

public class SameAttributeException extends RuntimeException{


    public SameAttributeException(String attribute) {
        super(attribute + " is already in database");
    }
}
