package INT222.Exceptions;

public class NotFoundDiscountException extends RuntimeException{

    public NotFoundDiscountException(long id) {
        super("Could not find discount id: " + id);
    }
}
