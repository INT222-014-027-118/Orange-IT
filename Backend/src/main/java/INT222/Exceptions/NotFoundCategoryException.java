package INT222.Exceptions;

public class NotFoundCategoryException extends RuntimeException{
    public NotFoundCategoryException(long id) {
        super("Could not find category id: " + id);
    }
}
