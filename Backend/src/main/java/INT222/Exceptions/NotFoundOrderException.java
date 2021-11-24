package INT222.Exceptions;

public class NotFoundOrderException extends RuntimeException{


    public NotFoundOrderException(Long id) {
        super("Could not find order id: " + id);
    }
}
