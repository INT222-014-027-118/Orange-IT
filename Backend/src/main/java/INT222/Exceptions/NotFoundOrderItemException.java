package INT222.Exceptions;

public class NotFoundOrderItemException extends RuntimeException{


    public NotFoundOrderItemException(Long id) {
        super("Could not find order item id: " + id);
    }
}
