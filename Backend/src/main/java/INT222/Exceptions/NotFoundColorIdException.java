package INT222.Exceptions;

public class NotFoundColorIdException  extends RuntimeException{

    public NotFoundColorIdException(long id) {
        super("Could not find color id: " + id);
    }
}
