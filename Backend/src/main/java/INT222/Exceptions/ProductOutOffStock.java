package INT222.Exceptions;

public class ProductOutOffStock extends RuntimeException{

    public ProductOutOffStock(Long id) {
        super("Product " +  id  + " Out Off Stock!!");
    }
}
