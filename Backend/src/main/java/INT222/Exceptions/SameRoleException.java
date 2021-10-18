package INT222.Exceptions;

public class SameRoleException  extends RuntimeException{

    public SameRoleException(String name) {
        super("Role " + name + " is already in database");
    }

}
