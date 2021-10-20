package INT222.Exceptions;

public class SameCategoryException  extends RuntimeException{


    public SameCategoryException(String category) {
        super(category + " is already in database");
    }
}
