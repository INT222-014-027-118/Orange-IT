package INT222.Exceptions;

public class ProductActiveException extends RuntimeException{

    public ProductActiveException(Long id) {
        super("Product Id: " + id + " is Active, can not delete!");
    }
}
