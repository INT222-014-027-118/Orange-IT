package INT222.Exceptions;

public class NotFoundAttributeException extends RuntimeException{


    public NotFoundAttributeException(Long id) {
        super("Attribute id: " + id + " is not found");
    }
}
