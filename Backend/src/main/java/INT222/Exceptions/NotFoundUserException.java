package INT222.Exceptions;

public class NotFoundUserException  extends RuntimeException{
    public NotFoundUserException(long id) {
        super("Could not find user id: " + id);
    }
}
