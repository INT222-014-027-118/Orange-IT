package INT222.Exceptions;

public class SameColorNameException extends RuntimeException{


    public SameColorNameException(String name) {
        super("Color name " + name + " is already in database");
    }
}

