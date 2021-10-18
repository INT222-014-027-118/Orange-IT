package INT222.Exceptions;

public class NotFoundDeliveryDetailException  extends RuntimeException{

    public NotFoundDeliveryDetailException(long id) {
        super("Could not find delivery detail id: " + id);
    }
}
