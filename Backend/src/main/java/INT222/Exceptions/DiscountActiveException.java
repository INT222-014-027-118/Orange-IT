package INT222.Exceptions;

public class DiscountActiveException extends RuntimeException{

    public DiscountActiveException(long id) {
        super("Discount Id: " + id + " is Active, can not delete!");
    }
}
