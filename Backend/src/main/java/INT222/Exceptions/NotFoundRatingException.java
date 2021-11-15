package INT222.Exceptions;

public class NotFoundRatingException extends RuntimeException {


    public NotFoundRatingException(long id) {
        super("Could not find rating id: " + id);
    }
}
