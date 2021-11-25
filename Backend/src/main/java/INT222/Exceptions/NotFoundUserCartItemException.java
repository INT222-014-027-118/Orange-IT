package INT222.Exceptions;

public class NotFoundUserCartItemException extends RuntimeException {


    public NotFoundUserCartItemException(long id) {
        super("Could not find cart item of user id: " + id);
    }
}
