package infrastructure.util;

public class InvalidCommandException extends Exception {
    InvalidCommandException(Exception e){
        super(e);
    }
}
