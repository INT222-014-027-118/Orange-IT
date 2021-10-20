package INT222.Exceptions;

public class NotFoundCartItemException extends RuntimeException{
    public NotFoundCartItemException(long id) {
        super("Could not find cartItem id: " + id);
    }
}
